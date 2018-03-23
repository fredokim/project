package admin.model.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.dao.BoardDAO;
import common.action.*;

public class BoardDeleteAction2 implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("num2"));

		BoardDAO boarddao = new BoardDAO();

		result = boarddao.boardDelete2(num);
		if (result == false) {
			System.out.println("게시판 삭제 실패");
			return null;
		}

		System.out.println("게시판 삭제 성공");
		forward.setRedirect(true);
		forward.setPath("./BoardList2.ad");
		return forward;
	}
}