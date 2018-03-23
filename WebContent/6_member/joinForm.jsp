<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script src="/SemiProject/js/jquery-3.2.1.js"></script>
<script src="/SemiProject/js/joinform.js"></script>
<link href="/SemiProject/css/joinForm.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>IT WORLD - [멤버] 회원가입 페이지</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
					<div id="gtco-logo">
						<a href="./main.ma" id="home">IT&nbsp;WOR<span id="logo-l">L</span>D.
						</a>
					</div>
				<div class="wrap">
					<p class="form-title">Sign In</p>
					<form class="login" action="./MemberJoinAction.me" method="post"
						name="joinform">
						<input type="text" placeholder="Username" name="member_id" id="member_id" /> 
						<input type="button" value="ID중복체크" id="check">
						<div id="idcheck"></div>
						<input type="hidden" id="idcheckresult"> 
						<input type="password" placeholder="Password" name="member_pw" /> 
						<input type="text" placeholder="Nickname" name="member_nickname" id="member_nickname"> 
						<input type="button" value="닉네임중복체크" id="nicknamecheck">
						<div id="nickcheck"></div>
						<input type="hidden" id="nicknamecheckresult"> 
						<input type="submit" value="회원가입" class="btn btn-success btn-sm" />
						<div class="row"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>