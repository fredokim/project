package admin.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.*;
import admin.model.dao.CommentDAO;

public class CommentDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("comment_num"));
		CommentDAO commentdao = new CommentDAO();

		if (!(id.equals("admin"))) {
			usercheck = commentdao.isCommentWriter(num, id);
			if (usercheck == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제할 권한이 없습니다.');");
				out.println("location.href='./BoardList2.ad';");
				out.println("</script>");
				out.close();
				return null;
			}
			result = commentdao.commetDelete(num);

			if (result == false) {
				System.out.println("댓글 삭제 실패");
				return null;
			}
			System.out.println("댓글 삭제 성공");
			forward.setRedirect(true);
			forward.setPath("./BoardList2.ad");
			return forward;
		} else {
			result = commentdao.commetDelete(num);

			if (result == false) {
				System.out.println("댓글 삭제 실패");
				return null;
			}
			System.out.println("댓글 삭제 성공");
			forward.setRedirect(true);
			forward.setPath("./BoardList2.ad");
			return forward;
		}
	}
}
