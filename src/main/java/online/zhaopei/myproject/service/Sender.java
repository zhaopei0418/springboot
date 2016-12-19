package online.zhaopei.myproject.service;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import online.zhaopei.myproject.domain.City;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate amqpTemlate;
	
	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	public void send() throws Exception {
		String context = "hello" + new Date();
		System.out.println("Sender:" + context);
		this.amqpTemlate.convertAndSend("hello", context);
	}
	
	public void send(City city) {
		this.rabbitMessagingTemplate.convertAndSend("hello", city);
	}
}
