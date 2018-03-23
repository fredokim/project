package admin.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.dao.MemberDAO;
import admin.model.vo.MemberBean;
import common.action.*;

public class MemberModifyViewAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();

		request.setCharacterEncoding("UTF-8");

		String viewId = request.getParameter("member_id");
		System.out.println("보여줄 ID = " + viewId);
		member = memberdao.getDetailMember(viewId);

		if (member == null) {
			System.out.println("회원정보보기실패");
			return null;
		}

		request.setAttribute("member", member);

		forward.setRedirect(false);

		forward.setPath("/7_admin/memberModify.jsp");

		return forward;
	}
}