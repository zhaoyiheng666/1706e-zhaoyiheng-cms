package com.zhaoyiheng.cms.service;

import java.util.List;

import com.zhaoyiheng.cms.entity.User;
/**
 * 
 * @author zhaoyiheng
 *
 */
public interface UserService {
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	int register(User user) ;
	
	User login(User user);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	boolean checkUserExist(String username);
	
	/**
	 * 封禁用户
	 * @param id
	 * @return
	 */
	int bannedUser(Integer id);
	
	/**
	 * 解封用户
	 * @param id
	 * @return
	 */
	int noBannedUser(Integer id);
	
	/**
	 * 管理员查询用户的列表
	 * @param locked
	 * @return
	 */
	List<User> UserList(Integer locked);
	
	/**
	 * 个人主要上传头像
	 * @param user
	 * @return
	 */
	int addHead_picture(User user);
}
