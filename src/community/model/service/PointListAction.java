package community.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.action.*;
import member.model.dao.MemberDAO;
import member.model.vo.MemberBean;
public class PointListAction implements Action{

	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
	 	ActionForward forward = new ActionForward();
		MemberDAO memberdao=new MemberDAO();
   		List<MemberBean> memberlist=new ArrayList<MemberBean>();
   		int listcount=memberdao.getListCount();
   		memberlist=memberdao.getPointList();
		
		if(memberlist==null){
			System.out.println("랭킹 목록 읽기 실패");
			return null;
		}

		request.setAttribute("memberlist", memberlist);
		request.setAttribute("listcount", listcount);
		forward.setRedirect(false);
		forward.setPath("5_community/rank_board_list.jsp");
		return forward;
	}

}
