package online.zhaopei.myproject.config;

import online.zhaopei.myproject.integration.DefaultFileNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

import static org.springframework.integration.support.MutableMessageBuilder.withPayload;

/**
 * Created by zhaopei on 17/12/20.
 */
@Configuration
@EnableIntegration
public class IntegrationConfiguration {

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileProcessChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileOutputChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedRate = "100"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("/Users/zhaopei/work/test/send"));
        source.setFilter(new SimplePatternFileListFilter("*.xml"));
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileOutputChannel")
    public MessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/Users/zhaopei/work/test/receive"));
        handler.setDeleteSourceFiles(true);
        handler.setExpectReply(false); //没有replyChannel或者outputChannel要设置成false，不然会出错
        handler.setFileNameGenerator(new DefaultFileNameGenerator("FILE_RECEIVE_", ".csv", false));
        return handler;
    }

    @ServiceActivator(inputChannel = "fileProcessChannel")
    public void processMessage(String message) {
        System.out.println("message is :[] " + message);
        Message<String> outMsg = MessageBuilder.withPayload("处理消息 " + message).build();
        fileOutputChannel().send(outMsg);
    }

    @Bean
    @Transformer(inputChannel = "fileInputChannel", outputChannel = "fileProcessChannel")
    public FileToStringTransformer fileToStringTransformer() {
        FileToStringTransformer transformer = new FileToStringTransformer();
        transformer.setCharset("UTF-8");
        transformer.setDeleteFiles(true);
        return transformer;
    }
}
