<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>IT WORLD - [관리자] 자유게시판 글쓰기</title>
<script src="/SemiProject/js/jquery-3.0.0.js"></script>
<link href="./css/Boardwrtie.css" type="text/css" rel="stylesheet">

<script>
	function addboard2() {
		boardform2.submit();
	}
</script>
</head>
<body>
	<jsp:include page="/1_main/mheader.jsp"></jsp:include>
	<form action="./BoardAddAction2.ad" method="post"
		enctype="multipart/form-data" name="boardform2">
		<input type="hidden" name="board_id2"
			value="<%=session.getAttribute("id")%>">
		<article id="main">
			<h3 id="title-admin">자유 게시판 - 글 쓰기</h3>
			<table id="list" class="table">
				<tr>
					<td width="15%">
						<div id="writer">글쓴이</div>
					</td>
					<td><%=session.getAttribute("id")%></td>
				</tr>
				<tr>
					<td>
						<div id="subject">제 목</div>
					</td>
					<td><input name="board_subject2" type="text" size=50
						maxlength="100" value="" /></td>
				</tr>
				<tr>
					<td>
						<div id="content">내 용</div>
					</td>
					<td><textarea id="contentset" name="board_content2" cols="80" rows="15"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<div id="upload">파일 첨부</div>
					</td>
					<td><input name="board_file2" type="file" /></td>
				</tr>

				<tr class="center">
					<td colspan="2" class="lime"><a id="add"
						href="javascript:addboard2()"> <span class="span"> 등록 </span>
					</a>&nbsp;&nbsp; <a id="back" href="javascript:history.go(-1)"> <span
							class="span">뒤로</span></a></td>
				</tr>
			</table>
		</article>
	</form>
	<!-- 게시판 등록 -->
</body>
</html>