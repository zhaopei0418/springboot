package online.zhaopei.myproject.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import online.zhaopei.myproject.domain.City;

@Component
public class Receiver {

	public void process(String hello) {
		System.out.println("receiver:" + hello);
	}
	
	@RabbitListener(queues = "hello")
	public void process(City city) {
		System.out.println("city.name===[" + city.getCountry() + "]");
		System.out.println(new Gson().toJson(city));
	}
}
