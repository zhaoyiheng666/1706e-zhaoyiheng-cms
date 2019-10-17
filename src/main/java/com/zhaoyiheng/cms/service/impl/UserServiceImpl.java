package com.zhaoyiheng.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaoyiheng.cms.dao.UserMapper;
import com.zhaoyiheng.cms.entity.User;
import com.zhaoyiheng.cms.service.UserService;
import com.zhaoyiheng.utils.Md5Utils;

/**
 * 
 * @author zhaoyiheng
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	

	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.findByName(user.getUsername());
		if(existUser!=null) {
			return -1;// 用户已经存在
		}
		user.setPassword(Md5Utils.md5(user.getPassword(),user.getUsername()));
		
		return userMapper.add(user);
		
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String pwdStr = Md5Utils.md5(user.getPassword(),user.getUsername());
		User loginUser =  userMapper.findByName(user.getUsername());
		if(loginUser!=null && pwdStr.equals(loginUser.getPassword())) {
			return loginUser;
		}
		return null;
	}

	@Override
	public boolean checkUserExist(String username) {
		// TODO Auto-generated method stub
		return null!=userMapper.findByName(username);
		
	}

}