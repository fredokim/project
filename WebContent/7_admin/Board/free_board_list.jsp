<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="admin.model.vo.BoardBean"%>

<%
	List boardList = (List) request.getAttribute("boardlist");
	int listcount = ((Integer) request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	int maxpage = ((Integer) request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer) request.getAttribute("startpage")).intValue();
	int endpage = ((Integer) request.getAttribute("endpage")).intValue();
	String id = (String) session.getAttribute("id");
%>

<html>
<head>
<title>IT WORLD - [게시판관리] 자유 게시판 리스트</title>
<script src="/SemiProject/js/jquery-3.0.0.js"></script>
<link href="./css/Boardlist.css" type="text/css" rel="stylesheet">
</head>

<body>
	<!-- 게시판 리스트 -->
	<jsp:include page="/1_main/mheader.jsp"></jsp:include>
	<div class="animate-box">
		<article id="main">
			<h3 id="title-admin">자유 게시판</h3>
			<table id="list" class="table">
				<tr>
					<th width="7%">
						<div id="num">번호</div>
					</th>
					<th width="40%">
						<div id="subject">제목</div>
					</th>
					<th width="14%">
						<div id="writer">작성자</div>
					</th>
					<th width="14%">
						<div id="date">날짜</div>
					</th>
					<th width="7%">
						<div id="readcount">조회수</div>
					</th>
				</tr>

				<%
					for (int i = 0; i < boardList.size(); i++) {
						BoardBean bl = (BoardBean) boardList.get(i);
				%>
				<tr>
					<td id="numset">
						<!-- limit가 10인 경우 --> <%=listcount - (nowpage - 1) * 10 - i%>
					</td>

					<td>
						<div id="div">
							<%
								if (bl.getBoard_re_lev() != 0) {
							%>
							<%
								for (int a = 0; a <= bl.getBoard_re_lev() * 2; a++) {
							%>
							&nbsp;
							<%
								}
							%>
							▶
							<%
								} else {
							%>
							▶
							<%
								}
							%>
							<a id="view"
								href="./BoardDetailAction2.ad?num2=<%=bl.getBoard_num()%>">
								<%=bl.getBoard_subject()%>
							</a>
						</div>
					</td>

					<td>
						<div id="idset"><%=bl.getBoard_id()%></div>
					</td>
					<td>
						<div id="dateset"><%=bl.getBoard_date()%></div>
					</td>
					<td>
						<div id="readcountset"><%=bl.getBoard_readcount()%></div>
					</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan=5 id="page">
						<%
							if (nowpage <= 1) {
						%><span class="span"> 이전 </span> &nbsp; <%
 	} else {
 %> <a id="beforepage" href="./BoardList2.ad?page=<%=nowpage - 1%>">
							<span class="span"> 이전 </span>
					</a> <%
 	}
 %> <%
 	for (int a = startpage; a <= endpage; a++) {
 		if (a == nowpage) {
 %> <span class="span"> &nbsp;<%=a%> &nbsp;
					</span> <%
 	} else {
 %> <a href="./BoardList2.ad?page=<%=a%>"><%=a%> </a> &nbsp; <%
 	}
 %> <%
 	}
 %> <%
 	if (nowpage >= maxpage) {
 %> <span class="span"> 다음 </span> <%
 	} else {
 %> <a id="nextpage" href="./BoardList2.ad?page=<%=nowpage + 1%>"> <span
							class="span"> 다음 </span>
					</a> <%
 	}
 %>
					</td>
				</tr>
				<tr align="right" class="center">
					<td colspan="5" id="center"><a id="write"
						href="./BoardWrite2.ad"> <span class="span"> 글쓰기 </span>
					</a></td>
				</tr>
			</table>

			<form action="./SearchBoard.ad" id="list-form">

				<table id="search" class="table">
					<tr>
						<td><select name="search_condition" id="condition">
								<option value="board_id">글쓴이</option>
								<option value="board_subject">제목</option>
						</select></td>
						<td><input type="text" name="search" id="searchset"></td>
						<td><input type="button" value="검색"
							onclick="javascript:submit()" id="search "></td>
					</tr>
				</table>
			</form>
		</article>
	</div>
</body>
</html>

