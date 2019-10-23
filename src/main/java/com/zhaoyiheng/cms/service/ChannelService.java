package com.zhaoyiheng.cms.service;


import java.util.List;

import com.zhaoyiheng.cms.entity.Channel;

/**
 * 
 * @author zhaoyiheng
 *
 */
public interface ChannelService {

	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	List<Channel> getAllChnls();

}
