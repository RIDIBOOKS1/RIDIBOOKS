<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="leave.do" method="post">
	<input type="hidden" name="id" value="${vo.id }">
	<input type="hidden" name="email" value="${vo.email }">
	<div style="margin: 10px">
		<div>
		<label for="nobook">
			<input id="nobook" type="checkbox" name="no_book" value="yes"> 원하는 책이 부족해서
		</label>
		</div>
		<div>
		<label for="nobenefit">
			<input id="nobenefit" type="checkbox" name="no_benefit" value="yes"> 원하는 혜택이 부족해서
		</label>
		</div>
		<div>
		<label for="systemerror">
			<input id="systemerror" type="checkbox" name="system_error" value="yes"> 시스템 오류가 잦아서
		</label>
		</div>
		<div>
		<label for="lateresponse">
			<input id="lateresponse" type="checkbox" name="late_response" value="yes"> 불만, 불편 사항에 대한 응대가 늦어서
		</label>
		</div>
		<div>
		<label for="nouse">
			<input id="nouse" type="checkbox" name="no_use" value="yes"> 자주 사용하지 않아서
		</label>
		</div>
		<div>
		<label for="concernedps">
			<input id="concernedps" type="checkbox" name="concerned_ps" value="yes"> 개인 정보 및 보안이 우려되어서
		</label>
		</div>
		<div>
		<label for="etc_">
			<input id="etc_" type="checkbox" name="etc" value="yes"> 기타
		</label>
		</div>
		<div>
		<label for="pw_">
			비밀번호 입력<input id="pw_" type="password" name="pw" placeholder="비밀번호입력">
		</label>
		</div>
		<div>
		<label for="withdrawalagree">
			<input id="withdrawalagree" type="checkbox" name="withdrawal_agree" value="agree"> 위 내용을 이해했으며, 모두 동의합니다.
		</label>
		</div>
	</div>
	<input type="submit" value="탈퇴버튼">
</form>
</body>
</html>