package mynote.model.services;

import common.action.*;
import mynote.model.dao.mynoteDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class wrongallAction implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("=============== 모두 삭제 메소드 진입 성공 ====================");
		mynoteDAO mynote = new mynoteDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		
		String category = request.getParameter("category");
		String sel_words[] = request.getParameter("sel_words").split(",");
		System.out.println("sel_words =" + sel_words + "  category = " + category);
		
		
		for(int n=0; n<sel_words.length; n++ ) {
			System.out.println(sel_words[n] + "삭제");
			mynote.wrongnote_delete(category, id, sel_words[n]);	//매개 변수 = 테이블이름, 회원번호, 선택된 단어 번호 => 선택된 단어를 삭제한다.
		}
		
		
		forward.setRedirect(true);		
		
		
		
		if(category.equals("word_ipe")) {
			forward.setPath("./WrongNoteView.no");
		}else if(category.equals("word_linux")) {
			forward.setPath("./WrongNoteViewLinux.no");
		}else if(category.equals("word_sql")) {
			forward.setPath("./WrongNoteViewSql.no");
		}else if(category.equals("word_etc")) {
			forward.setPath("./WrongNoteViewETC.no");
		}
		
		
		System.out.println("=============== 모두 삭제 메소드 끝!!! ====================");
		return forward;
		
	}

}
