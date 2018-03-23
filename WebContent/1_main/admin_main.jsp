<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//session에서 아이디를 받아서 비로그인 상태와 로그인 상태별로  
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE HTML>
<html>
	<head>
	<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
	<title>IT WORLD </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- <meta name="description" content="Free HTML5 Website Template" /> -->
<!-- 	<meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
 -->	

	
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'> -->
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">
	
	<!-- 마지막으로 덮어쓸 CSS  -->
	<link rel="stylesheet" href="css/last-css.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
		
	<div class="gtco-loader"></div>
	<input type="hidden" id="id" value="<%=id%>">
	<div id="page">
	<nav class="gtco-nav" role="navigation">
		<div class="gtco-container">
			<div class="row">
				<div class="col-xs-2">
				<!-- 로고 -->
					<div id="gtco-logo"><a href="main.ma">IT&nbsp;WOR<span id="logo-l">L</span>D.</a></div>
				</div>
				<div class="col-xs-8 text-center menu-1">
					<ul>
						<li class="active"><a href="AdminMain.ma">Home</a></li>
						
						<li class="has-dropdown">
							<a href="./manageWord.ad">단어 관리</a>
						</li>
						<li class="has-dropdown">
							<a href="./manageMem.ad">회원 관리</a>
						</li>
						<li class="has-dropdown">
							<a href="./BoardList2.ad">게시판 관리</a>
						</li>
					</ul>
				</div>
				<div class="col-xs-2 text-right hidden-xs menu-2">
				<!-- 관리자로 로그인 할 때  -->
				<% if(id.equals("admin")){ %>
				<ul>
				 	<li class="has-dropdown">
							 <a href="./AdminMain.ma" id="header-user">관리자 로그인 중</a>
							<ul class="dropdown">
								<li><a href="./AdminMain.ma">관리자 페이지</a></li>
								<li><a href="./MemberLogout.me">로그아웃</a></li>
							</ul> 
					</li>
				</ul>	
				<%} %>
				</div>
			</div>
			
		</div>
	</nav>

	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image:url(images/img_bg_2.jpg);">
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<div class="display-t">
						<div class="display-tc animate-box" data-animate-effect="fadeIn">
							<h1 id="main-itworld">IT&nbsp;WOR<span id="logo-l">L</span>D</h1>
							<br>
							<h4 class="main-h4">이곳은 IT WORLD 관리자 페이지입니다. 수정할 항목을 선택해 주세요.</h4>
							<br>
							<br>
							<a href="./manageWord.ad" class="btn btn-default btn-category">단어관리</a>
							<a href="./manageMem.ad" class="btn btn-default btn-category">회원관리</a>
							<a href="./BoardList2.ad" class="btn btn-default btn-category">게시판 관리</a>
							<br>
							<br>
							<br>
							<br>
							<br>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script> 
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
	
	<!-- (현선추가) -->
	<script src="js/hyunsun.js" type="text/javascript"></script>

	</body>
</html>

