package com.zhaoyiheng.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaoyiheng.cms.dao.CatMapper;
import com.zhaoyiheng.cms.entity.Cat;
import com.zhaoyiheng.cms.service.CatService;

/**
 * 
 * @author zhaoyiheng
 *
 */
@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	CatMapper catMapper;

	/**
	 * 根据频道去获取下边的分类
	 * @param id
	 * @return
	 */
	@Override
	public List<Cat> getListByChnlId(Integer id) {
		// TODO Auto-generated method stub
		return catMapper.selectByChnlId(id);
	}
	

}
