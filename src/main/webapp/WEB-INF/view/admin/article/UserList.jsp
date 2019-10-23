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
	<table class="table table-sm  table-hover table-bordered ">
		<tr>
			<th>用户名</th>
			<th>创建日期</th>
			<th>是否封禁</th>
			<th>封禁用户</th>
		</tr>
		<c:forEach items="${ulist}" var="u">
			<tr>
				<td>${u.username}</td>
				<td>${u.create_time}</td>
				<td>${u.locked==0?"未封禁":u.locked==1?"已封禁":"永久封禁"}</td>
				<td>
					<input type="button" value="封禁该用户" onclick="banned(${u.id})">
					<input type="button" value="解封该用户" onclick="noBanned(${u.id})">
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
	function banned(id){
		location="/user/bannedUser?id="+id;
	}
	
	function noBanned(id){
		
	}
</script>
</html>