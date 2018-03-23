package mynote.model.services;

import common.action.*;
import mynote.model.dao.mynoteDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class seldelAction implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		System.out.println("--------mynote seldelAction뷰 접속  성공-------");
		
		mynoteDAO mynote = new mynoteDAO();
		
		String wordnum = request.getParameter("wordnum");
		String category = request.getParameter("category");
		System.out.println("wordnum = " + wordnum + " category = " + category);
		
		
		mynote.sel_delete(category, id, wordnum);	//매개 변수 = 테이블이름, 회원번호, 선택된 단어 번호 => 선택된 단어를 삭제한다.
		
		
		forward.setRedirect(true);		
		
		if(category.equals("word_ipe")) {
			forward.setPath("./MynoteIPE.no");
		}else if(category.equals("word_linux")) {
			forward.setPath("./MynoteLinux.no");
		}else if(category.equals("word_sql")) {
			forward.setPath("./MynoteSQL.no");
		}else if(category.equals("word_etc")) {
			forward.setPath("./MynoteETC.no");
		}
		
		
		System.out.println("--------mynote seldelAction뷰 끝!!!!!!!!!!-------");
		return forward;
	}

}
