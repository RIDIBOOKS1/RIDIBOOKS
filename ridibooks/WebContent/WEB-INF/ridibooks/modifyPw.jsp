<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="modify/password.do" method="post">
	<input type="hidden" name="id" value="${vo.id }">
	<input type="hidden" name="email" value="${vo.email }">
	<input type="password" name="currentPW">
	<input type="password" name="newPW">
	<input type="password" name="newPWCheck">
	<input type="submit" value="정보 변경하러 가기">
</form>
</body>
</html>