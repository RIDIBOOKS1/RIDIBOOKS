package kr.ridibooks.frontcontroller;

import java.util.HashMap;
import kr.ridibooks.controller.Controller;
import kr.ridibooks.controller.MemberInsertController;
import kr.ridibooks.controller.MemberLoginController;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  mappings=new HashMap<String, Controller>();
	  mappings.put("/account/signup.do", new MemberInsertController());
	  mappings.put("/account/login.do", new MemberLoginController());
	 
	  
  }
  public Controller getController(String key) { 
	  return mappings.get(key);
  }
}

