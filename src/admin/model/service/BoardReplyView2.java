package admin.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import admin.model.dao.BoardDAO;
import admin.model.vo.BoardBean;
import common.action.*;


public class BoardReplyView2 implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();

		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		int num = Integer.parseInt(request.getParameter("num2"));

		boarddata = boarddao.getDetail2(num);

		if (boarddata == null) {
			System.out.println("답장 페이지 이동 실패");
			return null;
		}
		System.out.println("답장 페이지 이동 완료");

		request.setAttribute("boarddata", boarddata);

		forward.setRedirect(false);
		forward.setPath("7_admin/Board/free_board_reply.jsp");
		return forward;
	}
}