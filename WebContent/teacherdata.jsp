<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.* "%>
<%@page import="com.fuzhu.studentmanager.HomeworkServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业信息</title>
</head>
<body>
	<br>
	<a>今日作业信息</a>
	<br>
		<%
		List<String> homework=(List<String>)request.getAttribute("homework"); //request.getAttribute("homework")
		if(homework!=null){
			Iterator<String> iter = homework.iterator();
			while(iter.hasNext()){
			%>
			<h4><%=iter.next() %></h4>
			<%
			}
		}
%>
	<form action="HomeworkServlet" method="post" align="center">
		
			<textarea name="content" style="width:200px;height:80px;"></textarea>
			<input type="submit"  value="发布作业"/>
	</form>	
	
</body>
</html>