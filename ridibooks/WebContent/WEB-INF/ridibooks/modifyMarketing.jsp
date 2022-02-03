<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="modify/marketing.do" method="post">
	<input type="hidden" name="id" value="${vo.id }">
	<input type="hidden" name="email" value="${vo.email }">
	<div style="margin: 10px">
	<label for="emailmarketing">
		<input id="emailmarketing" type="checkbox" name="emailSub" value="agree"> 이메일 구독
		<input id="emailmarketing" type="text" name="emailToSub">
	</label>
	</div>
	<div style="margin: 10px">
	<label for="apppush">
		<input id="apppush" type="checkbox" name="appPush" value="agree"> 앱 푸시 수신
	</label>
	</div>
	<div style="margin: 10px">
	<label for="apppushnight">
		<input id="apppushnight" type="checkbox" name="nightAppPush" value="agree"> 야간 앱 푸시 수신
	</label>
	</div>
	<div>
	<input type="submit" value="마케팅 정보 변경하러 가기">
	</div>
	</div>
</form>
</body>
</html>