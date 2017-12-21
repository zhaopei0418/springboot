package online.zhaopei.myproject.integration;

import ch.qos.logback.core.CoreConstants;
import online.zhaopei.myproject.config.IntegrationConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.context.IntegrationObjectSupport;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by zhaopei on 17/12/21.
 */
public class QueueReadingMessageSource extends IntegrationObjectSupport implements MessageSource<String> {

    private static final Log logger = LogFactory.getLog(QueueReadingMessageSource.class);

    @Override
    public Message<String> receive() {
        Message<String> message = null;
        String[] ss = IntegrationConfiguration.RECEIPT_QUEUE.poll();
        if (null != ss) {
            message = this.getMessageBuilderFactory().withPayload(ss[1]).setHeaderIfAbsent("type", ss[0]).build();
            logger.info("Created message: [" + message + "]");
        }
        return message;
    }
}
