package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.CategoryWord;
import admin.model.service.CommentAddAction;
import admin.model.service.CommentDeleteAction;
import admin.model.service.InsertWordAction;
import admin.model.service.MemberList;
import admin.model.service.MemberModfiyAction;
import admin.model.service.MemberModifyViewAction;
import admin.model.service.MemberViewAction;
import admin.model.service.UpdateWordAction;
import admin.model.service.WordDeleteAction;
import admin.model.service.WordSearchAction;
import admin.model.service.MemUpdate;
import admin.model.service.MemberDeleteAction;

import common.action.Action;
import common.action.ActionForward;
import admin.model.service.BoardDeleteAction;
import admin.model.service.BoardDeleteAction2;
import admin.model.service.BoardDetailAction;
import admin.model.service.BoardDetailAction2;
import admin.model.service.BoardListAction;
import admin.model.service.BoardListAction2;
import admin.model.service.BoardReplyAction;
import admin.model.service.BoardReplyAction2;
import admin.model.service.BoardReplyView;
import admin.model.service.BoardReplyView2;
import admin.model.service.BoardSearchAction;
import admin.model.service.BoardWriteAction;
import admin.model.service.BoardWriteAction2;

@WebServlet("*.ad")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
		// 예) http://localhost:8088/JspProject/BoardList.ad인 경우
		// "/JspProject/BoardList.ad"로 반환됩니다.

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "/JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
		// 마지막 위치의 문자까지 추출합니다.
		// command는 "/BoardList.ad"반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// 초기화
		ActionForward forward = null; // forward는 값을 넘겨주며 처리를 시킬 페이지를 불러온다
		Action action = null;

		if (command.equals("/Homepage.ad")) { // 홈페이지로 넘어가는 command 처리
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("1_main/main.jsp");
		}

		else if (command.equals("/login.ad")) { // 로그인 페이지로 넘어가는 command 처리
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/6_member/login.jsp");
		}

		else if (command.equals("/joinForm.ad")) { // 회원가입 페이지로 넘어가는 command 처리
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/6_member/joinForm.jsp");
		}

		// 필요
		else if (command.equals("/word.ad")) { // 단어장 페이지로 넘어가는 command 처리
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/7_admin/Word/word.jsp");
		}
		// 필요
		else if (command.equals("/BoardList2.ad")) { // 자유게시판 들어가기
			action = new BoardListAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/BoardDetailAction2.ad")) { // 자유게시판 글 세부 들어가기
			action = new BoardDetailAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/BoardDetailAction.ad")) { // 자유게시판 들어가기
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyView2.ad")) { // 자유게시판 답글보기
			action = new BoardReplyView2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyView.ad")) { // 문의게시판 답글보기
			action = new BoardReplyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyAction.ad")) { // 문의 게시판 답글달기
			action = new BoardReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyAction2.ad")) { // 자유게시판 답글달기
			action = new BoardReplyAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommentWrite.ad")) { // 자유게시판 답글달기
			action = new CommentAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommentDelete.ad")) { // 자유게시판 답글삭제
			action = new CommentDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 필요
		else if (command.equals("/BoardList.ad")) { // 문의게시판 리스트
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/manageWord.ad")) { // "관리자" 단어관리 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/7_admin/manageWord.jsp");
		}

		else if (command.equals("/manageMem.ad")) { // "관리자" 회원관리 페이지
			action = new MemberList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/manageBoard.ad")) { // "관리자" 게시판 관리 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/7_admin/manageBoard.jsp");
		}

		else if (command.equals("/insertWordPage.ad")) { // 추가할 단어 띄우기
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/7_admin/insertWord.jsp");
		}

		else if (command.equals("/insertWordDB.ad")) { // 단어 추가 버튼 클릭하면 DB에 단어 추가
			action = new InsertWordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/updateWordPage.ad")) { // 단어 수정 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/7_admin/updateWord.jsp");
		}

		else if (command.equals("/updateWordDB.ad")) { // 단어 수정 버튼 클릭하면 DB에 단어 수정
			action = new UpdateWordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/categoryWord.ad")) { // 카테고리별 jsp 뷰를 줄 커맨드
			action = new CategoryWord();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/memUpdate.ad")) { // 관리자의 회원 정보 수정
			action = new MemUpdate();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/WordSearch.ad")) { // 관리자의 단어 검색
			action = new WordSearchAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/DeleteMember.ad")) { // 관리자의 회원 정보 삭제
			action = new MemberDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/deleteWord.ad")) { // 관리자의 단어 삭제
			action = new WordDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/MemberViewAction.ad")) { // 관리자의 멤버 정보 보기
			action = new MemberViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/MemberModifyAction.ad")) {// 관리자의 멤버 수정
			action = new MemberModfiyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberModifyView.ad")) {// 관리자의 수정 멤버 정보 보기
			action = new MemberModifyViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDeleteAction.ad")) {// 관리자의 문의게시판 보드 삭제
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDeleteAction2.ad")) {// 관리자의 자유게시판 보드 삭제
			action = new BoardDeleteAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/SearchBoard.ad")) {// 자유게시판 검색
			action = new BoardSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardAddAction2.ad")) {// 자유게시판 글쓰
			action = new BoardWriteAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardAddAction.ad")) {// 문의게시판 글쓰기
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/BoardWrite.ad")) {// 문의게시판 글쓰기 페이지
			forward = new ActionForward();
			forward.setRedirect(false);// 포워딩 방식으로 주소가 바뀌지 않아요
			forward.setPath("7_admin/Board/qna_board_write.jsp");
		}else if (command.equals("/BoardWrite2.ad")) {// 자유게시판 글쓰기 페이지
			forward = new ActionForward();
			forward.setRedirect(false);// 포워딩 방식으로 주소가 바뀌지 않아요
			forward.setPath("7_admin/Board/free_board_write.jsp");
		}

		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else { // 포워딩됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

	public AdminFrontController() {
		super();
	}

	// doProcess(request,response)메서드를 구현하야여 요청이 GET방식으로 전송되어 오든
	// POST방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
