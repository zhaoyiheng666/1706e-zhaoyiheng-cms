package com.zhaoyiheng.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zhaoyiheng.cms.comon.ArticleType;
import com.zhaoyiheng.cms.comon.CmsAssertJson;
import com.zhaoyiheng.cms.comon.CmsExceptionJson;
import com.zhaoyiheng.cms.comon.ConstClass;
import com.zhaoyiheng.cms.comon.ResultMsg;
import com.zhaoyiheng.cms.entity.Article;
import com.zhaoyiheng.cms.entity.Cat;
import com.zhaoyiheng.cms.entity.Channel;
import com.zhaoyiheng.cms.entity.Comment;
import com.zhaoyiheng.cms.entity.ImageBean;
import com.zhaoyiheng.cms.entity.User;
import com.zhaoyiheng.cms.service.ArticleService;
import com.zhaoyiheng.cms.service.CatService;
import com.zhaoyiheng.cms.service.ChannelService;
import com.zhaoyiheng.utils.FileUtils;

/**
 * 
 * @author zhaoyiheng
 *
 */
@Controller
@RequestMapping("article")
public class ArticleController   {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ChannelService chanService;
	
	
	@Autowired
	CatService catService;
	
	
	/**
	 *  显示一篇具体的文章
	 * @param id  文章的id
	 * @return
	 */
	@RequestMapping("show")
	public String show(HttpServletRequest request, Integer id) {
		CmsAssertJson.Assert(id!=0,"文章id不能等于0");
		Article  article = articleService.findById(id);
		
		if(article.getArticleType()==ArticleType.HTML) {
			request.setAttribute("article", article);
			return "article/detail";
		}else {
			Gson gson = new Gson();
			article.setImgList(gson.fromJson(article.getContent(), List.class));
			request.setAttribute("article", article);
			return "article/slieimgarticle";
		}
	}
	
	/**
	 * 跳转到添加的页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add",method=RequestMethod.GET)
	public String add(HttpServletRequest request) {
		List<Channel> allChnls = chanService.getAllChnls();
		request.setAttribute("channels", allChnls);
		return "article/publish";
		
	}
	
	@RequestMapping(value = "addimg",method=RequestMethod.GET)
	public String addimg(HttpServletRequest request) {
		List<Channel> allChnls = chanService.getAllChnls();
		request.setAttribute("channels", allChnls);
		return "article/publishimg";
		
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "addimg",method=RequestMethod.POST)
	public String addimg(HttpServletRequest request,Article article, 
			@RequestParam("file") MultipartFile file,//标题图片
			@RequestParam("imgs") MultipartFile[] imgs,// 文章中图片
			@RequestParam("imgsdesc") String[]  imgsdesc// 文章中图片的描述
			) throws IllegalStateException, IOException {
		
		
		article.setArticleType(ArticleType.IMAGE);
		
		processFile(file,article);
		List<ImageBean> imgBeans =  new ArrayList<ImageBean>();
		
		for (int i = 0; i < imgs.length; i++) {
			String picUrl = processFile(imgs[i]);//
			if(!"".equals(picUrl)) {
				ImageBean imageBean = new ImageBean(imgsdesc[i],picUrl);
				imgBeans.add(imageBean);
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(imgBeans);// 文章的内容
		article.setContent(json);//
		
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		articleService.add(article);
		
		return "article/publish";
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "add",method=RequestMethod.POST)
	public String add(HttpServletRequest request,Article article, MultipartFile file) throws IllegalStateException, IOException {
		
		processFile(file,article);
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		articleService.add(article);
		
		return "article/publish";
		
	}
	
	
	
	/**
	 * 跳转到修改的页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update",method=RequestMethod.GET)
	public String update(HttpServletRequest request,Integer id) {
		
		List<Channel> allChnls = chanService.getAllChnls();
		Article article = articleService.findById(id);
		
		request.setAttribute("article", article);
		request.setAttribute("content1", article.getContent());
		request.setAttribute("channels", allChnls);
		return "my/update";
		
	}
	
	/**
	 * 处理文章的附件上传
	 * @param file
	 * @param article
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private void processFile(MultipartFile file,Article article) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			article.setPicture("");
			return;
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		article.setPicture(destFileName.substring(7));
		
	}
	
	/**
	 * 处理每一个图片集合中的文件
	 * @param file
	 * @param article
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(7);
		
		
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public boolean update(HttpServletRequest request,Article article, MultipartFile file) throws IllegalStateException, IOException {
		
		processFile(file,article);
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		int result = articleService.update(article);
		
		return result > 0;
		
	}
	
	
	/**
	 * 根据频道获取相应的分类  用户发布文章或者修改文章的下拉框
	 * @param chnlId 频道id
	 * @return
	 */
	@RequestMapping(value="listCatByChnl",method=RequestMethod.GET)
	@ResponseBody
	//public List<Cat> getCatByChnl(int chnlId){
	public ResultMsg getCatByChnl(int chnlId){
		CmsAssertJson.Assert(chnlId>0,"频道id必须大于0");
		List<Cat> chnlList = catService.getListByChnlId(chnlId);
		return new ResultMsg(1, "获取数据成功", chnlList);
	}
	
	
	
	/**
	 *  发布评论
	 * @param content
	 * @return
	 *///article/comment
	@RequestMapping("comment")
	@ResponseBody
	public ResultMsg comment(HttpServletRequest request,Integer articleId, String content) {
		User loginUser= (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		if(loginUser==null) {
			return new ResultMsg(2, "用户尚未登陆","");
		}
		articleService.comment(loginUser.getId(),articleId,content);
		return new ResultMsg(1, "发布成功","");
		
	}
	
	@RequestMapping("getclist")
	public String getComment(HttpServletRequest request,Integer articleId,
			@RequestParam(defaultValue="1") Integer page) {
		PageInfo<Comment> comments = articleService.getCommentByArticleId(articleId, page);
		request.setAttribute("comments", comments);
		return "article/clist";
	}

	/**
	 * 获取我的评论
	 * 
	 */
	@RequestMapping("clist")
	public String list(HttpServletRequest request) {
		User loginUser =(User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		List<Comment> clist = articleService.clist(loginUser.getId());
		request.setAttribute("clist", clist);
		return "admin/article/clist";
		
		
	}
}
