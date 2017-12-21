package online.zhaopei.myproject.controller;

import online.zhaopei.myproject.config.IntegrationConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaopei on 17/12/21.
 */
@RestController
@RequestMapping("/queue")
public class QueueController {

    @RequestMapping("/{type}/{message}")
    public String putMessage(@PathVariable("type") String type, @PathVariable("message") String message) throws Exception {
        IntegrationConfiguration.RECEIPT_QUEUE.put(new String[]{type, message});
        return "{\"success\": true}";
    }
}
