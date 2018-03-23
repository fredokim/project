package admin.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.dao.BoardDAO;
import admin.model.vo.BoardBean;
import common.action.*;
;

public class BoardReplyAction2 implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("UTF-8");
		 	ActionForward forward = new ActionForward();
		 	
			BoardDAO boarddao=new BoardDAO();
	   		BoardBean boarddata=new BoardBean();
	   		int result=0;
	   		System.out.println(Integer.parseInt(request.getParameter("board_num2")));
	   		boarddata.setBoard_num(Integer.parseInt(request.getParameter("board_num2")));
	   		boarddata.setBoard_id(request.getParameter("board_id2"));
	   		boarddata.setBoard_subject(request.getParameter("board_subject2"));
	   		boarddata.setBoard_content(request.getParameter("board_content2"));
	   		boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref2")));
	   		boarddata.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev2")));
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq2")));
	   		
	   		result=boarddao.boardReply2(boarddata);
	   		if(result==0){
	   			System.out.println("답장 실패");
	   			return null;
	   		}
	   		System.out.println("답장 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardDetailAction2.ad?num2="+result);
	   		return forward;
	}  	
}