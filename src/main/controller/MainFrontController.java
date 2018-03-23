package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.*;

@WebServlet("*.ma")
public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainFrontController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) 
    					   throws ServletException, IOException {
    	
    	String RequestURI=request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	
    	String contextPath=request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	
    	String command=RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);
    	
    	ActionForward forward=null;
    	Action action=null;
    	
    	if(command.equals("/main.ma")) {
    		forward=new ActionForward();
    		forward.setRedirect(false); 
    		forward.setPath("./1_main/main.jsp");
    	} else if(command.equals("/AdminMain.ma")) {
    		forward=new ActionForward();
    		forward.setRedirect(false); 
    		forward.setPath("./1_main/admin_main.jsp");
    	} 
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { 
    			response.sendRedirect(forward.getPath());
    		} 
 {
    			RequestDispatcher dispatcher = 
    				request.getRequestDispatcher(forward.getPath());
    				dispatcher.forward(request, response);
    		}
    	}
    }
    
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
					  	 throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						  throws ServletException, IOException {
		doProcess(request, response);
	}

}
