package kr.ridibooks.frontcontroller;

import java.util.HashMap;
import kr.ridibooks.controller.Controller;
import kr.ridibooks.controller.MemberDeleteController;
import kr.ridibooks.controller.MemberFindPasswordController;
import kr.ridibooks.controller.MemberFindByIdController;
import kr.ridibooks.controller.MemberFindByIdPageController;
import kr.ridibooks.controller.MemberInsertController;
import kr.ridibooks.controller.MemberLoginController;
import kr.ridibooks.controller.MemberLogoutController;
import kr.ridibooks.controller.MemberModifyController;
import kr.ridibooks.controller.MemberModifyEmailController;
import kr.ridibooks.controller.MemberModifyPasswordController;
import kr.ridibooks.controller.MemberResetPasswordController;
import kr.ridibooks.controller.MemberSignUpPageController;
import kr.ridibooks.controller.TestController;
import kr.ridibooks.controller.TestFindIDController;
import kr.ridibooks.controller.TestFindPasswordController;
import kr.ridibooks.controller.TestLoginPageController;
import kr.ridibooks.controller.TestModifyController;
import kr.ridibooks.controller.TestModifyEmailPageController;
import kr.ridibooks.controller.TestModifyPwPageController;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  mappings=new HashMap<String, Controller>();
	  // <페이지>
	  // 회원 가입 페이지 이동
	  mappings.put("/account/signuppage.do", new MemberSignUpPageController());
	  
	  // 로그인 페이지 이동(테스트 페이지)
	  mappings.put("/account/loginpage.do", new TestLoginPageController());
	  
	  // 비밀번호 몰라서 아이디 이메일 입력하여 값을 입력하는 페이지
	  mappings.put("/account/findpw.do", new TestFindPasswordController());
	  
	  // 이메일 입력하여 아이디 찾는 페이지 이동
	  mappings.put("/account/find-idpage.do", new MemberFindByIdPageController());
	  
	  // 회원정보 변경(비밀번호) 테스트 페이지
	  mappings.put("/account/modifyPwpage.do", new TestModifyPwPageController());
	  
	  // 성공했을 떄 이동하는 테스트 페이지
	  mappings.put("/success.do", new TestController());
		  
	  // 아이디 찾기 성공했을 때 이동하는 테스트 페이지
	  mappings.put("/findidsuccess.do", new TestFindIDController());
	  
	  // 정보 변경을 위해 비밀번호를 입력하는 페이지
	  mappings.put("/account/modifypage.do", new TestModifyController());
	  
	  // 회원정보 변경(이메일) 테스트 페이지
	  mappings.put("/account/modifyEmailpage.do", new TestModifyEmailPageController());
	  
	  
	  // <로직 컨트롤러>
	  
	  // 회원가입 로직 컨트롤러
	  mappings.put("/account/signup.do", new MemberInsertController());
	  
	  // 로그인 로직 컨트롤러
	  mappings.put("/account/login.do", new MemberLoginController());
	  
	  // 로그아웃 로직 컨트롤러
	  mappings.put("/account/logout.do", new MemberLogoutController());
	  
	  // 비밀번호 모르는 경우 : 아이디 / 이메일이 들어왔을 때 처리해서 패스워드 리셋 페이지 리턴해주는 컨트롤러
	  mappings.put("/account/find-password.do", new MemberFindPasswordController());
	  
	  // 비밀번호 모르는 경우 : 비밀번호 변경하는 컨트롤러
	  mappings.put("/account/reset-password.do", new MemberResetPasswordController());
	  
	  // 아이디 찾기(이메일 값으로 받으면 아이디 두 글자 보여주는) 로직 컨트롤러
	  mappings.put("/account/find-id.do", new MemberFindByIdController());
	  
	  // 이메일, 비밀번호, 수신정보 변경을 위해 비밀번호 입력페이지를 처리하는 컨트롤러
	  mappings.put("/account/modify.do", new MemberModifyController());
	  
	  // 회원정보 수정 -> 비밀번호
	  mappings.put("/account/modify/password.do", new MemberModifyPasswordController());
	  
	  // 회원정보 수정 -> 이메일
	  mappings.put("/account/modify/email.do", new MemberModifyEmailController());
	  
	  // 회원탈퇴 컨트롤러 -> 추후 delete가 아니라 로그 정보 남기게 수정해야 함
	  mappings.put("/account/leave.do", new MemberDeleteController());
	  
	  
  }
  public Controller getController(String key) { 
	  return mappings.get(key);
  }
}

