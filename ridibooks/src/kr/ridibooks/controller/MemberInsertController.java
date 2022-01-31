package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;
import kr.ridibooks.validator.IdValidator;
import kr.ridibooks.validator.NameValidator;
import kr.ridibooks.validator.PwValidator;

// ȸ������ ���� ��Ʈ�ѷ�
public class MemberInsertController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
		
		// ���� ��ü ����
		MemberServiceImpl service = new MemberServiceImpl();
		
		// �����̷�Ʈ, ������
	    String nextPage=null;
		
		// �Ķ���Ͱ� String ��ü�� ����
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pwCheck");;
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birthdateStr = request.getParameter("birthdate");
		String genderStr = request.getParameter("gender");
		String agreeStr = request.getParameter("agree");
		String eventStr = request.getParameter("event");
		String infoStr = request.getParameter("info");
		String personalStr = request.getParameter("personal");
		
		// �ʼ���(���̵�, �����ȣ, �����ȣ Ȯ��, �̸���, �̸�, �̿�������, �������� ���� �� �̿뵿��)�� null �� �����ڵ� 400
		if(id == null || pw == null || pwCheck == null || email == null || name == null || agreeStr == null || personalStr == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("�ʼ��� �Է� �ȵǼ� ����� ����");
			return null;
		}
		
		// ����� �Է°� ���� ����
		id = id.trim();
		pw = pw.trim();
		pwCheck = pwCheck.trim();
		email = email.trim();
		name = name.trim();
		
		id = id.replace(" ", "");
		pw = pw.replace(" ", "");
		pwCheck = pwCheck.replace(" ", "");
		email = email.replace(" ", "");
		name = name.replace(" ", "");
		
		// �ʼ���(���̵�, �����ȣ, �����ȣ Ȯ��, �̸���, �̸�, �̿�������, �������� ���� �� �̿뵿��)�� ������� �� �����ڵ� 400
		if(id.isEmpty() || pw.isEmpty() || pwCheck.isEmpty() || email.isEmpty() || name.isEmpty() || agreeStr.isEmpty() || personalStr.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("�ʼ��� �Է� �ȵǼ� ����� ����");
			return null;
		}
		
		if(birthdateStr != null) {
			birthdateStr = birthdateStr.trim();
			birthdateStr = birthdateStr.replace(" ", "");
		}
		
		
		int birthdate;
		
		// ����⵵�� ���� ���� �� int �� ��ȯ
		if(birthdateStr == null || birthdateStr.isEmpty()) {
			birthdate = 0;
			
		} else {
			birthdate = Integer.parseInt(birthdateStr);
			
			// birthdate ����⵵ 1920 ~ 2008 ���� ����, 2009 ~ 2022 �����븮�� ���� �ʿ�
			// ���� ���� (1920~2008)�� �����ڵ� 400
			if(birthdate < 1920 || birthdate > 2008) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("����⵵ ����");
				return null;
			}
			
			if(2009 <= birthdate && birthdate <= 2002) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("����⵵ ����");
				return null;
			}
		}
		
		char gender;
		
		// genderStr�� ���� ���� �� char ���� ��ȯ
		if(genderStr == null || genderStr.isEmpty()) {
			gender = '\u0000';
		} else {
			gender = genderStr.charAt(0);
		}
		
		
		int agree;
		int event;
		int info;
		int personal;
		
		// �̿��� ����(�ʼ�)
		agree = 0;
		
		// �̺�Ʈ, ���� �˸� ���� ����(����)
		if(eventStr == null) {
			event = 1;
		} else {
			event = 0;
		}
		
		//����, ���� ���� ��������(����)
		if(infoStr == null) {
			info = 1;
		} else {
			info = 0;
		}
		
		// ���� ���� ���� �� �̿� ����(�ʼ�)
		personal = 0;
		
		
		// ���̵� ��ȿ�� üũ
		if(!new IdValidator().idCheck(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("���̵� ��ȿ�� üũ ����");
			return null;
		}
		
		// ��й�ȣ ��ȿ�� üũ
		if(!new PwValidator().pwCheck(pw)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("��й�ȣ ��ȿ�� üũ ����");
			return null;
		}
		
		// ��й�ȣ.equals(��й�ȣ Ȯ��)�Ѱ�
		if(!pw.equals(pwCheck)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("��й�ȣ ��ġ ����");
			return null;
		}
		
		// �̸��� ��ȿ�� üũ
		if(!new EmailValidator().emailCheck(email)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("�̸��� ��ȿ�� üũ ����");
			return null;
		}
		
		// �̸� ��ȿ�� üũ
		if(!new NameValidator().nameCheck(name)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("�̸� ��ȿ�� üũ ����");
			return null;
		}
		
		// ���̵� �ߺ� üũ
		if(service.idDoublecheck(id)!="NO") {
			response.setStatus(409);
			System.out.println("���̵� �ߺ� üũ ����");
			return null;
		}
		
		// �̸��� �ߺ� üũ
		if(service.emailDoublecheck(email)!="NO") {
			response.setStatus(409);
			System.out.println("�̸��� �ߺ� üũ ����");
			return null;
		}
		
		
		// VO �����ֱ�
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setEmail(email);
		vo.setName(name);
		vo.setBirthdate(birthdate);
		vo.setGender(gender);
		vo.setAgree(agree);
		vo.setEvent(event);
		vo.setInfo(info);
		vo.setPersonal(personal);
		
		
		// insert ������ ��� ��ȯ
	    int cnt=service.register(vo);
	    
	    
	    if(cnt>0) {
	    	// ���Լ���
	    	nextPage="redirect:"+ctx+"/success.do";
	    }else {
	    	// ���Խ���-> ���ܰ�ü�� ���� WAS���� ������
	    	throw new ServletException("not insert");	    	
	    }	
	    
	    //nextPage�� �����̷�Ʈ
		return nextPage;
	}
	
}
