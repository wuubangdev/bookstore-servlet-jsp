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
		String err = request.getAttribute("err")+"";
 	%>
 	<div class="container">
 		<h1><%=err %></h1>
 	</div>
</body>
</html>