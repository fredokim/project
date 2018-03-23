package member.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dao.MemberDAO;
import member.model.vo.MemberBean;
import common.action.*;

public class MemberViewAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();

		request.setCharacterEncoding("UTF-8");

		String viewId = (String) session.getAttribute("id");
		System.out.println("보여줄 ID = " + viewId);
		member = memberdao.getDetailMember(viewId);

		if (member == null) {
			System.out.println("회원정보보기실패");
			return null;
		}

		request.setAttribute("member", member);

		forward.setRedirect(false);

		forward.setPath("/6_member/memberView.jsp");

		return forward;
	}
}