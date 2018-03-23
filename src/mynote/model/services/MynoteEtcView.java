package mynote.model.services;

import common.action.*;
import mynote.model.dao.mynoteDAO;
import mynote.model.vo.mynoteBean;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MynoteEtcView implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		mynoteDAO mynote = new mynoteDAO();
		List<mynoteBean> mylist = new ArrayList<mynoteBean>();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		
		System.out.println("id = " + id);
		
		if(id == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("if(confirm('로그인을 하셔야 합니다. 로그인 페이지로 이동하시겠습니까?')){;");
			out.println("location.href='./MemberLogin.me'");
			out.println("}else{");
			out.println("history.go(-1); }");
			out.println("</script>");
			out.flush();
			return null;
		}else {
			int page = 1;
			int limit = 10;
			
			if(request.getParameter("page") != null){
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			System.out.println("넘어온 페이지 = " + page);
			
			int listcount = mynote.getCount("word_etc", id);
			System.out.println("총 리스트 수 = " + listcount);
			
			mylist = mynote.getmyList(page,limit,"word_etc",id, listcount);	//리스트를 받아
			
			int maxpage = (listcount+limit-1)/limit;
			System.out.println("총 페이지의 수 = " + maxpage);
			
			int startpage = ((page-1)/10) * 10 + 1;
			System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
			
			//endpage 현재 페이지 그룹에서 보여줄 마지막 페이지 수 ([10],[20],[30])
			int endpage = startpage + 10 -1;
			System.out.println("현제 페이지에 보여줄 마지막 페이지 수 = " + endpage);
			
			
			if(endpage>maxpage) endpage = maxpage;
			request.setAttribute("page", page); //현재 페이지 수
			request.setAttribute("maxpage", maxpage); //최대 페이지 수
			
			//현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);
			
			//현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("endpage", endpage);
			request.setAttribute("listcount", listcount); //총 글의 수
					
			if(mylist == null) {
				System.out.println("mynote IPE뷰 접속 실패");	
				return null;
			}
			System.out.println("mynote IPE뷰 접속  성공!!");
			
			
			request.setAttribute("mylist", mylist);
			forward.setRedirect(false);
			forward.setPath("./3_mynote/MynoteETC.jsp");
			
			return forward;
		}
		
	}

}
