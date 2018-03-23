<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="community.model.vo.BoardBean"%>
<%
	BoardBean board = (BoardBean) request.getAttribute("boarddata");
%>

<html>
<head>
<link href="/SemiProject/css/Boardview.css" type="text/css"
	rel="stylesheet">
<title>문의 게시판 - 게시글 보기</title>
</head>

<body>
	<jsp:include page="/1_main/header.jsp"></jsp:include>
	<!-- 게시판 수정 -->
	<div class="animate-box">
	<article id="main">
	<div>
				<h3 id="title">문의 게시판 - 게시글 확인</h3>
			</div>
		<table id="list" class="table">
			<tr>
				<td width="12%" id="subjecthead">
					<div id="subject">제 목</div>
				</td>
				<td id="subjectset"><%=board.getBoard_subject()%></td>
			</tr>

			<tr>
				<td width="15%">
					<div id="content">내 용</div>
				</td>
				<td id="contentset">
				<%=board.getBoard_content()%>
				</td>
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
					href="./BoardReplyView.bo?num=<%=board.getBoard_num()%>"> <span
						class="span"> 답변 </span>
				</a>&nbsp;&nbsp; <a id="modify"
					href="./BoardModify.bo?num=<%=board.getBoard_num()%>"> <span class="span">
							수정 </span>
				</a>&nbsp;&nbsp; <a id="delete"
					href="./BoardDeleteAction.bo?num=<%=board.getBoard_num()%>"> <span class="span">
							삭제 </span>
				</a>&nbsp;&nbsp; <a id="boardlist" href="./BoardList.bo"> <span class="span">
							목록 </span>
				</a>&nbsp;&nbsp;</td>
			</tr>
		</table>
	</article>
		</div>
	<!-- 게시판 수정 -->
</body>
</html>