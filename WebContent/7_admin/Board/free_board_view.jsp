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
<title>IT WORLD - [게시판관리] 자유게시판 </title>
<script src="/SemiProject/js/jquery-3.0.0.js"></script>
<script>

	function addcomment() {
		commentform.submit();
	}
</script>
<link href="/SemiProject/css/Boardview.css" type="text/css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="/1_main/mheader.jsp"></jsp:include>
	<article id="main">
		<h3 id="title-admin">자유 게시판 - 게시글 확인</h3>
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
					href="./BoardReplyView2.ad?num2=<%=board.getBoard_num()%>"> <span
						class="span"> 답변 </span>
				</a>&nbsp;&nbsp; <a id="delete"
					href="./BoardDeleteAction2.ad?num2=<%=board.getBoard_num()%>">
						<span class="span">삭제</span>
				</a>&nbsp;&nbsp; <a id="boardlist" href="./BoardList2.ad"> <span
						class="span">목록</span>
				</a>&nbsp;&nbsp;</td>
			</tr>
		</table>
		<form action="./CommentWrite.ad" method="post" name="commentform">
			<table id="comment" class="table">

				<%
					if (commentlist != null) {
						for (int i = 0; i < commentlist.size(); i++) {
							CommentBean cl = (CommentBean) commentlist.get(i);
				%>
				<tr>
					<td>
						<div id="comment_head">
							<%=cl.getComment_id()%><br>
							<!--작성자 아이디-->
							<!-- 작성 날짜 -->
							<%=cl.getComment_date()%>
						</div>
					</td>
					<td>
						<div id="commentset">
							<%=cl.getComment_content()%>
							<!-- 본문 내용 -->
						</div>
					</td>
					<td><a
						href="./CommentDelete.ad?comment_num=<%=cl.getComment_num()%>">
							<span class="span">삭제</span>
					</a></td>
				</tr>
				<%
					}

					}
				%>
				<tr>
					<td><input type="hidden" name="comment_id" value="<%=id%>">
						<input type="hidden" name="comment_board"
						value="<%=board.getBoard_num()%>">
						<div id="comment_sub">댓글달기</div></td>
					<td width="60%"><textarea rows="2" cols="80"
							name="comment_content"></textarea></td>
					<td><a href="javascript:addcomment()"><span class="span">댓글
								등록</span></a></td>
				</tr>

			</table>
		</form>
	</article>
	<!-- 게시판 수정 -->
</body>
</html>