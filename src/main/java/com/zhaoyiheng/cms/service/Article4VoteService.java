package com.zhaoyiheng.cms.service;

import java.util.List;

import com.zhaoyiheng.cms.entity.Article4Vote;
import com.zhaoyiheng.cms.entity.VoteStatic;

/**
 * 
 * @author zhaoyiheng
 *
 */
public interface Article4VoteService {
	
	int publish(Article4Vote av);
	
	List<Article4Vote>  list();
	
	Article4Vote  findById(Integer id);
	
	int vote(Integer articleId,Character option);
	//int vote(Integer userId, Integer articleId,Character option);
	
	List<VoteStatic> getVoteStatics(Integer articleId);
	
	
	

}
