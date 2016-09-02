package online.zhaopei.myproject.mapper.second;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import online.zhaopei.myproject.domain.UserTest;
import online.zhaopei.myproject.sqlprovide.UserTestProvide;

public interface UserTestMapper {

	@SelectProvider(type = UserTestProvide.class, method = "getAllUsers")
	List<UserTest> getAllUsers();
}
