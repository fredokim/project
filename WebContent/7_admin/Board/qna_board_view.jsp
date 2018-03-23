<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="admin.model.vo.BoardBean"%>
<%@page import="admin.model.vo.CommentBean"%>
<%
	BoardBean board = (BoardBean) request.getAttribute("boarddata");
	List commentlist = (List) request.getAttribute("commentlist");
	String id = (String) session.getAttribute("id");
%>

<html>
<head>
<title>IT WORLD - [게시판관리] 문의 게시판 </title>
<script src="/SemiProject/js/jquery-3.0.0.js"></script>
<script>
	function addcomment() {
		commentform.submit();
	}
</script>
<link href="/SemiProject/css/Boardview.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/1_main/mheader.jsp"></jsp:include>
	<article id="main">
		<h3 id="title-admin">문의 게시판 - 게시글 확인</h3>
		<table id="list" class="table">
			<tr>
				<td width="15%" id="subjecthead">
					<div id="subject">제 목</div>
				</td>

				<td id="subjectset"><%=board.getBoard_subject()%></td>
			</tr>
			<tr>
				<td>
					<div id="content">내 용</div>
				</td>
				<td id="contentset"><%=board.getBoard_content()%></td>
			</tr>

			<tr>
				<td>
					<div id="file">첨부파일</div>
				</td>
				<td>
					<%
						if (!(board.getBoard_file() == null)) {
					%> <a id="upload" href="./boardupload/<%=board.getBoard_file()%>">
						<%=board.getBoard_file()%>
				</a> <%
 	}
 %>
				</td>
			</tr>

			<tr class="center">
				<td colspan="2" class="lime"><a id="reply"
					href="./BoardReplyView.ad?num=<%=board.getBoard_num()%>"> <span
						class="span"> 답변 </span>
				</a>&nbsp;&nbsp; <a id="delete"
					href="./BoardDeleteAction.ad?num=<%=board.getBoard_num()%>"> <span
						class="span">삭제</span>
				</a>&nbsp;&nbsp; <a id="boardlist" href="./BoardList.ad"> <span
						class="span">목록</span>
				</a>&nbsp;&nbsp;</td>
			</tr>
		</table>
	</article>
	<!-- 게시판 수정 -->
</body>


</html>