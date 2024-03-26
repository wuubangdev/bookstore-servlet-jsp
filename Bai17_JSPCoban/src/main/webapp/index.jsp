<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bai Tap JSP-1</title>
</head>
<body>
	<%
		int a = 3;
		int b = 2;
		int sum = a +b;
		double cb2 = Math.sqrt(sum);
	%>
	Ba cong hai bang <b><%=cb2 %></b>
</body>
</html>