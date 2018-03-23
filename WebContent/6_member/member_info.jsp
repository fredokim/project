<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.MemberBean"%>
<%
	MemberBean member = (MemberBean) request.getAttribute("member");
%>

<html>
<head>
<title>회원관리 시스템 관리자모드(회원 정보 보기)</title>
</head>
<body>
	<table border=1>
		<tr>
			<th colspan=2>회원 정보</th>
		</tr>
		<tr>
			<td>회원번호</td>
			<td><%=member.getMember_num()%></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=member.getMember_id()%></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%=member.getMember_pw()%></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><%=member.getMember_nickname()%></td>
		</tr>
		<tr>
		<td>랭킹포인트</td>
		<td><%=member.getMember_rankpoint() %></td>
		</tr>
		<tr>
			<td colspan=2 style="background: lime"><a
				href="MemberListAction.me">리스트로 돌아가기</a></td>
		</tr>
	</table>
</body>
</html>