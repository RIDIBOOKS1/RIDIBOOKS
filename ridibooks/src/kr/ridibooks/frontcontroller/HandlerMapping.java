package kr.ridibooks.frontcontroller;

import java.util.HashMap;
import kr.ridibooks.controller.Controller;
import kr.ridibooks.controller.MemberFindByIdController;
import kr.ridibooks.controller.MemberInsertController;
import kr.ridibooks.controller.MemberLoginController;
import kr.ridibooks.controller.MemberSignUpController;
import kr.ridibooks.controller.TestController;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  mappings=new HashMap<String, Controller>();
	  mappings.put("/account/signuppage.do", new MemberSignUpController());
	  mappings.put("/account/signup.do", new MemberInsertController());
	  mappings.put("/account/login.do", new MemberLoginController());
	  mappings.put("/account/find-id.do", new MemberFindByIdController());
	  mappings.put("/success.do", new TestController());
	  
  }
  public Controller getController(String key) { 
	  return mappings.get(key);
  }
}

