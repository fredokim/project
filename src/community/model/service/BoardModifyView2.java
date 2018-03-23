package community.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import community.model.dao.BoardDAO;
import community.model.vo.BoardBean;
import common.action.*;

public class BoardModifyView2 implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	ActionForward forward = new ActionForward();
		 	request.setCharacterEncoding("UTF-8");
	   		
			BoardDAO boarddao=new BoardDAO();
		   	BoardBean boarddata=new BoardBean();
		   	
			int num=Integer.parseInt(request.getParameter("num2"));
		   	boarddata=boarddao.getDetail2(num);
		   	
		   	if(boarddata==null){
		   		System.out.println("(수정)상세보기 실패");
		   		return null;
		   	}
		   	System.out.println("(수정)상세보기 성공");
		   	
		   	request.setAttribute("boarddata", boarddata);
		   	forward.setRedirect(false);
	   		forward.setPath("5_community/free_board_modify.jsp");
	   		return forward;
	 }
}