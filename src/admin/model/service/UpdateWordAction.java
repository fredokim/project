package admin.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.dao.WordDAO;
import admin.model.vo.WordBean;
import common.action.Action;
import common.action.ActionForward;

public class UpdateWordAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WordBean wordbean = new WordBean();
		WordDAO worddao = new WordDAO();
		ActionForward forward = new ActionForward();

		boolean result = false;

		wordbean.setCategory(request.getParameter("category"));
		wordbean.setWord_num(Integer.parseInt(request.getParameter("word_num")));
		wordbean.setWord_name(request.getParameter("word_name"));
		wordbean.setWord_info(request.getParameter("word_info"));

		result = worddao.updateWord(wordbean);

		if (result == false) {
			System.out.println("단어 수정 실패");
			return null;
		}

		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('단어 수정 성공')");
			out.println("window.open('about:blank','_self').self.close();\n");
			out.println("</script>");
			out.close();
		}

		return null;
	}

}
