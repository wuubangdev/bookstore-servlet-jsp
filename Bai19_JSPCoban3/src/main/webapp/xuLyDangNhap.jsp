<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String userName = request.getParameter("userName");
		String passsword = request.getParameter("inputPassword");
	%>
	<%
		if(userName.equalsIgnoreCase("bangkbdtvtcm")&&passsword.equals("123456a")) 
		{
	%>
		<h1>Bạn đã nhập thành công</h1>
	<%
		} else {
	%>
		<h1>Bạn đăng nhập không thành công</h1>
	<%
		}
	%>
</body>
</html>