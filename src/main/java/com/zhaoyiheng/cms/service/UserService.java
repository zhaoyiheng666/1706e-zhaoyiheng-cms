package com.zhaoyiheng.cms.service;

import java.util.List;

import com.zhaoyiheng.cms.entity.User;

public interface UserService {
	
	int register(User user) ;
	User login(User user);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	boolean checkUserExist(String username);
	
	/**
	 * 管理员查询用户
	 * @param locked
	 * @return
	 */
	List<User> UserList(Integer locked);
	
	/**
	 * 封禁用户
	 * @param id
	 * @return
	 */
	int bannedUser(Integer id);
	

}
