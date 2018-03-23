package dictionary.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dictionary.model.dao.WordDAO;
import common.action.*;

public class WordSearchAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		WordDAO worddao = new WordDAO();

		String search = "";
		List wordlist = new ArrayList();
		
		System.out.println(request.getParameter("searchword"));
		if (request.getParameter("searchword") != null) {
			search = request.getParameter("searchword");
			System.out.println("<WordSearchAction>검색값 받음");
			System.out.println("<WordSearchAction>search=" + search);
		}

		wordlist = (List) worddao.getSearch(search);

		System.out.println("<WordSearchAction>검색 값 얻음");
		request.setAttribute("wordlist", wordlist);
		forward.setRedirect(false);
		forward.setPath("2_dictionary/search_result.jsp");
		return forward;
	}

}