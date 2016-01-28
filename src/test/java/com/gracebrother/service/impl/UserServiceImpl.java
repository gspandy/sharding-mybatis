package com.gracebrother.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gracebrother.dao.UserDao;
import com.gracebrother.multipledatasource.CustomerContextHolder;
import com.gracebrother.pojo.User;
import com.gracebrother.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	public void insert(){
		for(int i=1;i<=50;i++){
			User u=new User();
			u.setId(i);
			u.setName("user_"+i);
			userDao.insert(u);
		}
		CustomerContextHolder.setContextType("dataSource2");
		for(int i=51;i<=100;i++){
			User u=new User();
			u.setId(i);
			u.setName("user_"+i);
			userDao.insert(u);
		}
		CustomerContextHolder.clearContextType();
	}
}
