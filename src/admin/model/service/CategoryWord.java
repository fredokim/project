package admin.model.service;

import common.action.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.dao.WordDAO;

public class CategoryWord implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		WordDAO dicdao = new WordDAO();
		List dicdatalist = new ArrayList();
		
		
		int category = Integer.parseInt(request.getParameter("category"));
		
		HttpSession session=request.getSession();
		
		session.setAttribute("category", request.getParameter("category"));
		System.out.println("세션 "+category);
		
		dicdatalist = dicdao.getWords(category);

		if (dicdatalist == null) {
			System.out.println("IPE불러오기 실패");
			return null;
		}
		System.out.println("IPE불러오기 성공!!");

		request.setAttribute("dicdatalist", dicdatalist);
		forward.setRedirect(false);
		// 글 수정 폼 페이지로 이동하기 위해 경로를 설정합

		forward.setPath("/7_admin/categoryWord.jsp");	
		
		
		return forward;
	}

}