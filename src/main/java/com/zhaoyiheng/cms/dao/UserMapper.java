package com.zhaoyiheng.cms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhaoyiheng.cms.entity.User;

/**
 * 
 * @author zhaoyiheng
 *
 */
public interface UserMapper {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@Insert("INSERT INTO cms_user(username,password,gender,create_time) "
			+ "values(#{username},#{password},#{gender},now())")
	int add(User user);
	
	/**
	 * 根据用户名查账
	 * @param username
	 * @return
	 */
	@Select("SELECT id,username,password FROM cms_user where username=#{value} limit 1")
	User findByName(String username);
	

}
