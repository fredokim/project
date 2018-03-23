package community.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.*;
import community.model.dao.CommentDAO;
import community.model.vo.BoardBean;
import community.model.vo.CommentBean;

public class CommentAddAction implements Action {

	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		BoardBean boarddata = new BoardBean();
		CommentDAO commentdao = new CommentDAO();
		CommentBean commentdata = new CommentBean();
		ActionForward forward = new ActionForward();
		
		int num=Integer.parseInt(request.getParameter("comment_board"));
		
		boolean result = false;
		System.out.println("AddAction");
		commentdata.setComment_num(commentdao.getSeq());
		commentdata.setComment_board(num);
		
		commentdata.setComment_id(request.getParameter("comment_id"));
		commentdata.setComment_content(request.getParameter("comment_content"));
		result = commentdao.insertComment(commentdata);
	
		if (result == false) {
			System.out.println("댓글 입력 실패");
			return null;
		}
		forward.setRedirect(false);
		forward.setPath("/BoardDetailAction2.bo?num2="+num);

		return forward;
	}
}
