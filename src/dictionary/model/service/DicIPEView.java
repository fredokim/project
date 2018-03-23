package dictionary.model.service;

import common.action.*;
import dictionary.model.dao.dictionaryDAO;
import dictionary.model.vo.dictionaryBean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DicIPEView implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		dictionaryDAO dicdao = new dictionaryDAO();
		List<dictionaryBean> dicdatalist = new ArrayList<dictionaryBean>();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		int listcount = dicdao.getListCount("word_ipe");	//총 리스트 수를 받아옴.
		System.out.println("총 리스트 수 = " + listcount);
		
		dicdatalist = dicdao.getdicList(page,limit,"word_ipe");	//리스트를 받아
		
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
		
		request.setAttribute("dicdatalist", dicdatalist);
		forward.setRedirect(false);
		forward.setPath("./2_dictionary/DicIPE.jsp");
		
		
		
		return forward;
	}

}
