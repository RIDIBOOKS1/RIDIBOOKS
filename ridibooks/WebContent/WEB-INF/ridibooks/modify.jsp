<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="modify.do" method="post">
	<input type="hidden" name="id" value="${vo.id }">
	<input type="hidden" name="email" value="${vo.email }">
	<input type="password" name="pw">
	<input type="submit" value="정보 변경하러 가기">
</form>
</body>
</html>