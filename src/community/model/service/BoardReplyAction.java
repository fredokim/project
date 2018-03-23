package community.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dao.BoardDAO;
import community.model.vo.BoardBean;
import common.action.*;

public class BoardReplyAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("UTF-8");
		 	ActionForward forward = new ActionForward();
		 	
			BoardDAO boarddao=new BoardDAO();
	   		BoardBean boarddata=new BoardBean();
	   		int result=0;
	   		
	   		boarddata.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
	   		boarddata.setBoard_id(request.getParameter("board_id"));
	   		boarddata.setBoard_subject(request.getParameter("board_subject"));
	   		boarddata.setBoard_content(request.getParameter("board_content"));
	   		boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
	   		boarddata.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
	   		
	   		result=boarddao.boardReply(boarddata);
	   		if(result==0){
	   			System.out.println("답장 실패");
	   			return null;
	   		}
	   		System.out.println("답장 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardDetailAction.bo?num="+result);
	   		return forward;
	}  	
}