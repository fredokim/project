<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mynote.model.vo.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ITWORLD - [ 내 단어장 정보처리기사 ]</title>
	<link rel="stylesheet" type="text/css" href="./css/mynote.css" />
	<script src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/mynote.js"></script>
	<jsp:include page="../1_main/header.jsp"/>

</head>
<body>
		<input type="hidden" id="category" value="word_ipe">
		<article id="article">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h3 id="searchword-h3">내 단어장</h3>
					<hr class="searchword-hr">
				</div>
			</div>
	</article>
			<div id="dictionary">
				<span class="animate-box"><a class="dictionary_name now_page">정보처리기사</a></span> 
				<span class="animate-box"><a href="MynoteSQL.no" class="dictionary_name"> SQL-D</a></span> 
				<span class="animate-box"><a href="MynoteLinux.no" class="dictionary_name"> 리눅스 마스터</a></span> 
				<span class="animate-box"><a href="MynoteETC.no" class="dictionary_name"> 기타실무용어</a> &nbsp;</span>
			</div>
			<div class="select-div">
				<select class="seloption">
					<option>모두 보기</option>
					<option>단어만 보기</option>
					<option>해설만 보기</option>
				</select>
			</div>
	<% 	List<mynoteBean> mylist = (ArrayList<mynoteBean>)request.getAttribute("mylist");
		int listcount = ((Integer)request.getAttribute("listcount")).intValue();
		int nowpage = ((Integer)request.getAttribute("page")).intValue();
		int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
		int startpage = ((Integer)request.getAttribute("startpage")).intValue();
		int endpage = ((Integer)request.getAttribute("endpage")).intValue();	%>
		
			<div class="animate-box">
	<%	for(int i=0;i<mylist.size();i++){
		mynoteBean ml = (mynoteBean)mylist.get(i);
		
		if(ml.getNum() == 0){	%>
			<div class="animate-box no-words"> 등록된 내 단어가 없습니다.</div>
	
	<%}else {%>
		
	<div class="all">
			<input type="hidden" id="v_length" value="<%=mylist.size() %>">	<!-- 화면에 표시될 단어 갯수 -->
				<div class="wordss" id="w<%=ml.getNum()%>">
				<input type="button" class="btn wordsButton x" id="<%=ml.getNum() %>" value="X">
				<input type="button" class="btn wordsButton v" id="<%=ml.getNum() %>" value="V" name="v">
				<h4 class="words words_word"><%=ml.getWord()%> </h4>
					
					
					
				<p class="words words_content">
					<%=ml.getContent() %>
				</p>
				</div><br>	
		<%} 
		}%>
		<div class="mypagelist">
		<%if(nowpage<=1){ //1페이지 이하인 경우 %>
				[이전]&nbsp;
			<%}else{  //2페이지 이상인 경우 - 한 페이지 적은 페이지로 이동 %>
				<a class="mypagelist" href="./MynoteIPE.no?page=<%=nowpage-1 %>">
				[이전]</a>&nbsp;
			<%} %>
			
			<%for (int a=startpage; a<= endpage; a++){
				if(a==nowpage){  // 현재 페이지 - 링크 걸지 않습니다. %>
					<span id="nowmylist"><%=a %></span>
			<%	}else{ //현재 페이지 아닌 경우 링크 겁니다.	%>
				<a class="mypagelist" href="./MynoteIPE.no?page=<%=a %>"><%=a %></a>
				<%} %>
			<%}%>
			  
		<!-- 현재 페이지가 가장 큰 페이지보다 크거나 같은 경우 [다음]링크에 걸지 않습니다.  -->
		<%if(nowpage >= maxpage){ %>
			[다음]
		<%}else{ %>
			<a class="mypagelist" href="./MynoteIPE.no?page=<%=nowpage+1 %>">[다음]</a>
		<%} %>	
		</div>
		</div> 
		
	</div> 
			<br>
			<input type="button" class="btn btn-primary nini" id="word_test" value="단어 테스트" />
			<input type="button" class="btn btn-primary" id="sel_del" value="선택 삭제" />
			단어 전체 선택 &nbsp;<input type="button" class="btn btn-primary" id="all_v" value="V" />
			<hr>
</body>	
</html>