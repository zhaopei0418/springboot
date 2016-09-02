package online.zhaopei.myproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.zhaopei.myproject.domain.UserTest;
import online.zhaopei.myproject.mapper.second.UserTestMapper;
import online.zhaopei.myproject.service.UserTestService;

@Service
public class UserTestServiceImpl implements UserTestService {

	@Autowired
	private UserTestMapper userTestMapper;
	
	@Override
	@Transactional(value = "secondTxMan", readOnly = true)
	public List<UserTest> getAllUsers() {
		return this.userTestMapper.getAllUsers();
	}

}
