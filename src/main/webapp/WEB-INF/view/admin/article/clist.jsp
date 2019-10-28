<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="<%=path%>/css/index_work.css"/> 
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<table class="table" border="1" align="right">
		<tr>
			<td>编号</td>
			<td>文章标题</td>
			<td>用户</td>
			<td>评论内容</td>
			<td>评论时间</td>
		</tr>
		<c:forEach items="${clist }" var="c">
			<tr>
				<td>${c.id }</td>
				<td>${c.articleId }</td>
				<td>${c.userName }</td>
				<td>${c.content }</td>
				<td>${c.created }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>