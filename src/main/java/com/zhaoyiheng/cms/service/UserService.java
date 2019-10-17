package com.zhaoyiheng.cms.service;

import com.zhaoyiheng.cms.entity.User;

public interface UserService {
	int register(User user) ;
	User login(User user);
	
	boolean checkUserExist(String username);
}
