package member.model.service;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dao.MemberDAO;
import member.model.vo.MemberBean;
import common.action.*;

public class MemberLoginAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	ActionForward forward = new ActionForward();
		 	
		 	HttpSession session=request.getSession();
			MemberDAO memberdao=new MemberDAO();
	   		MemberBean member=new MemberBean();
	   		
	   		int result=-1;
	   		request.setCharacterEncoding("UTF-8");
	   		member.setMember_id(request.getParameter("member_id"));
	   		member.setMember_pw(request.getParameter("member_pw"));
	   		result=memberdao.isMember(member);
	   		
	   		if(result==0){	   			
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out=response.getWriter();
		   		out.println("<script>");
		   		out.println("alert('비밀번호가 일치하지 않습니다.');");
		   		out.println("location.href='./MemberLogin.me';");
		   		out.println("</script>");
		   		out.close();
		   		return null;
	   		}else if(result==-1){	   			
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out=response.getWriter();
		   		out.println("<script>");
		   		out.println("alert('아이디가 존재하지 않습니다.');");
		   		out.println("location.href='./MemberLogin.me';");
		   		out.println("</script>");
		   		out.close();
		   		return null;
	   		}
	   		
	   		String IDstore = request.getParameter("idrem");
	   		Cookie cookie = new Cookie("id1", request.getParameter("member_id"));
	   		if (IDstore != null) {
	   			cookie.setMaxAge(120);
	   			response.addCookie(cookie);
	   		} else {
	   			cookie.setMaxAge(0);
	   			response.addCookie(cookie);
	   		}
	   		
	   		//로그인 성공
	   		session.setAttribute("id", member.getMember_id());
	   		forward.setRedirect(true);
	   		forward.setPath("./main.ma");
	   		return forward;
	}
}