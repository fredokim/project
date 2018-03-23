package community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.action.ActionForward;
import community.model.service.BoardAddAction;
import community.model.service.BoardAddAction2;
import community.model.service.BoardDeleteAction;
import community.model.service.BoardDeleteAction2;
import community.model.service.BoardDetailAction;
import community.model.service.BoardDetailAction2;
import community.model.service.BoardListAction;
import community.model.service.BoardListAction2;
import community.model.service.BoardModifyAction;
import community.model.service.BoardModifyAction2;
import community.model.service.BoardModifyView;
import community.model.service.BoardModifyView2;
import community.model.service.BoardReplyAction;
import community.model.service.BoardReplyAction2;
import community.model.service.BoardReplyView;
import community.model.service.BoardReplyView2;
import community.model.service.BoardSearchAction;
import community.model.service.CommentAddAction;
import community.model.service.CommentDeleteAction;
import community.model.service.PointListAction;

@WebServlet("*.bo")
public class BoardFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFrontController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다. 예)
		 * http://localhost:8088/jspProject/BoardList.bo인 경우 "/JspProject/BoardList.bo"를
		 * 반환합ㄴ이다.
		 */

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI=" + RequestURI);

		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "/JspProject" 가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath=" + contextPath);

		// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
		// 마지막 위치의 문자까지 추출합니ㅏㄷ.
		// command 는 "/BoardList.bo" 반환됩니다.

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command=" + command);

		// 초기화
		ActionForward forward = null;
		Action action = null;

		// 문의 게시판
		if (command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);// 포워딩 방식으로 주소가 바뀌지 않아요
			forward.setPath("5_community/qna_board_write.jsp");
		} else if (command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardList.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyView.bo")) {
			action = new BoardReplyView();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyAction.bo")) {
			action = new BoardReplyAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardModify.bo")) {
			action = new BoardModifyView();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardModifyAction.bo")) {
			action = new BoardModifyAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/RankPoint.bo")) {
			action = new PointListAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 자유게시판
		} else if (command.equals("/BoardWrite2.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);// 포워딩 방식으로 주소가 바뀌지 않아요
			forward.setPath("5_community/free_board_write.jsp");
		} else if (command.equals("/BoardAddAction2.bo")) {
			action = new BoardAddAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardList2.bo")) {
			action = new BoardListAction2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDetailAction2.bo")) {
			action = new BoardDetailAction2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyView2.bo")) {
			action = new BoardReplyView2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyAction2.bo")) {
			action = new BoardReplyAction2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardModify2.bo")) {
			action = new BoardModifyView2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardModifyAction2.bo")) {
			action = new BoardModifyAction2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDeleteAction2.bo")) {
			action = new BoardDeleteAction2();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommentWrite.bo")) {
			action = new CommentAddAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/SearchBoard.bo")) {
			action = new BoardSearchAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/CommentDelete.bo")) {
			action = new CommentDeleteAction();
			try {
				forward = action.execute(request, response);// 다형성에 의한 업캐스팅
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {// 리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else {// 포워딩 됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	// doProcess(request,response)메서드를 구현하여 요청이 GET방식이든
	// POST방식으로 전송되어 오든 같은 메서드에서 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
