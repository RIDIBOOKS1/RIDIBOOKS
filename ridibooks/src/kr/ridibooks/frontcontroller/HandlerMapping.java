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
	  // <������>
	  // ȸ�� ���� ������ �̵�
	  mappings.put("/account/signuppage.do", new MemberSignUpPageController());
	  
	  // �α��� ������ �̵�(�׽�Ʈ ������)
	  mappings.put("/account/loginpage.do", new TestLoginPageController());
	  
	  // ��й�ȣ ���� ���̵� �̸��� �Է��Ͽ� ���� �Է��ϴ� ������
	  mappings.put("/account/findpw.do", new TestFindPasswordController());
	  
	  // �̸��� �Է��Ͽ� ���̵� ã�� ������ �̵�
	  mappings.put("/account/find-idpage.do", new MemberFindByIdPageController());
	  
	  // ȸ������ ����(��й�ȣ) �׽�Ʈ ������
	  mappings.put("/account/modifyPwpage.do", new TestModifyPwPageController());
	  
	  // �������� �� �̵��ϴ� �׽�Ʈ ������
	  mappings.put("/success.do", new TestController());
		  
	  // ���̵� ã�� �������� �� �̵��ϴ� �׽�Ʈ ������
	  mappings.put("/findidsuccess.do", new TestFindIDController());
	  
	  // ���� ������ ���� ��й�ȣ�� �Է��ϴ� ������
	  mappings.put("/account/modifypage.do", new TestModifyController());
	  
	  // ȸ������ ����(�̸���) �׽�Ʈ ������
	  mappings.put("/account/modifyEmailpage.do", new TestModifyEmailPageController());
	  
	  
	  // <���� ��Ʈ�ѷ�>
	  
	  // ȸ������ ���� ��Ʈ�ѷ�
	  mappings.put("/account/signup.do", new MemberInsertController());
	  
	  // �α��� ���� ��Ʈ�ѷ�
	  mappings.put("/account/login.do", new MemberLoginController());
	  
	  // �α׾ƿ� ���� ��Ʈ�ѷ�
	  mappings.put("/account/logout.do", new MemberLogoutController());
	  
	  // ��й�ȣ �𸣴� ��� : ���̵� / �̸����� ������ �� ó���ؼ� �н����� ���� ������ �������ִ� ��Ʈ�ѷ�
	  mappings.put("/account/find-password.do", new MemberFindPasswordController());
	  
	  // ��й�ȣ �𸣴� ��� : ��й�ȣ �����ϴ� ��Ʈ�ѷ�
	  mappings.put("/account/reset-password.do", new MemberResetPasswordController());
	  
	  // ���̵� ã��(�̸��� ������ ������ ���̵� �� ���� �����ִ�) ���� ��Ʈ�ѷ�
	  mappings.put("/account/find-id.do", new MemberFindByIdController());
	  
	  // �̸���, ��й�ȣ, �������� ������ ���� ��й�ȣ �Է��������� ó���ϴ� ��Ʈ�ѷ�
	  mappings.put("/account/modify.do", new MemberModifyController());
	  
	  // ȸ������ ���� -> ��й�ȣ
	  mappings.put("/account/modify/password.do", new MemberModifyPasswordController());
	  
	  // ȸ������ ���� -> �̸���
	  mappings.put("/account/modify/email.do", new MemberModifyEmailController());
	  
	  // ȸ��Ż�� ��Ʈ�ѷ� -> ���� delete�� �ƴ϶� �α� ���� ����� �����ؾ� ��
	  mappings.put("/account/leave.do", new MemberDeleteController());
	  
	  
  }
  public Controller getController(String key) { 
	  return mappings.get(key);
  }
}

