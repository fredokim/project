package test.model.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.action.ActionForward;
import dictionary.model.vo.*;
import test.model.dao.*;

public class SelTestListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession(false);
		
		//리스트를 담아 테스트 페이지로 보낼 forward
		ActionForward forward = new ActionForward();
		String[] sel_words = request.getParameter("sel_words").split(",");
		String wrongAnswerNum= request.getParameter("wrongAnswerNum");
		
		System.out.println("틀린 단어 번호는? " + wrongAnswerNum);
		
		for (int i = 0; i < sel_words.length; i++) {
			System.out.println(sel_words[i] + " ");
		}
		
		
		String test_num = "0";
		String rightAnswer = request.getParameter("rightAnswer");

		
		//첫번째 문제일 경우
		if(rightAnswer==null) {
			
			List testlist = new ArrayList();
			
			WordBean wordbean = new WordBean();
			TestDAO testdao = new TestDAO();
			
			
			//해당 페이지의 단어를 wordlist에 전부 담아옴
			testlist = (List)testdao.selTestlist(sel_words);
			
			session.setAttribute("testlist", testlist);
			
		//두번째 문제 부터	
		} else {
			test_num = request.getParameter("test_num");
			
			System.out.println("SelTestList 현재 문제번호는 : " + test_num );
			System.out.println("SelTestList 현재 맞은 개수는 : " + rightAnswer );
			
			TestDAO testdao = new TestDAO();
			
			List testlist = (List)session.getAttribute("testlist");
			String id = (String)session.getAttribute("id");
			
			
		
			
			if (id != null) {
				
				if(!wrongAnswerNum.equals("")) {
					boolean upMywanResult = testdao.updateMywan(wrongAnswerNum, id);
					if(upMywanResult) {
						System.out.println("오답노트 추가 성공");
					}else {
						System.out.println("오답노트 추가 실패");
					}
					
				}
			
			if (Integer.parseInt(test_num) == 20) {
					boolean result = testdao.updateRank(Integer.parseInt(rightAnswer), id);

					if (result == false) {
						System.out.println("랭크포인트 추가 실패");
					} else {
						System.out.println("랭크포인트 추가 성공");
					}
				}
			}
			
			request.setAttribute("rightAnswer", rightAnswer);
		
		}
		
		
		request.setAttribute("test_num", test_num);
		forward.setRedirect(false);
		forward.setPath("4_test/alltest.jsp");
		return forward;
	}
	

}
