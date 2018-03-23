package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.action.*;
import dictionary.model.service.*;
import test.model.service.*;

@WebServlet("*.ts")
public class TestFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public TestFrontController() {
		super();
	}
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI=" + RequestURI);

		String contextPath = request.getContextPath();
		System.out.println("contextPath=" + contextPath);

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command=" + command);

		ActionForward forward = null;
		Action action = null;

		if (command.equals("/AllTest.ts")) {
			action = new AllTestListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/SelTest.ts")) {
			action = new SelTestListAction();
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