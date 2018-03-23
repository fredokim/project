package test.model.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.action.ActionForward;
import dictionary.model.vo.*;
import test.model.dao.*;

public class AllTestListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession(false);
		
		//리스트를 담아 테스트 페이지로 보낼 forward
		ActionForward forward = new ActionForward();
		String test_num = "0";
		String rightAnswer = request.getParameter("rightAnswer");
		String wrongAnswerNum= request.getParameter("wrongAnswerNum");
		
		System.out.println("틀린 단어 번호는? " + wrongAnswerNum);
		System.out.println("맞은 답 개수는 ? "+ rightAnswer);
		
		
		//첫번째 문제일 경우
		if (rightAnswer==null) {
			
			//4가지 카테고리 중 해당하는 파일명 주소 담아오기
			String currentPage = request.getHeader("referer");
			int slashPath = currentPage.indexOf("/", 33)+1;
			currentPage = currentPage.substring(slashPath, currentPage.length()-3);
			System.out.println("현재페이지 in AlltestAction : " + currentPage);
			
			
			//랜덤 단어 20개를 담을 단어 리스트 
			List testlist = new ArrayList();
			
			//전체 단어를 담을 리스트
			List wordlist = new ArrayList();
			WordBean wordbean = new WordBean();
			TestDAO testdao = new TestDAO();
			
			
			//해당 페이지의 단어를 wordlist에 전부 담아옴
			wordlist = (List)testdao.getDictionary(currentPage);
			//랜덤숫자 20개를 중복되지 않도록 뽑음
			int random[] = new int[20];
			System.out.println(wordlist.size());
			for (int i = 0; i < random.length; i++) {
				random[i] = (int)(Math.random()*wordlist.size()-1);
			
				for (int j = 0; j < i; j++) {
					if(random[i] == random[j]) {
						i--;
						break;
					}
				}
			}

			//전체단어 중 랜덤으로 선택된 단어 20개를 testlist에 넣음
			for (int i = 0; i < random.length; i++) {
				testlist.add(wordlist.get(random[i]));
			}
			
			session.setAttribute("testlist", testlist);
			
		//두번째 문제 부터
		} else {
			
			TestDAO testdao = new TestDAO();
			
			System.out.println("AllTestList 현재 문제번호는 : " + test_num );
			System.out.println("AllTestList 현재 맞은 개수는 : " + rightAnswer );
			
			List testlist = (List)session.getAttribute("testlist");
			String id = (String)session.getAttribute("id");
			
			test_num = request.getParameter("test_num");
			
			
			
			if(id!=null) {
				
				if(!wrongAnswerNum.equals("")) {
					boolean upMywanResult = testdao.updateMywan(wrongAnswerNum, id);
					if(upMywanResult) {
						System.out.println("오답노트 추가 성공");
					}else {
						System.out.println("오답노트 추가 실패");
					}
					
				}
				
				if(Integer.parseInt(test_num)==20) {
					boolean result = testdao.updateRank(Integer.parseInt(rightAnswer), id);
					
					if(result==false) {
						System.out.println("랭크포인트 추가 실패");
					}else {
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
