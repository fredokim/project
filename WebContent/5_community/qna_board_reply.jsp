<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="community.model.vo.BoardBean"%>
<%
	BoardBean board = (BoardBean) request.getAttribute("boarddata");
%>

<html>
<head>
<title>문의 게시판 - 글 수정</title>
<link href="./css/Boardreply.css" type="text/css" rel="stylesheet">
<script>
	function replyboard() {
		boardform.submit();
	}
</script>
</head>
<body>
	<jsp:include page="/1_main/header.jsp"></jsp:include>

	<!-- 게시판 답변 -->
	<form action="./BoardReplyAction.bo" method="post" name="boardform">
		<input type="hidden" name="board_num"
			value="<%=board.getBoard_num()%>"> <input type="hidden"
			name="board_re_ref" value="<%=board.getBoard_re_ref()%>"> <input
			type="hidden" name="board_re_lev"
			value="<%=board.getBoard_re_lev()%>"> <input type="hidden"
			name="board_re_seq" value="<%=board.getBoard_re_seq()%>"> <input
			type="hidden" name="board_id"
			value="<%=session.getAttribute("id")%>">
		<div class="animate-box">
	
		<article id="main">
			<div>
				<h3 id="title">문의 게시판 - 답변</h3>
			</div>
			<table id="list" class="table" >
				<tr>
					<td width="15%">
						<div id="writer">글쓴이</div>
					</td>
					<td id="writerset"><%=session.getAttribute("id")%></td>
				</tr>
				<tr>
					<td>
						<div id="subject">제 목</div>
					</td>
					<td><input id="subject set " name="board_subject" type="text"
						size="50" maxlength="100"
						value="Re: <%=board.getBoard_subject()%>" /></td>
				</tr>
				<tr>
					<td>
						<div id="content">내 용</div>
					</td>
					<td><textarea id="contentset" name="board_content" cols="80"
							rows="15"></textarea></td>
				</tr>
				<tr>
					<td>
						<div id="boardpass" align="center">비밀번호</div>
					</td>
					<td><input id="boardpassset" name="board_pass" type="password">
					</td>
				</tr>

				<tr class="center">
					<td colspan="2"><a id="write" href="javascript:replyboard()"><span class="span">등록</span></a>&nbsp;&nbsp;
						<a id="back" href="javascript:history.go(-1)"><span class="span">뒤로</span></a></td>
				</tr>
			</table>
		</article>
			</div>
	</form>
</body>
</html>