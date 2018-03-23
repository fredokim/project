package dictionary.model.service;

import common.action.*;
import dictionary.model.dao.dictionaryDAO;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class selintertAction implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("\n---------------- 단어 추가 액션 접속  성공-----------------");
		
		
		dictionaryDAO dicnote = new dictionaryDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String wordnum = request.getParameter("wordnum");
		String category = request.getParameter("category");
		System.out.println("wordnum = " + wordnum + " category = " + category);
		
		
		//매개 변수 = 테이블이름, 회원번호, 선택된 단어 번호 => 선택된 단어를 추가한다. 
		boolean over = dicnote.insert_word(category, id, wordnum);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(over) {
			out.println("<script>");
			out.println("alert('내 단어장에 추가되었습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			return null;
		}else {
			System.out.println("중복된 단어이기 때문에 실패 !!");
			out.println("<script>");
			out.println("alert('중복된 단어입니다');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			return null;
		}
		//true = 단어추가가능     false = 중복되어 추가 불가능
		
		
//		diclist = dicnote.getWords(category);	//매개변수 = 테이블이름, 회원번호 => 테이블 리스트들을 가져온다
//		
//		
//		request.setAttribute("dicdatalist", diclist);
//		forward.setRedirect(false);		
//		
//		if(category.equals("word_ipe")) {
//			forward.setPath("./2_dictionary/DicIPE.jsp");
//		}else if(category.equals("word_linux")) {
//			forward.setPath("./2_dictionary/DicLinux.jsp");
//		}else if(category.equals("word_sql")) {
//			forward.setPath("./2_dictionary/DicSQL.jsp");
//		}else if(category.equals("word_etc")) {
//			forward.setPath("./2_dictionary/DicETC.jsp");
//		}
//		
//		
//		System.out.println("----------------- 단어 추가 액션  끝!!!!!!!!!!--------------\n");
//		return forward;
	}

}
