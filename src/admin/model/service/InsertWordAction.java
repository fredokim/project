package admin.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.dao.WordDAO;
import admin.model.vo.WordBean;
import common.action.Action;
import common.action.ActionForward;

public class InsertWordAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WordBean wordbean = new WordBean();
		WordDAO worddao = new WordDAO();
		ActionForward forward = new ActionForward();

		boolean result = false;

		wordbean.setCategory(request.getParameter("WordTable"));
		wordbean.setWord_num(Integer.parseInt(request.getParameter("word_num")));
		wordbean.setWord_name(request.getParameter("word_name"));
		wordbean.setWord_info(request.getParameter("word_info"));

		result = worddao.insertWord(wordbean);

		if (result == false) {
			System.out.println("단어입력 실패");
			return null;
		}

		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('단어 입력 성공.');");
			out.print("window.open('about:blank','_self').self.close();\n");
			out.println("</script>");
			out.close();
		}

		// 단어 입력 성공

		return null;
	}

}
