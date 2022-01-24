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
import kr.ridibooks.controller.MemberModifyPasswordController;
import kr.ridibooks.controller.MemberResetPasswordController;
import kr.ridibooks.controller.MemberSignUpPageController;
import kr.ridibooks.controller.TestController;
import kr.ridibooks.controller.TestFindIDController;
import kr.ridibooks.controller.TestFindPasswordController;
import kr.ridibooks.controller.TestLoginPageController;
import kr.ridibooks.controller.TestModifyController;
import kr.ridibooks.controller.TestModifyPwPageController;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  mappings=new HashMap<String, Controller>();
	  // <������>
	  // ȸ�� ���� ������ �̵�
	  mappings.put("/account/signuppage.do", new MemberSignUpPageController());
	  
	  // �̸��� �Է��Ͽ� ���̵� ã�� ������ �̵�
	  mappings.put("/account/find-idpage.do", new MemberFindByIdPageController());
	  
	  // �α��� ������ �̵�(�׽�Ʈ ������)
	  mappings.put("/account/loginpage.do", new TestLoginPageController());
	  
	  // ��й�ȣ ���� ���̵� �̸��� �Է��Ͽ� ���� �Է��ϴ� ������
	  mappings.put("/account/findpw.do", new TestFindPasswordController());
	  
	  // ���� ������ ���� ��й�ȣ�� �Է��ϴ� ������
	  mappings.put("/account/modifypage.do", new TestModifyController());
	  
	  // ȸ������ ����(��й�ȣ) �׽�Ʈ ������
	  mappings.put("/account/modifyPwpage.do", new TestModifyPwPageController());
	  
	  // <���� ��Ʈ�ѷ�>
	  
	  // ȸ������ ���� ��Ʈ�ѷ�
	  mappings.put("/account/signup.do", new MemberInsertController());
	  
	  // �α��� ���� ��Ʈ�ѷ�
	  mappings.put("/account/login.do", new MemberLoginController());
	  
	  // �α׾ƿ� ���� ��Ʈ�ѷ�
	  mappings.put("/account/logout.do", new MemberLogoutController());
	  
	  // ���̵� ã��(�̸��� ������ ������ ���̵� �� ���� �����ִ�) ���� ��Ʈ�ѷ�
	  mappings.put("/account/find-id.do", new MemberFindByIdController());
	  
	  // ��й�ȣ �𸣴� ��� : ���̵� / �̸����� ������ �� ó���ؼ� �н����� ���� ������ �������ִ� ��Ʈ�ѷ�
	  mappings.put("/account/find-password.do", new MemberFindPasswordController());
	  
	  // ��й�ȣ �𸣴� ��� : ��й�ȣ �����ϴ� ��Ʈ�ѷ�
	  mappings.put("/account/reset-password.do", new MemberResetPasswordController());
	  
	  // �̸���, ��й�ȣ, �������� ������ ���� ��й�ȣ �Է��������� ó���ϴ� ��Ʈ�ѷ�
	  mappings.put("/account/modify.do", new MemberModifyController());
	  
	  // ȸ������ ���� -> ��й�ȣ
	  mappings.put("/account/modify/password.do", new MemberModifyPasswordController());
	  
	  mappings.put("/account/leave.do", new MemberDeleteController());
	  mappings.put("/success.do", new TestController());
	  mappings.put("/findidsuccess.do", new TestFindIDController());
	  
	  
	  
  }
  public Controller getController(String key) { 
	  return mappings.get(key);
  }
}

