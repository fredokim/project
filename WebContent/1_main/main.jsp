
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//session에서 아이디를 받아서 비로그인 상태와 로그인 상태별로  
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE HTML>
<html>
	<head>
	<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
	<title>IT WORLD</title>
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
	<meta name="twitter:image" content="" />
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
						<li class="active"><a href="main.ma">Home</a></li>
						
						<li class="has-dropdown">
							<a href="DicIPE.di">dictionary</a>
							<ul class="dropdown">
								<li><a href="DicIPE.di">정보처리기사</a></li>
								<li><a href="DicSQL.di">SQL-D</a></li>
								<li><a href="DicLinux.di">리눅스마스터</a></li>
								<li><a href="DicETC.di">기타실무용어</a></li>
							</ul>
						</li>
						<li class="has-dropdown">
							<a href="MynoteIPE.no">wordbook</a>
							<ul class="dropdown">
								<li><a href="MynoteIPE.no">내 단어장</a></li>
								<li><a href="WrongNoteView.no">오답 노트</a></li>
							</ul>
						</li>
						<li class="has-dropdown">
							<a href="./BoardList.bo">community</a>
							<ul class="dropdown">
								<li><a href="./BoardList.bo">문의 게시판</a></li>
								<li><a href="./BoardList2.bo">자유 게시판</a></li>
								<li><a href="./RankPoint.bo">랭킹 게시판</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="col-xs-2 text-right hidden-xs menu-2">
			<!-- 비로그인 상태일시   -->
				 <% if(id==null){ %>
					<ul>
						<li class="btn-cta"><a href="./MemberLogin.me"><span>Login</span></a></li>
					</ul>
				<!-- 관리자로 로그인 할 때  -->
				<%} else if(id.equals("admin")){ %>
				
				<ul>
				 	<li class="has-dropdown">
							 <a href="./AdminMain.ma" id="header-user">관리자 로그인 중</a>
							<ul class="dropdown">
								<li><a href="./AdminMain.ma">관리자 페이지</a></li>
								<li><a href="./MemberLogout.me">로그아웃</a></li>
							</ul> 
					</li>
				</ul>	
				<!-- 로그인 상태일시 -->
				<%} else { %>
				<ul>
				 	<li class="has-dropdown">
							 <a href="./MemberViewAction.me" id="header-user"><%=id%> 님</a>
							<ul class="dropdown">
								<li><a href="./MemberViewAction.me">내 정보</a></li>
								<li><a href="./MemberLogout.me">로그아웃</a></li>
							</ul> 
					</li>
				</ul>
				<%} %> 
				</div>
			</div>
			
		</div>
	</nav>

	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image:url(images/img_bg_1.jpg);">
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<div class="display-t">
						<div class="display-tc animate-box" data-animate-effect="fadeIn">
							<h1 id="main-itworld">IT&nbsp;WOR<span id="logo-l">L</span>D</h1>
							<h4 class="main-h4">IT관련 업무 학생과 초보 실무자를 위한 IT 단어장</h4>
							
							<!-- 단어 검색 form submit으로 값 넘김 -->
							<form method="get">
							<input type="text" id="searchword" placeholder="&nbsp;&nbsp;&nbsp;IT단어를 검색해 보세요.">
							<input type="submit" id="main-search-btn" class="btn btn-primary btn-modify" value="검색"><br>
							</form>
							<a href="DicIPE.di" class="btn btn-default btn-category">정보처리기사</a>
							<a href="DicSQL.di" class="btn btn-default btn-category">SQL-D</a>
							<a href="DicLinux.di" class="btn btn-default btn-category">리눅스마스터</a>
							<a href="DicETC.di" class="btn btn-default btn-category">기타 실무용어</a>
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
	
	<div id="gtco-features">
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="icon-book"></i>
						</span>
						<h3>Dictionary</h3>
						<p>IT자격증 정보처리기사, SQL-D, 리눅스 마스터와 기타실무용어에 관련한 단어 사전입니다. 
						      단어 열람이 가능하고, 원하는 단어를 내 단어장에 등록할 수 있습니다.</p>
						<br>
						<!-- 정보처리기사 단어사전 페이지로 이동 -->
						<p><a href="DicIPE.di" class="btn btn-primary">단어사전 바로가기</a></p>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="icon-folder2"></i>
						</span>
						<h3>WordBook</h3>
						<p>회원 전용 내 단어장 서비스 입니다. 단어사전에서 단어를 저장하여 단어 테스트를 보실 수 있습니다.
						      또한 테스트를 보고 틀린 단어를 오답 노트에 등록하여 재시험을 보실 수 있습니다.  </p>
						<p><a href="MynoteIPE.no" class="btn btn-primary">내 단어장 바로가기</a></p>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="icon-users"></i>
						</span>
						<h3>Community</h3>
						<p>문의 게시판에서 등록하길 원하는 단어를 관리자에게 문의 할 수 있습니다.
						      자유 게시판은 자유롭게 회원님들과 소통할 수 있습니다. </p>
						<br>
						<p><a href="BoardList2.bo" class="btn btn-primary">커뮤니티 바로가기</a></p>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	
	
	
    <div class="copyrights">Developed by 20s</div>

	
	<div id="gtco-testimonial">
		<div class="gtco-section">
				<div class="gtco-container">
					<div class="animate-box">
						<div class="text-center gtco-heading gtco-heading-sm">
							<h2>ITWORLD 란?</h2>
						</div>
					</div>
					<div class="row animate-box">
							<p class="text-center">IT관련 자격증을 취득하기 위해 공부하는 실무자와 공부하는 학생을 위한 <br>
												   IT용어 사전과 단어장 서비스를 제공하는 페이지 입니다.<br>
												      정보처리기사, SQL-D, 리눅스마스터 세가지의 자격증 관련 단어를 공부할 수 있고, <br>
												      초보 실무자를 위한 현장에서 사용하는 기타실무용어 사전이 있습니다. 한번 참고해보세요. <br>
											  	      용어 사전에서 모르는 단어를 내 단어장에 등록하여 공부하고 내 실력을 테스트 해보세요! <br>
												      자격증을 취득하는데에 많은 도움이 될 것입니다!</p> 
						</div>
				</div>
			</div>
	</div>



	<div id="gtco-team" class="gtco-section">
		<div class="gtco-container">
			<div class="row animate-box">
				<div class="col-md-8 col-md-offset-2 text-center gtco-heading">
					<h2>Our Team</h2>
				</div>
			</div>
			<div class="row">
			<div class="col-md-4 animate-box" data-animate-effect="fadeIn">
					<div class="gtco-staff">
						<img src="images/person_1.jpg" alt="Free HTML5 Templates by gettemplates.co">
						<h3>김윤환</h3>
						<strong class="role">Leader, Developer</strong>
						<p>[팀장]</p>
						<ul class="gtco-social-icons">
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-twitter"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 animate-box" data-animate-effect="fadeIn">
					<div class="gtco-staff">
						<img src="images/person_1.jpg">
						<h3>김도현</h3>
						<strong class="role">Developer</strong>
						<p>김도현 설명</p>
						<ul class="gtco-social-icons">
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-twitter"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 animate-box" data-animate-effect="fadeIn">
					<div class="gtco-staff">
						<img src="images/person_1.jpg">
						<h3>김태용</h3>
						<strong class="role">Developer</strong>
						<p>김태용 설명</p>
						<ul class="gtco-social-icons">
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-twitter"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 animate-box" data-animate-effect="fadeIn">
					<div class="gtco-staff">
						<img src="images/person_2.jpg">
						<h3>나현선</h3>
						<strong class="role">Web Designer, Developer</strong>
						<p>나현선 설명</p>
						<ul class="gtco-social-icons">
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-twitter"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 animate-box" data-animate-effect="fadeIn">
					<div class="gtco-staff">
						<img src="images/person_3.jpg">
						<h3>이성우</h3>
						<strong class="role">Developer</strong>
						<p>이성우 설명</p>
						<ul class="gtco-social-icons">
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-twitter"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<footer id="gtco-footer" role="contentinfo">
		<div class="gtco-container">
			<div class="row row-pb-md">
				<div class="col-md-4 gtco-widget">
					<h3>20s</h3>
					<p>KH아카데미 2018년 세미 프로젝트 ITWORLD [20세들] - IT관련 업무 학생과 초보 실무자를 위한 IT 단어장 </p>
				</div>
				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="gtco-footer-links">
						<li><a href="#">정보처리기사</a></li>
						<li><a href="#">SQL-D</a></li>
						<li><a href="#">리눅스 마스터</a></li>
						<li><a href="#">기타실무용어</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="gtco-footer-links">
						<li><a href="#">내 단어장</a></li>
						<li><a href="#">오답노트</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="gtco-footer-links">
						<li><a href="./BoardList.bo">문의 게시판</a></li>
						<li><a href="./BoardList2.bo">자유 게시판</a></li>
						<li><a href="./RankPoint.bo">랭킹 게시판</a></li>
					</ul>
				</div>
			</div>

			<div class="row copyright">
				<div class="col-md-12">
					<p class="text-center">
						<small class="block">&copy; 2018 IT WORLD. All Rights Reserved.</small> 
					</p>
				</div>
			</div>

		</div>
		
		
		
	</footer>
	</div>

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

