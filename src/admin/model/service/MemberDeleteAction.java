package admin.model.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.dao.MemberDAO;
import common.action.Action;
import common.action.ActionForward;

public class MemberDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();

		boolean result = false;

		request.setCharacterEncoding("UTF-8");
		String deleteId = request.getParameter("mem_id");
		result = memberdao.deleteMember(deleteId);

		if (result == false) {
			System.out.println("회원 삭제 실패");
			return null;

		}

		forward.setRedirect(true);
		forward.setPath("./manageMem.ad");

		return forward;
	}

}
