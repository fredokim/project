
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



   <%
      //※활성화된 페이지 일 경우 class를 active로 설정 
   		 String status[] = {"","","",""};
		String path = request.getServletPath();
		int slashPath = path.indexOf("/", 1) + 1;
		
		path = path.substring(slashPath ,path.length()-4);
		
		System.out.println("<출력 위치 : mheader.jsp>최종 파일명은(path) : " + path);
		
      
      //검색 결과, 단어사전 4가지 페이지일 경우 - dictionary 카테고리 활성화
      if(path.equals("admin_main")){
    	 status[0] = "active";
      }else if(path.equals("manageWord")){   
         status[1] = "active";
      //내 단어장 페이지(오답노트, 단어장)일 경우 - wordbook 카테고리 활성화 
      } else if (path.equals("manageMem")){
         status[2] = "active";
      //커뮤니티 (문의, 자유 게시판)일 경우 - community 카테고리 활성화
      } else if (path.contains("board")){
         status[3] = "active";
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
<style>
.gtco-cover.gtco-cover-sm {
    height: 130px;
}

.dropdown > li {
display:inline-block;
}
</style>


<script>
$(function() {
	$("#카테고리1").click(function() {		
		$("#WordView").load("./categoryWord.ad?category=1");
	});
});

	$(function() {
		$("#카테고리2").click(function() {
			
			$("#WordView").load("./categoryWord.ad?category=2");
		});
	});
	$(function() {
		$("#카테고리3").click(function() {
			$("#WordView").load("./categoryWord.ad?category=3");
		});
	});
	$(function() {
		$("#카테고리4").click(function() {
			$("#WordView").load("./categoryWord.ad?category=4");
		});
	});

</script>

   </head>
   <body>

   <div class="gtco-loader"></div>
   
   <nav class="gtco-nav" role="navigation">
      <div class="gtco-container">
         <div class="row">
            <div class="col-xs-2">
            <!-- 로고 -->
               <div id="gtco-logo"><a href="./Homepage.ad">IT&nbsp;WOR<span id="logo-l">L</span>D.</a></div>
            </div>
            
            <div class="col-xs-8 text-center menu-1">
               <ul>  
               
               	 <li class="<%=status[0] %> has-dropdown">
                    <a href="./AdminMain.ma">HOME</a>
                  </li>                
                  <li class="<%=status[1] %> has-dropdown">
                    <a href="./manageWord.ad">단어관리</a>
                  </li>
                  <li class="<%=status[2] %> has-dropdown">
                     <a href="./manageMem.ad">회원관리</a>                     
                  </li>
                  <li class="<%=status[3] %> has-dropdown">
                     <a href="./BoardList2.ad">게시판관리</a>
                     <ul class="dropdown">
                        <li><a href="./BoardList2.ad">자유게시판</a></li>
                        <li><a href="./BoardList.ad">문의게시판</a></li>
                     </ul>
                  </li>
               </ul>
            </div>
            <div class="col-xs-2 text-right hidden-xs menu-2">
           
            <% if(id.equals("admin")){ %>
				
				<ul>
				 	<li class="has-dropdown">
							 <a href="./AdminMain.ma">관리자 로그인 중</a>
							<ul class="dropdown">
								<li><a href="./AdminMain.ma">관리자 페이지</a></li>
								<li><a href="./MemberLogout.me">로그아웃</a></li>
							</ul> 
					</li>
				</ul>
					
				<%}  %>
            </div>
         </div>
         
      </div>
   </nav>


   <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image:url(/SemiProject/images/admin_setting.jpg);">

   
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
   

   </body>
</html>
