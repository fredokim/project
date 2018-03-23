<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="member.model.vo.MemberBean" %>
<%
	List memberlist = (List)request.getAttribute("memberlist");
%>
<html>
 <head>
  <title>회원관리 시스템 관리자모드(회원 목록 보기)</title>
 </head>
 <body>
   <table border=1>
	<tr>
		<td colspan=2>회원 목록</td>
	</tr>
	<%
	for(int i=0;i<memberlist.size();i++){ 
	MemberBean member=(MemberBean)memberlist.get(i);
	%>
	<tr>
		<td width="50%">
			<a href="MemberViewAction.me?id=<%=member.getMember_id() %>">
				<%=member.getMember_id() %>
			</a>
		</td>
		<td>
		<a href="MemberDeleteAction.me?id=<%=member.getMember_id() %>">삭제</a>
		</td>
	</tr>
	<%} %>
	
	<tr class="lime">
		<td colspan=2>
			<a href="./main.ma">[메인페이지 이동]</a>
		</td>
	</tr>
  </table>
 </body>
</html>