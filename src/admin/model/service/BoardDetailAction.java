package admin.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.dao.BoardDAO;
import admin.model.dao.CommentDAO;
import admin.model.vo.BoardBean;
import admin.model.vo.CommentBean;
import common.action.*;

public class BoardDetailAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommentDAO commentdao = new CommentDAO();
		CommentBean commentdata = new CommentBean();
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		int num = Integer.parseInt(request.getParameter("num"));
		boarddata = boarddao.getDetail(num);
		if (boarddata == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");

		List commentlist = new ArrayList();
		commentlist = commentdao.getComentList(num);

		if (commentlist.size() > 0)
			request.setAttribute("commentlist", commentlist);

		request.setAttribute("boarddata", boarddata);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./7_admin/Board/qna_board_view.jsp");
		return forward;

	}
}