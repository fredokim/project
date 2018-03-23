package dictionary.model.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dictionary.model.dao.WordDAO;
import common.action.*;


public class WordListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();

		WordDAO worddao = new WordDAO();
		List wordlist = new ArrayList();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listcount = worddao.getListCount();// 총 리스트 수를 받아 옴
		System.out.println("listcount= " + listcount);
		wordlist = worddao.getWordList(page, limit); // 리스트를 받아 옴

		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;

		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		request.setAttribute("page", page); // 현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수
		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount", listcount); // 글 수
		request.setAttribute("wordlist", wordlist);

		forward.setRedirect(false);
		forward.setPath("/_34.Model2/word_list.jsp");
		return forward;
	}
}