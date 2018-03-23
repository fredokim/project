package dictionary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.action.*;
import dictionary.model.service.*;

@WebServlet("*.di")
public class WordFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public WordFrontController() {
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

		if (command.equals("/WordList.di")) {
			action = new WordListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WordSearch.di")) {
			action = new WordSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WordAdd.di")) {
			action = new WordAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/DicIPE.di")){
			   action = new DicIPEView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/DicSQL.di")){
			   action = new DicSQLView();
			   System.out.println("다시 컨트럴로 옴");
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/DicLinux.di")){
			   action = new DicLinuxView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/DicETC.di")){
			   action = new DicETCView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/selintertAction.di")){
			   action = new selintertAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/allinsertAction.di")){
			   action = new allinsertAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
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