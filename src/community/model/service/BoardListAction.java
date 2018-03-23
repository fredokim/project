package community.model.service;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.dao.BoardDAO;
import common.action.*;


 public class BoardListAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionForward forward= new ActionForward();
		HttpSession session=request.getSession();
		
		String id=(String)session.getAttribute("id");
   		if(id==null){
   			response.setContentType("text/html;charset=UTF-8");
   			PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')){;");
	   		out.println("location.href='./MemberLogin.me'; "
	   				  + "} else {"
	   				  + "history.go(-1); }");
	   		out.println("</script>");
	   		out.close();
			return null;
   		}
   		
		BoardDAO boarddao=new BoardDAO();
		List boardlist=new ArrayList();
		
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=boarddao.getListCount(); //총 리스트 수를 받아 옴
		boardlist = boarddao.getBoardList(page,limit); //리스트를 받아 옴
		
		//총 페이지 수
		int maxpage = (listcount+limit-1)/limit;
		
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)		
		int startpage = ((page-1) / 10) * 10 + 1;
		
   		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 -1;
   		
		if (endpage> maxpage) endpage= maxpage;
   		
   		request.setAttribute("page", page);  //현재 페이지 수
   		request.setAttribute("maxpage", maxpage);  //최대 페이지 수
   		request.setAttribute("startpage", startpage);  //현재 페이지에 표시할 첫 페이지 수
   		request.setAttribute("endpage", endpage);  //현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount",listcount);  //글 수
		request.setAttribute("boardlist", boardlist);
		
	   	forward.setRedirect(false);
   		forward.setPath("5_community/qna_board_list.jsp");
   		return forward;
	 }
 }