<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기 - 리디북스</title>
    <link rel="stylesheet" href="css/result.css">
</head>
<body>
    
    <header>
        <h3><a href="#"> RIDIBOOKS</a></h3>
    </header>


    <div class="center-box">

        <section>
            <div class="com"><p>회원님의 아이디는 다음과 같습니다.</p></div>
        </section>
        <section>
        	<!-- 추후 아이디 두글자만 노출되게 수정 -->
            <div class="blue"> {id} </div>
        </section>

        <section>
            <div class="text">
                <p>개인 정보 보호를 위해 앞 두글자만 보여드립니다.<br>
                전체 아이디는 입력하신 메일 주소로 안내해드렸습니다.</p>
            </div>
        </section>
        
        <section>
            <p class="search"><a href="#">로그인</a></p>
        </section>

        <section class="view">
            <p>비밀번호를 잃어버리셨나요? <a href="#" class="clear"> 비밀번호 재설정＞ </a></p>
            
        </section>


    </div>

</body>
</html>