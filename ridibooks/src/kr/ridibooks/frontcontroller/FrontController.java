package kr.ridibooks.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.controller.Controller;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// Ŭ���̾�Ʈ�� � ��û�� �ߴ��� �ľ��ϱ�
		String url=request.getRequestURI();
		//System.out.println(url);		
		String ctx=request.getContextPath();
		//System.out.println(ctx);		
		// ������ ��û�� ����� �������� �ľ�
		String command=url.substring(ctx.length());
		System.out.println(command); // /memberInsert.do
		// ��û�� ���� �б��۾�(if~ else if~)
		Controller controller=null;
		String nextPage=null;
		// �ڵ鷯����->HandlerMapping
	    HandlerMapping mapping=new HandlerMapping();
	    controller=mapping.getController(command);
	    nextPage=controller.requestHandler(request, response);
		// forward, redirect
		if(nextPage!=null) {
			if(nextPage.indexOf("redirect:")!=-1) {
				response.sendRedirect(nextPage.split(":")[1]); // redirect
			}else {
				RequestDispatcher rd=request.getRequestDispatcher(ViewResolver.makeView(nextPage)); // forward
				rd.forward(request, response);
			}
		}		
	}
}
