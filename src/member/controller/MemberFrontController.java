package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.action.*;
import member.model.service.MemberJoinAction;
import member.model.service.MemberLoginAction;
import member.model.service.MemberLogoutAction;
import member.model.service.MemberModifyAction;
import member.model.service.MemberViewAction;

@WebServlet("*.me")
public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	public MemberFrontController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI=" + RequestURI);

		String contextPath = request.getContextPath();
		System.out.println("contextPath=" + contextPath);

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command=" + command);

		ActionForward forward = null;
		Action action = null;

		System.out.println(command);
		if (command.equals("/MemberLogin.me")) {// 멤버 로그인 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("6_member/loginForm.jsp");
		} else if (command.equals("/MemberJoin.me")) {// 회원 가입 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("6_member/joinForm.jsp");
		} else if (command.equals("/MemberLoginAction.me")) {// 멤버 로그인 액션
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberJoinAction.me")) {// 회원 가입 액션
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLogout.me")) {// 로그아웃 액션
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberViewAction.me")) {// 로그아웃 액션
			action = new MemberViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (command.equals("/MemberModifyAction.me")) {// 로그아웃 액션
			action = new MemberModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}