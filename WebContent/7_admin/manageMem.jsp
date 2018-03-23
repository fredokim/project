<!-- Ctrl + F 로 "필요" 라고 찾으면 필요한 항목 검색 가능 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="admin.model.vo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<html>
<head>
<title>IT WORLD - [관리자] 회원관리</title>
<link rel="stylesheet" href="./css/managemem.css">
<script src="./js/jquery-3.0.0.js">
	
</script>
</head>
<body>
	<jsp:include page="../1_main/mheader.jsp" />
	<div id="내용">

		<%
			List memberList = (ArrayList) request.getAttribute("memberList");
		%>

		<fieldset>
			<legend>회원목록</legend>
			<br>
			<table width="100%" cellspacing="0">
				<tr>
					<th class="thead" width=20%><font color=black>아이디</font></th>
					<th class="thead" width=30%><font color=black>닉네임</font></th>
					<th class="thead"><font color=black>작업</font></th>
				</tr>
			</table>
			<hr>
			<div
				style="overflow: scroll; width: 100%; height: 400PX; overflow-x: hidden;">

				<%
					for (int i = 0; i < memberList.size(); i++) {
						MemberBean m1 = (MemberBean) memberList.get(i);
				%>
				<table id="dataTable" width="100%" cellspacing="0">
					<tr>
						<td><%=m1.getMember_id()%></td>
						<td><%=m1.getMember_nickname()%></td>
						<td><a href="MemberViewAction.ad?id=<%=m1.getMember_id()%>"
							onClick="window.open(this.href,'','width=450, height=570'); return false">
								<button class="snip1535">회원정보보기</button>
						</a> <a href=./DeleteMember.ad?mem_id=<%=m1.getMember_id()%>>
								<button class="snip1535">회원삭제</button>
						</a></td>
					</tr>
				</table>
				<hr>
				<%
					}
				%>
			</div>
		</fieldset>
	</div>
</body>
</html>