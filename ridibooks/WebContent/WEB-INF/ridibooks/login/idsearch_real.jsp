<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기 - 리디북스</title>
    <link rel="stylesheet" href="css/idsearch.css">
    <script type="text/javascript">
	    function add(){
	    	document.form1.action="<c:url value='/account/find-id.do'/>"; 
	    	document.form1.submit();
	     }
    </script>
   
</head>
<body>
    
    <header>
        <h3><a href="#"> RIDIBOOKS</a></h3>
    </header>

    <div class="center-box">
        <section>
            <p class="top">아이디 찾기</p>
        </section>
       
        <form id="form1" name="form1" method="post">
            <input type="email" name="email" id="email" placeholder="이메일주소">

        </form>

        <section>
            <p class="search"><a href="/ridibooks/account/find-id.do" onclick="add()">찾기</a></p>
        </section>

        <section class="view">
            <p>이메일이 기억나지 않으세요?</p>
            <a href="#" class="clear">해결방법 보러가기 ＞ </a>
        </section>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script>

        let customer = $("#email").val();

        $.ajax({
            url: "<c:url value='/account/find-id.do'/>",
            type: "POST",
            data: "email=" + customer,
            // data : {"id" : id},
            success: function(){
                location.href="http://localhost/ridibooks/login/result.jsp#"
            }



        });


    </script>

</body>
</html>