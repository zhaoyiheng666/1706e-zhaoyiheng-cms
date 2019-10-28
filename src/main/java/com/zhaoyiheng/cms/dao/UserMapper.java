package com.zhaoyiheng.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	 * 根据用户名查找
	 * @param username
	 * @return
	 */
	@Select("SELECT id,username,password,role,head_picture FROM cms_user "
			+ "WHERE username=#{value} limit 1")
	User findByName(String username);
	
	/**
	 * 管理员查询用户列表
	 * @param locked
	 * @return
	 */
	@Select("select id,username,create_time,locked from cms_user where role=0")
	List<User> UserList(Integer locked);

	
	/**
	 * 管理员封禁用户
	 * @param id
	 * @return
	 */
	@Update("update cms_user set locked=1 where id=#{id}")
	int bannedUser(Integer id);

	/**
	 * 解封用户
	 * @param id
	 * @return
	 */
	@Update("update cms_user set locked=0 where id=#{id}")
	int noBannedUser(Integer id);
	
	/**
	 * 上传头像图片
	 * @param user
	 * @return
	 */
	@Update("update cms_user set head_picture=#{head_picture} where id=#{id}")
	int addHead_picture(User user);
}
