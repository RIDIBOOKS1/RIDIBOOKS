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
import kr.ridibooks.controller.MemberSignUpPageController;
import kr.ridibooks.controller.TestController;
import kr.ridibooks.controller.TestFindIDController;
import kr.ridibooks.controller.TestLoginPageController;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  mappings=new HashMap<String, Controller>();
	  mappings.put("/account/signuppage.do", new MemberSignUpPageController());
	  mappings.put("/account/find-idpage.do", new MemberFindByIdPageController());
	  mappings.put("/account/loginpage.do", new TestLoginPageController());
	  mappings.put("/account/signup.do", new MemberInsertController());
	  mappings.put("/account/login.do", new MemberLoginController());
	  mappings.put("/account/logout.do", new MemberLogoutController());
	  mappings.put("/account/find-id.do", new MemberFindByIdController());
	  mappings.put("/account/find-password.do", new MemberFindPasswordController());
	  mappings.put("/account/leave.do", new MemberDeleteController());
	  mappings.put("/success.do", new TestController());
	  mappings.put("/findidsuccess.do", new TestFindIDController());
	  
	  
  }
  public Controller getController(String key) { 
	  return mappings.get(key);
  }
}

