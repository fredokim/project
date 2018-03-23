package community.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import community.model.dao.BoardDAO;
import community.model.dao.CommentDAO;
import community.model.vo.BoardBean;
import community.model.vo.CommentBean;
import common.action.*;

 public class BoardDetailAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{ 
		request.setCharacterEncoding("UTF-8");
   		
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean boarddata=new BoardBean();
		int num=Integer.parseInt(request.getParameter("num"));
		boarddao.setReadCountUpdate(num);
	   	boarddata=boarddao.getDetail(num);
	   	
	   	if(boarddata==null){
	   		System.out.println("상세보기 실패");
	   		return null;
	   	}
	   	System.out.println("상세보기 성공");
	   	
	   	request.setAttribute("boarddata", boarddata);
	   	
	   	
	   	ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("/5_community/qna_board_view.jsp");
   		return forward;

	 }
}