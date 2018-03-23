package member.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dao.MemberDAO;
import member.model.vo.MemberBean;
import common.action.*;

public class MemberJoinAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	request.setCharacterEncoding("UTF-8");
		 	
		 	ActionForward forward = new ActionForward();
		 	
			MemberDAO memberdao=new MemberDAO();
	   		MemberBean member=new MemberBean();
	   		
	   		boolean result=false;
	   		
	   		member.setMember_id(request.getParameter("member_id"));
	   		member.setMember_pw(request.getParameter("member_pw"));
	   		member.setMember_nickname(request.getParameter("member_nickname"));
	   		
	   		result=memberdao.joinMember(member);
	   		
	   		if(result==false){
	   			System.out.println("회원가입 실패");
		   		return null;
		   	}
	   		//회원가입 성공.
	   		forward.setRedirect(true);
	   		forward.setPath("./MemberLogin.me");
	   		return forward;
	}
}