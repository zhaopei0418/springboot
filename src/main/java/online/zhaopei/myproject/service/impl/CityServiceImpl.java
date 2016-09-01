package online.zhaopei.myproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.zhaopei.myproject.domain.City;
import online.zhaopei.myproject.mapper.CityMapper;
import online.zhaopei.myproject.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<City> findByState(String state) {
		return this.cityMapper.findByState(state);
	}

}
