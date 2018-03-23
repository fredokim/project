<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>IT WORLD - [관리자] 단어수정</title>

<link href="./css/insertword.css" type="text/css" rel="stylesheet">
<link href="./css/insertword2.css" type="text/css" rel="stylesheet">

</head>
<body class="bg-dark">

	<%
		String category = (String) session.getAttribute("category");

		int word_num = Integer.parseInt(request.getParameter("word_num"));
	%>
	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">단어수정</div>
			<div class="card-body">
				<form action="./updateWordDB.ad">
					<div class="form-group">
						<label for="exampleInputEmail1">
						카테고리 :<%
							if (category.equals("1")) {
						%> 정보처리기사 <%
							} else if (category.equals("2")) {
						%> sql-D <%
							} else if (category.equals("3")) {
						%> 리눅스 마스터 <%
							} else {
						%> 기타 실무 용어 <%
							}
						%>
						</label>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">단어번호</label> <input
							class="form-control" name="word_num" value=<%=word_num%>
							readonly>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">단어명</label> <input
							class="form-control" name="word_name" type="text">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">단어설명</label> <input
							class="form-control" name="word_info" type="text">
					</div>
					<div class="form-group"></div>

					<input name="category" type="hidden" value=<%=category%>> <input
						class="btn btn-primary btn-block" type="button" value="단어수정"
						onclick="javascript:submit()"> <input
						class="btn btn-primary btn-block" type="button" value="취소"
						onclick="self.close()">
				</form>
			</div>
		</div>
	</div>
</body>
</html>