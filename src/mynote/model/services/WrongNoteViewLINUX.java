package mynote.model.services;

import common.action.*;
import mynote.model.dao.mynoteDAO;
import mynote.model.vo.mynoteBean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WrongNoteViewLINUX implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		mynoteDAO mynote = new mynoteDAO();
		List<mynoteBean> mylist = new ArrayList<mynoteBean>();
		
		mylist = mynote.getwrongnote("word_linux", id);	//매개변수 = 테이블이름, 회원번호 => 오답노트 리스트를 가져온다
		
		if(mylist == null) {
			System.out.println("오답노트뷰 접속 실패");	
			return null;
		}
		System.out.println("오답노트뷰 접속  성공!!");
		
		
		request.setAttribute("mylist", mylist);
		forward.setRedirect(false);
		forward.setPath("./3_mynote/WrongNoteLINUX.jsp");
		
		
		
		return forward;
	}

}
