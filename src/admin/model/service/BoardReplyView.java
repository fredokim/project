package admin.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import admin.model.dao.BoardDAO;
import admin.model.vo.BoardBean;
import common.action.*;


public class BoardReplyView implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();

		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		int num = Integer.parseInt(request.getParameter("num"));

		boarddata = boarddao.getDetail(num);

		if (boarddata == null) {
			System.out.println("답장 페이지 이동 실패");
			return null;
		}
		System.out.println("답장 페이지 이동 완료");

		request.setAttribute("boarddata", boarddata);

		forward.setRedirect(false);
		forward.setPath("7_admin/Board/qna_board_reply.jsp");
		return forward;
	}
}