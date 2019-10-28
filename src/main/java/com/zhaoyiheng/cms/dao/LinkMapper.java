package com.zhaoyiheng.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhaoyiheng.cms.entity.Link;
/**
 * 
 * @author zhaoyiheng
 *
 */
public interface LinkMapper {
	/**
	 * 友情链接列表
	 * @return
	 */
	@Select("select * from cms_link")
	List<Link> linkList();
	
	@Select("select * from cms_link")
	List<Link> linklist();
	
	//有情链接的添加
	@Insert("insert into cms_link (http,name) VALUES(#{http},#{name})" )
	int addlink(Link link);

	//友情链接的删除
	@Delete("delete from cms_link where id =#{id}")
	int deletelink(Integer id);

}
