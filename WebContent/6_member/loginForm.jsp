<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/SemiProject/css/loginForm.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%
	boolean click = false;
	String id = null;

	String cookie = request.getHeader("Cookie");
	if (cookie != null) {
		Cookie cookies[] = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("id1")) {
				click = true;
				id = cookies[i].getValue();
			}
		}
	}
%>
</head>
<title>IT WORLD - [멤버] 로그인 페이지</title>
<body>
	<script src="./js/jquery-3.2.1.js"></script>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div id="gtco-logo">
					<a href="./main.ma" id="home">IT&nbsp;WOR<span id="logo-l">L</span>D.
					</a>
				</div>
				<div class="wrap">
					<p class="form-title">Sign In</p>
					<form class="login" action="MemberLoginAction.me" method="post">
						<%
							if (click == true) {
						%>
						<input type="text" placeholder="Username" name="member_id"
							value=<%=id%> /> <input type="password" placeholder="Password"
							name="member_pw" /> <input type="submit" value="Login"
							class="btn btn-success btn-sm" />
						<div class="remember-forgot">
							<div class="row">
								<div class="col-md-6">
									<div class="checkbox">
										<label> <input type="checkbox" checked="checked"
											name="idrem">ID기억하기
										</label>
									</div>
								</div>
								<div class="col-md-6 forgot-pass-content">
									<a href="./MemberJoin.me" class="forgot-pass">회원 가입</a>
								</div>
							</div>
						</div>
						<%
							} else {
						%>
						<input type="text" placeholder="Username" name="member_id" /> <input
							type="password" placeholder="Password" name="member_pw" /> <input
							type="submit" value="Login" class="btn btn-success btn-sm" />
						<div class="remember-forgot">
							<div class="row">
								<div class="col-md-6">
									<div class="checkbox">
										<label> <input type="checkbox" name="idrem">ID기억하기
										</label>
										<%
											}
										%>

									</div>
								</div>
								<div class="col-md-6 forgot-pass-content">
									<a href="./MemberJoin.me" class="forgot-pass">회원 가입</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>