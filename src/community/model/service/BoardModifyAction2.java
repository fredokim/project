package community.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import community.model.dao.BoardDAO;
import community.model.vo.BoardBean;
import common.action.*;

public class BoardModifyAction2 implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		boolean result = false;

		int num = Integer.parseInt(request.getParameter("board_num2"));

		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		boolean usercheck = boarddao.isBoardWriter(num, request.getParameter("board_id2"));
		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF_8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href='./BoardList2.bo';");
			out.println("</script>");
			out.close();
			return null;
		}

		try {
			boarddata.setBoard_num(num);
			boarddata.setBoard_subject(request.getParameter("board_subject2"));
			boarddata.setBoard_content(request.getParameter("board_content2"));

			result = boarddao.boardModify2(boarddata);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");

			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction2.bo?num2=" + boarddata.getBoard_num());
			return forward;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return forward;
	}
}