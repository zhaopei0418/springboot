package online.zhaopei.myproject.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import online.zhaopei.myproject.domain.City;
import online.zhaopei.myproject.service.CityService;

@RestController
public class CityController {

	@Resource
	private CityService cityService;
	
	@RequestMapping("/city/getCity")
	public List<City> getCity() {
		PageHelper.startPage(3, 3);
		return this.cityService.findByState("CA");
	}
}
