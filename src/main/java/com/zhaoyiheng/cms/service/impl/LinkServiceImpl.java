package com.zhaoyiheng.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaoyiheng.cms.dao.LinkMapper;
import com.zhaoyiheng.cms.entity.Link;
import com.zhaoyiheng.cms.service.LinkService;
/**
 * 
 * @author zhaoyiheng
 *
 */
@Service
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	LinkMapper linkMapper;
	
	/**
	 * 友情链接列表
	 */
	@Override
	public List<Link> linkList() {
		return linkMapper.linkList();
	}

	@Override
	public List<Link> linklist() {
		// TODO Auto-generated method stub
		return linkMapper.linklist();
	}
	//友情链接的添加
	@Override
	public int addlink(Link link) {
		// TODO Auto-generated method stub
		return linkMapper.addlink(link);
	}
	
	//友情链接的删除
	@Override
	public int deletelink(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.deletelink(id);
	}

}
