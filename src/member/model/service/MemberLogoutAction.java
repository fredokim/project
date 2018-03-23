package member.model.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.*;

public class MemberLogoutAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 	throws Exception{
				 	ActionForward forward = new ActionForward();
				 	HttpSession session=request.getSession();
				 	session.invalidate();
					response.setContentType("text/html;charset=UTF-8");
		   			PrintWriter out=response.getWriter();
			   		out.println("<script>");
			   		out.println("alert('로그아웃 되었습니다. 메인 페이지로 돌아갑니다.');");
			   		out.println("location.href='./main.ma';");
			   		out.println("</script>");
			   		out.close();
          return null;
	 }
}
