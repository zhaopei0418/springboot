package online.zhaopei.myproject.service;

import java.util.List;

import online.zhaopei.myproject.domain.City;

public interface CityService {

	List<City> findByState(String state);
}
