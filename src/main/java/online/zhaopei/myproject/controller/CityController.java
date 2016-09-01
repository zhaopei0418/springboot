package online.zhaopei.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.zhaopei.myproject.domain.City;
import online.zhaopei.myproject.mapper.CityMapper;

@RestController
public class CityController {

	@Autowired
	private CityMapper cityMapper;
	
	@RequestMapping("/city/getCity")
	public City getCity() {
		return this.cityMapper.findByState("CA");
	}
}
