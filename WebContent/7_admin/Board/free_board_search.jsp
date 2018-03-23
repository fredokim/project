<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="java.util.*"%>
<%@ page import="admin.model.vo.BoardBean"%>

<%
	List searchList = (List) request.getAttribute("searchlist");
	String id = (String) session.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<title>IT WORLD - [게시판관리] 자유 게시판 검색</title>
<script src="/SemiProject/js/jquery-3.0.0.js"></script>
<link href="./css/Searchlist.css" type="text/css" rel="stylesheet">
</head>

<body>
	<jsp:include page="/1_main/mheader.jsp"></jsp:include>
	<div class="animate-box">
		<article id="main">
			<h3 id="title-admin">게시판  - 검색 결과</h3>
			<table id="list" class="table">
				<tr>
					<th width="40%">
						<div id="subject">제목</div>
					</th>
					<th width="30%">
						<div id="writer">작성자</div>
					</th>
					<th width="14%">
						<div id="date">날짜</div>
					</th>
					<th width="15%">
						<div id="readcount">조회수</div>
					</th>
				</tr>
				<%
					for (int i = 0; i < searchList.size(); i++) {
						BoardBean bl = (BoardBean) searchList.get(i);
				%>
					<tr>
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
			</table>
		</article>
	</div>
</body>
</html>