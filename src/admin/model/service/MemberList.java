package admin.model.service;

import common.action.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.dao.MemberDAO;

public class MemberList implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		
		MemberDAO memdao = new MemberDAO();
		List memberList = new ArrayList();
		
		memberList = memdao.getMember();

		if (memberList == null) {
			System.out.println("IPE불러오기 실패");
			return null;
		}
		System.out.println("IPE불러오기 성공!!");

		request.setAttribute("memberList", memberList);
		forward.setRedirect(false);
		// 글 수정 폼 페이지로 이동하기 위해 경로를 설정합

		forward.setPath("/7_admin/manageMem.jsp");	
		
		
		return forward;
	}

}