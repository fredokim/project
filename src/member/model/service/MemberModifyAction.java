package member.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dao.MemberDAO;
import member.model.vo.MemberBean;
import common.action.*;

public class MemberModifyAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		boolean result = false;

		int num = Integer.parseInt(request.getParameter("member_num"));
		String id = request.getParameter("member_id");
		MemberDAO memberdao = new MemberDAO();
		MemberBean memberdata = new MemberBean();

		try {
			memberdata.setMember_num(num);
			memberdata.setMember_id(id);
			memberdata.setMember_pw(request.getParameter("member_pw"));
			memberdata.setMember_nickname(request.getParameter("member_nickname"));
			memberdata.setMember_rankpoint(Integer.parseInt(request.getParameter("member_rankpoint")));

			result = memberdao.MemberModify(memberdata);
			if (result == false) {
				System.out.println("멤버정보 수정 실패");
				return null;
			}
			System.out.println("멤버정보 수정 완료");

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('내 정보가 수정 되었습니다.');");
			out.print("history.back();\n");
			out.println("</script>");
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
