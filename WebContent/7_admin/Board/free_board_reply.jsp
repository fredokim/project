<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="admin.model.vo.BoardBean"%>
<%
	BoardBean board = (BoardBean) request.getAttribute("boarddata");
%>

<html>

<head>
<title>IT WORLD - [게시판관리] 자유게시판 답글</title>
<script src="/SemiProject/js/jquery-3.0.0.js"></script>
<link href="./css/Boardreply.css" type="text/css" rel="stylesheet">
<script>
	function replyboard() {
		boardform.submit();
	}
</script>
</head>
<body>
	<jsp:include page="/1_main/mheader.jsp"></jsp:include>

	<!-- 게시판 답변 -->
	<form action="./BoardReplyAction2.ad" method="post" name="boardform">
		<input type="hidden" name="board_num2"
			value="<%=board.getBoard_num()%>"> <input type="hidden"
			name="board_re_ref2" value="<%=board.getBoard_re_ref()%>"> <input
			type="hidden" name="board_re_lev2"
			value="<%=board.getBoard_re_lev()%>"> <input type="hidden"
			name="board_re_seq2" value="<%=board.getBoard_re_seq()%>"> <input
			type="hidden" name="board_id2"
			value="<%=session.getAttribute("id")%>">

		<div class="animate-box">
			<article id="main">
			<h3 id="title-admin">자유 게시판 - 답변</h3>
				<table id="list" class="table">
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
						<td><input id="subject set" name="board_subject2" type="text"
							size="50" maxlength="100"
							value="Re: <%=board.getBoard_subject()%>" /></td>
					</tr>
					<tr>
						<td>
							<div id="content">내 용</div>
						</td>
						<td><textarea id="contentset" name="board_content2" cols="80"
								rows="15"></textarea></td>
					</tr>
					<tr>
						<td>
							<div id="boardpass" align="center">비밀번호</div>
						</td>
						<td><input id="boardpassset" name="board_pass2"
							type="password"></td>
					</tr>

					<tr class="center">
						<td colspan="2"><a id="write" href="javascript:replyboard()"><span class="span">등록</span>
						</a>&nbsp;&nbsp;
							<a id="back" href="javascript:history.go(-1)"><span class="span">뒤로</span>
							</a></td>
					</tr>
				</table>
			</article>
		</div>
	</form>
</body>
</html>