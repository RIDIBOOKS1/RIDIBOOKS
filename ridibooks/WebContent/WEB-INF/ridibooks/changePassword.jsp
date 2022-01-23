<%@page import="kr.ridibooks.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%
	// MemberVO vo = (MemberVO)request.getAttribute("foundVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="reset-password.do" method="post">
	<input type="hidden" name = "id" value = ${foundVO.id }>
	<input type="password" name="newPW">
	<input type="password" name="newPWCheck">
	<input type="submit" value="비밀번호 변경">
</form>
</body>
</html>