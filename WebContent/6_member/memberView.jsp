<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.MemberBean"%>

<%
	MemberBean member = (MemberBean) request.getAttribute("member");
%>

<html>
<head>
<title>IT WORLD - [멤버] 내정보 페이지 </title>

<link href="./css/insertword.css" type="text/css" rel="stylesheet">
<link href="./css/insertword2.css" type="text/css" rel="stylesheet">

</head>
<body class="bg-dark">
	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">회원정보</div>
			<div class="card-body">
				<form action="./MemberModifyAction.me" method="post">

					<div class="form-group">
						<label for="exampleInputEmail1">회원번호</label><br> <input
							name="member_num" class="form-control" type="text"
							value="<%=member.getMember_num()%>" readonly>
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">아이디</label> <input
							name="member_id" class="form-control" type="text"
							value="<%=member.getMember_id()%>" readonly>
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">비밀번호</label> <input
							class="form-control" name="member_pw" type="text"
							value="<%=member.getMember_pw()%>">
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">닉네임</label> <input
							name="member_nickname" class="form-control" type="text"
							value="<%=member.getMember_nickname()%>">
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">랭킹포인트</label> <input
							class="form-control" name="member_rankpoint" type="text"
							value="<%=member.getMember_rankpoint()%>" readonly>
					</div>

					<input class="btn btn-primary btn-block" type=button value="회원정보수정"
						onclick="javascript:submit()"> 
						<input class="btn btn-primary btn-block" type="button" value="닫기"
						onclick="javascript:history.go(-1)">
				</form>
			</div>
		</div>
	</div>
</body>
</html>