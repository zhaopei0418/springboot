package online.zhaopei.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("/")
	public String home() {
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
