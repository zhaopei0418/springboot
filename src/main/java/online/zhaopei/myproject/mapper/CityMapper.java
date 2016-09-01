package online.zhaopei.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import online.zhaopei.myproject.domain.City;

@Mapper
public interface CityMapper {

	@Select("select * from city where state = #{state}")
	List<City> findByState(@Param("state") String state);
}
