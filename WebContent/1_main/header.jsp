<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



	<%
		//※활성화된 페이지 일 경우 class를 active로 설정 
		String status[] = {"","",""};
		String path = request.getServletPath();
		int slashPath = path.indexOf("/", 1) + 1;
		
		path = path.substring(slashPath ,path.length()-4);
		
		System.out.println("<출력 위치 : header.jsp>최종 파일명은(path) : " + path);
		
		//검색 결과, 단어사전 4가지 페이지일 경우 - dictionary 카테고리 활성화
		if(path.equals("search_result") || path.contains("Dic")){	
			status[0] = "active";
		//내 단어장 페이지(오답노트, 단어장)일 경우 - wordbook 카테고리 활성화 
		} else if (path.contains("Mynote") || path.equals("alltest")){
			status[1] = "active";
		//커뮤니티 (문의, 자유, 랭킹 게시판)일 경우 - community 카테고리 활성화
		} else if (path.contains("board")){
			status[2] = "active";
		}
		
		//session에서 아이디를 받아서 비로그인 상태와 로그인 상태별로  
		String id = (String)session.getAttribute("id");
	%>
	
	
<!DOCTYPE HTML>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Animate.css -->
	<link rel="stylesheet" href="/SemiProject/css/animate.css">
	
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="/SemiProject/css/bootstrap.css">
	
	<!-- Theme style  -->
	<link rel="stylesheet" href="/SemiProject/css/style.css">
	
	<!-- 마지막으로 덮어쓸 CSS  -->
	<link rel="stylesheet" href="/SemiProject/css/last-css.css">
	
	<!-- Modernizr JS -->
	<script src="/SemiProject/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>

	<div class="gtco-loader"></div>
	
	<nav class="gtco-nav" role="navigation">
		<div class="gtco-container">
			<div class="row">
				<div class="col-xs-2">
				<!-- 로고 -->
					<div id="gtco-logo"><a href="main.ma">IT&nbsp;WOR<span id="logo-l">L</span>D.</a></div>
				</div>
				<div class="col-xs-8 text-center menu-1">
					<input type="hidden" id="id" value="<%=id%>">
					<ul>
			
						<li><a href="main.ma">Home</a></li>
						
						<li class="<%=status[0] %> has-dropdown">
							<a href="DicIPE.di">dictionary</a>
							<ul class="dropdown">
								<li><a href="DicIPE.di">정보처리기사</a></li>
								<li><a href="DicSQL.di">SQL-D</a></li>
								<li><a href="DicLinux.di">리눅스마스터</a></li>
								<li><a href="DicETC.di">기타실무용어</a></li>
							</ul>
						</li>
						<li class="<%=status[1] %> has-dropdown">
							<a href="MynoteIPE.no">wordbook</a>
							<ul class="dropdown">
								<li><a href="MynoteIPE.no">내 단어장</a></li>
								<li><a href="WrongNoteView.no">오답 노트</a></li>
							</ul>
						</li>
						<li class="<%=status[2] %> has-dropdown">
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
						<li class="btn-cta"><a href="#"><span>Login</span></a></li>
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
							 <a href="#" id="header-user"><%=id%> 님</a>
							<ul class="dropdown">
								<li><a href="#">내 정보</a></li>
								<li><a href="./MemberLogout.me">로그아웃</a></li>
							</ul> 
					</li>
				</ul>
				<%} %> 
				</div>
			</div>
			
		</div>
	</nav>


	<header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image:url(/SemiProject/images/img_bg_header.jpg);">
	</header>


	<!-- jQuery -->
	<script src="/SemiProject/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="/SemiProject/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/SemiProject/js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="/SemiProject/js/owl.carousel.min.js"></script>
	<!-- Main -->
	<script src="/SemiProject/js/main.js"></script>
	
	<script type="text/javascript" src="/SemiProject/js/hyunsun.js">
<!--

//-->
</script>

	</body>
</html>

