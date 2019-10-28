package com.zhaoyiheng.cms.service;

import java.util.List;

import com.zhaoyiheng.cms.entity.Link;

public interface LinkService {
	//友情链接列表
	List<Link> linkList();
	
	//获取友情链接列表
	List<Link> linklist();
	//获取友情链接添加
	int addlink(Link link);
		
	//友情链接的删除
	int deletelink(Integer id);

}
