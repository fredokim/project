<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	List memberList = (List) request.getAttribute("memberlist");
	int listcount = ((Integer) request.getAttribute("listcount")).intValue();
	int[] rank = new int[memberList.size()];
%>
<%@ page import="member.model.vo.MemberBean"%>
<!DOCTYPE html>
<html>
<head>
<title>랭킹 게시판</title>
<link href="./css/Rankpoint.css" type="text/css" rel="stylesheet">
</head>

<body>
	<jsp:include page="/1_main/header.jsp"></jsp:include>
	<div class="animate-box">
		<article id="main">
			<h3 id="title">랭킹 게시판</h3>
			<table id="list" class="table">
				<tr>
					<th>
						<div id="rankhead">순위</div>
					</th>
					<th>
						<div id="namehead">닉네임</div>
					</th>
					<th>
						<div id="pointhead">포인트</div>
					</th>
				</tr>
				<%
					rank[0] = 1;
					int count = 1;
					for (int i = 0; i < memberList.size(); i++) {
						MemberBean mb = (MemberBean) memberList.get(i);
						for (int j = i + 1; j < memberList.size(); j++) {
							MemberBean mj = (MemberBean) memberList.get(j);

							if (mb.getMember_rankpoint() != mj.getMember_rankpoint()) {
								rank[j] = rank[i] + 1;
							} else {
								rank[j] = rank[i];
							}
						}
				%>
				<tr>
					<td>
						<div id="rank"><%=rank[i]%></div>
					</td>

					<td>
						<div id="name"><%=mb.getMember_nickname()%></div>
					</td>
					<td>
						<div id="point"><%=mb.getMember_rankpoint()%></div>
					</td>
				</tr>

				<%
					}
				%>
			</table>
		</article>
	</div>
</body>
</html>