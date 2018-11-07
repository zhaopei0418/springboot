package online.zhaopei.myproject.controller;

import org.springframework.integration.context.IntegrationObjectSupport;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController extends IntegrationObjectSupport {

    @Resource(name = "redisReceiveChannel")
    private MessageChannel redisReceiveChannel;

    @Resource(name = "redisSendChannel")
    private MessageChannel redisSendChannel;

    @RequestMapping("/publish")
    public String publishMessage(String message) {
        Message m = this.getMessageBuilderFactory().withPayload(message).build();
        this.redisSendChannel.send(m);
        return "put message: [" + message + "] success";
    }

}
