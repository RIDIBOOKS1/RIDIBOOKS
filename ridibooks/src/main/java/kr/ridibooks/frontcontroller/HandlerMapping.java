package kr.ridibooks.frontcontroller;

import java.util.HashMap;
import kr.ridibooks.controller.Controller;
import kr.ridibooks.controller.MemberInsertController;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  mappings=new HashMap<String, Controller>();
	  mappings.put("/account/signup.do", new MemberInsertController());
	 
	  
  }
  public Controller getController(String key) { // key=>/memberList.do
	  return mappings.get(key);
  }
}

