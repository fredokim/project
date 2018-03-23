package mynote.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.*;
import mynote.model.services.*;

@WebServlet("*.no")
 public class MynoteFrontController 
 	extends javax.servlet.http.HttpServlet 
 	implements javax.servlet.Servlet {
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 String RequestURI=request.getRequestURI();
		 String contextPath=request.getContextPath();
		 String command=RequestURI.substring(contextPath.length());
		 ActionForward forward=null;
		 Action action=null;
		
//		 System.out.println("RequestURI = " + RequestURI);
//		 System.out.println("contextPath = " + contextPath);
//		 System.out.println("command = " + command);
		   
		   if(command.equals("/Main.no")){
			   forward=new ActionForward();
			   HttpSession session = request.getSession();
			   session.setAttribute("id", "6");
			   forward.setRedirect(false);
			   forward.setPath("./3_mynote/Main.jsp");
		   }else if(command.equals("/MynoteIPE.no")){
			   action = new MynoteIPEView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/MynoteSQL.no")){
			   action = new MynoteSQLView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/MynoteLinux.no")){
			   action = new MynoteLinuxView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/MynoteETC.no")){
			   action = new MynoteEtcView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/seldelAction.no")){
			   action = new seldelAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/alldelAction.no")){
			   action = new alldelAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/WrongNoteView.no")){
			   action = new WrongNoteView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/WrongNoteViewSql.no")){
			   action = new WrongNoteViewSql();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/WrongNoteViewLinux.no")){
			   action = new WrongNoteViewLINUX();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/WrongNoteViewETC.no")){
			   action = new WrongNoteViewETC();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/WrongdelAction.no")){
			   action = new wrongdelAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/wrongallAction.no")){
			   action = new wrongallAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }
		   
		   
		  
		  
		   if(forward != null){
		   if(forward.isRedirect()){
			   response.sendRedirect(forward.getPath());
		   }else{
			   RequestDispatcher dispatcher=
				   request.getRequestDispatcher(forward.getPath());
			   dispatcher.forward(request, response);
		   }
		   }
	 }
	private void setAttribute(String string, String string2) {
		// TODO Auto-generated method stub
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}