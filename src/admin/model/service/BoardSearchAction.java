package admin.model.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.*;
import admin.model.dao.BoardDAO;

public class BoardSearchAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		String condition = "";

		condition = request.getParameter("search_condition");

		String search = "";
		List searchlist = new ArrayList();

		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
			System.out.println("검색값 받음");
			System.out.println("search=" + search);
		}

		searchlist = (List) boarddao.getSearch(search, condition);

		System.out.println("검색값 받음");
		request.setAttribute("searchlist", searchlist);
		forward.setRedirect(false);
		forward.setPath("/7_admin/Board/free_board_search.jsp");

		return forward;
	}

}
