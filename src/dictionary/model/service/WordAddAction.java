package dictionary.model.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dictionary.model.dao.WordDAO;
import dictionary.model.vo.*;
import common.action.*;

public class WordAddAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//세션에서 아이디 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		//인서트할 단어 이름
		String wordname = "";
		//인서트 성공 또는 실패 결과를 가져올 변수
		boolean insertResult = false;
		//카테고리 값 구할 변수
		int categoryNum=0;
		
		//객체 생성
		ActionForward forward = new ActionForward();
		WordDAO worddao = new WordDAO();
		List wordlist = new ArrayList();
		List wordmemberlist = new ArrayList();
		
		
		//hyunsun.js 쿼리스트링으로 넘긴 파라미터 값(단어 이름)을 얻어온다.
		if (request.getParameter("wordname") != null) {
			wordname = request.getParameter("wordname");
			System.out.println("단어이름 wordname=" + wordname);
		}
		
		//가져온 단어이름을 검색해서 wordlist에 넣음
		wordlist = (List) worddao.getSearch(wordname);
		
		
		//검색한 단어의 번호를 Bean에 넣어서 update
		WordBean wl = (WordBean)wordlist.get(0);
		categoryNum = Integer.parseInt(String.valueOf(wl.getNumber()).substring(0, 1));
		
		System.out.println("카테고리 넘버는 : " + categoryNum);
		
		id="yypp";
		//내 단어장 목록을 불러오기 위해 wordmemberlist에 db값 저장
		wordmemberlist = (List)worddao.getMynote(id);
		
		//내 단어장에 추가 성공시 true 실패시 false반환하는 DAO 메소드
		insertResult = worddao.updateMynote(categoryNum, id, wl.getNumber(), wordmemberlist);
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(!insertResult) {
			//return값 false일 경우 alert띄우고 전페이지로 돌아가기
			out.println("<script>"
					  + "alert('이미 있는 단어입니다.');"
					  + "history.go(-1);"
					  + "</script>");
			out.flush();
			
			System.out.println("내 단어장 추가(업데이트) 실패");
			return null;
		} else {
			
			out.println("<script>"
					  + "if(confirm('내 단어장에 등록되었습니다. 단어장으로 이동하시겠습니까?')){"
					  + "location.href='MynoteIPE.no'"
					  + "} else {"
					  + "history.go(-1);}"
					  + "</script>");
			
			out.flush();
			
			System.out.println("내 단어장 추가(업데이트) 성공");
			return null;
			
		}
		
		
		
		
	}

}