package com.zhaoyiheng.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaoyiheng.cms.dao.ChannelMapper;
import com.zhaoyiheng.cms.entity.Channel;
import com.zhaoyiheng.cms.service.ChannelService;

/**
 * 
 * @author zhaoyiheng
 *
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	ChannelMapper channelMapper;
	
	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	@Override
	public List<Channel> getAllChnls() {
		// TODO Auto-generated method stub
		return channelMapper.listAll();
	
	}

}
