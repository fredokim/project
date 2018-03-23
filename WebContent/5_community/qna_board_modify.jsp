<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="community.model.vo.BoardBean"%>
<%
	BoardBean board = (BoardBean) request.getAttribute("boarddata");
%>

<html>
<head>
<title>문의 게시판 - 글 수정</title>
<link href="./css/Boardmodify.css" type="text/css" rel="stylesheet">

<script>
	function modifyboard() {
		modifyform.submit();
	}
</script>
</head>

<body>
	<jsp:include page="/1_main/header.jsp"></jsp:include>

	<form action="BoardModifyAction.bo" method="post" name="modifyform">
		<input type="hidden" name="board_num"
			value="<%=board.getBoard_num()%>"> <input type="hidden"
			name="board_id" value="<%=session.getAttribute("id")%>">
		<div class="animate-box">
			<article id="main">
				<h3 id="title">문의 게시판 - 글 수정</h3>
				<table id="list">
					<tr>
						<td width="10%" id="subjecthead">
							<div id="subject">제 목</div>
						</td>
						<td id="subjectset"><input id="subject2" name="board_subject" size="103" maxlength="150"
							value="<%=board.getBoard_subject()%>"></td>
					</tr>
					<tr>
						<td >
							<div id="content" align="center">내 용</div>
						</td>
						<td><textarea id="contentset" name="board_content" cols="100" rows="15"><%=board.getBoard_content()%></textarea>
						</td>
					</tr>
					<%
						if (!(board.getBoard_file() == null)) {
					%>
					<tr>
						<td>
							<div id="upload" align="center">파일 첨부</div>
						</td>
						<td>&nbsp;&nbsp;<%=board.getBoard_file()%>
						</td>
					</tr>
					<%
						}
					%>
					<tr>
						<td colspan="2" class="center"><a id="modify"
							href="javascript:modifyboard()"> <span class="span">
									수정 </span>
						</a>&nbsp;&nbsp; <a id="back" href="javascript:history.go(-1)"> <span
								class="span"> 뒤로 </span>
						</a>&nbsp;&nbsp;</td>
					</tr>
				</table>
			</article>
		</div>
	</form>

	<!-- 게시판 수정 -->
</body>
</html>