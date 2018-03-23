package community.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dao.BoardDAO;
import community.model.dao.CommentDAO;
import community.model.vo.BoardBean;
import community.model.vo.CommentBean;
import common.action.*;

public class BoardDetailAction2 implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommentDAO commentdao = new CommentDAO();
		CommentBean commentdata = new CommentBean();
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		int num = Integer.parseInt(request.getParameter("num2"));
		boarddao.setReadCountUpdate(num);
		boarddata = boarddao.getDetail2(num);
		if (boarddata == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");


		List commentlist = new ArrayList();
		commentlist = commentdao.getComentList(num);
		
		if(commentlist.size()>0)
			request.setAttribute("commentlist", commentlist);

		request.setAttribute("boarddata", boarddata);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/5_community/free_board_view.jsp");
		return forward;

	}
}