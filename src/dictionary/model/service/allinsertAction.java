package dictionary.model.service;

import common.action.*;
import dictionary.model.dao.dictionaryDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class allinsertAction implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		System.out.println("=============== 모두 추가 메소드 진입 성공 ====================");
		dictionaryDAO dicnote = new dictionaryDAO();
		
		String category = request.getParameter("category");
		
		String sel_words[] = request.getParameter("sel_words").split(",");
		
		System.out.println("sel_words =" + sel_words + "  category = " + category);
		
		
		for(int n=0; n<sel_words.length; n++ ) {
			System.out.println(sel_words[n]+" 추가");
			dicnote.insert_word(category, id, sel_words[n]);	//매개 변수 = 테이블이름, 회원번호, 선택된 단어 번호 => 선택된 단어를 추가한다
		}
		
		
		forward.setRedirect(true);
		if(category.equals("word_ipe")) {
			forward.setPath("./DicIPE.di");
		}else if(category.equals("word_linux")) {
			forward.setPath("./DicLinux.di");
		}else if(category.equals("word_sql")) {
			forward.setPath("./DicSQL.di");
		}else if(category.equals("word_etc")) {
			forward.setPath("./DicETC.di");
		}
		
		
		System.out.println("----------------- 단어 모두 추가 액션  끝!!!!!!!!!!--------------\n");
		return forward;
		
		
		
		
		
	}

}
