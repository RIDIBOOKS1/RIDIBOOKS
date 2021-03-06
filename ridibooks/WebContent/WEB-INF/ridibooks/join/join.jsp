<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <style><%@include file="/WEB-INF/ridibooks/join/css/join.css"%></style>
</head>
<body>
    
    <header>
        <h3><a href="#"> RIDIBOOKS</a></h3>
    </header>

    <div class="center-box">

        <form>
            <input type="id" name="id" id="id" placeholder="아이디">

            <div class="id_input"><img src="images/icons8-high-importance-20.png">아이디를 입력해주세요</div>

            <input type="password" name="pw" id="pw" placeholder="비밀번호">
            <input type="password" name="pwcheck" id="pwcheck" placeholder="비밀번호 확인">
            <div class="pw_input"><img src="images/icons8-high-importance-20.png">비밀번호를 입력해주세요</div>

            <input type="email" name="email" id="email" placeholder="이메일 주소">
            <div class="email_input"><img src="images/icons8-high-importance-20.png" >이메일을 입력해주세요</div>

            <input type="text" name="text" id="text" placeholder="이름">
            <div class="text_input"><img src="images/icons8-high-importance-20.png">이름을 입력해주세요</div>

            <p>선택 입력</p>
            <div class="year">
                <input type="text" name="birth" id="birth" placeholder="출생년도">
                <label>
                    <button type="button" class="female">여</button>
                    <button type="button" class="male">남</button>
                </label>
            </div>
            
            <div class="check-box">
                <div class="button_all"><input type="button" name="all" id="all">선택 포함 전체 약관 동의</div><br>
                <div class="use_all"><input type="button" name="use" id="use">이용약관 동의(필수) <span>약관 보기＞ </span></div><br>
                <div class="event_all"><input type="button" name="event" id="event">이벤트,혜택 알림 수신 동의(선택)</div><br>
                <div class="gender_all"> <input type="button" name="gender" id="gender">성별,생년 정보 제공 동의(선택) <span>내용 확인＞</span></div><br>
                <div class="essential_all"><input type="button" name="essential" id="essential">개인 정보 수집 및 이용 동의(필수) <span>내용 확인 ＞</span></div>
            </div>
            
        </form>


        <section>
            <p class="join"><a href="#">회원 가입 완료</a></p>
        </section>


    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script>
        $(".join").on("click",function(){
            $(".id_input").css("display", "block");
            $(".pw_input").css("display", "block");
            $(".email_input").css("display", "block");
            $(".text_input").css("display", "block");
            $(".gender_input").css("display", "block");
        });

    </script>
</body>
</html>