package admin.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.dao.WordDAO;
import common.action.Action;
import common.action.ActionForward;

public class WordSearchAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		WordDAO worddao = new WordDAO();
		
		String search="";
		List wordlist = new ArrayList();
		
		if (request.getParameter("검색단어")!=null) {
			search = request.getParameter("검색단어");
			System.out.println("검색값받음");
			System.out.println("검색단어 = " + search);			
		}
		
		wordlist = (List) worddao.getSearch(search);
		
		request.setAttribute("wordlist", wordlist);
		forward.setRedirect(false);
		forward.setPath("/7_admin/searchWord.jsp");
				
		
		
		return forward;
	}

}
