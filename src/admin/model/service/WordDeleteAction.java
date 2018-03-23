package admin.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.dao.WordDAO;
import common.action.Action;
import common.action.ActionForward;

public class WordDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WordDAO worddao = new WordDAO();
		ActionForward forward = new ActionForward();

		boolean result = false;

		int deletenum = Integer.parseInt(request.getParameter("word_num"));

		String category = request.getParameter("category");
		
		
		System.out.println(deletenum);
		System.out.println(category);
		

		result = worddao.deleteWord(deletenum, category);
		
		
		if (result == false) {
			System.out.println("단어 삭제 실패");
			return null;
		}
		
		System.out.println("단어 삭제 성공");
		

		// 단어 입력 성공
		forward.setRedirect(true);
		forward.setPath("./manageWord.ad");

		return forward;
	}

}
