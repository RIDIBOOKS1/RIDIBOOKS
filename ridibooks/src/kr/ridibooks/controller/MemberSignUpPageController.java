package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSignUpPageController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) {
		return "join/join";
	}

}
