package online.zhaopei.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.zhaopei.myproject.domain.City;
import online.zhaopei.myproject.service.Sender;

@RestController
public class UserController {

	@Autowired
	private Sender sender;
	
	@RequestMapping("/")
	public String home() throws Exception {
//		this.sender.send();
		
		City city = new City();
		city.setId(111L);
		city.setName("name-city");
		city.setState("sss");
		city.setCountry("country===");
		//注释掉不再发送，自行开启
//		this.sender.send(city); 
		
		return "home";
	}

	@RequestMapping("/user/list")
	public List<String> listUser() {
		List<String> list = new ArrayList<String>();
		list.add("s1");
		list.add("s2");
		list.add("s3");
		list.add("s4");

		return list;
	}
}
