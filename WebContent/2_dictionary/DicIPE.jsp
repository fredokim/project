<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dictionary.model.vo.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ITWORLD - [ 용어사전 - 정보처리기사 ]</title>
	<link rel="stylesheet" type="text/css" href="./css/dictionary.css" />
	<script src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/dictionary.js"></script>
	<jsp:include page="../1_main/header.jsp"/>
</head>
<body>
		<input type="hidden" id="category" value="word_ipe">
		<input type="hidden" id="session_id" value="<%=session.getAttribute("id") %>">
	<article id="article">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h3 id="searchword-h3">IT용어사전</h3>
					<hr class="searchword-hr">
				</div>
			</div>
	</article>
			<div id="dictionary">
				<span class="animate-box"><a class="dictionary_name now_page">정보처리기사</a>
				</li></span> <span class="animate-box"><a href="DicSQL.di"
					class="dictionary_name"> SQL-D</a></span> <span class="animate-box"><a
					href="DicLinux.di" class="dictionary_name"> 리눅스 마스터</a></span> <span
					class="animate-box"><a href="DicETC.di"
					class="dictionary_name"> 기타실무용어</a> &nbsp;</span>
			</div>
			<div class="select-div">
				<select class="seloption">
					<option>모두 보기</option>
					<option>단어만 보기</option>
					<option>해설만 보기</option>
				</select>
			</div>

	<div class="all">
	<% List<dictionaryBean> dicdatalist = (ArrayList<dictionaryBean>)request.getAttribute("dicdatalist"); 
		int listcount = ((Integer)request.getAttribute("listcount")).intValue();
		int nowpage = ((Integer)request.getAttribute("page")).intValue();
		int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
		int startpage = ((Integer)request.getAttribute("startpage")).intValue();
		int endpage = ((Integer)request.getAttribute("endpage")).intValue();	%>	
		
		
			<div class="animate-box">
	<%	for(int i=0;i<dicdatalist.size();i++){
		dictionaryBean dl = (dictionaryBean)dicdatalist.get(i);	%>
		<input type="hidden" id="v_length" value="<%=dicdatalist.size() %>">	<!-- 화면에 표시될 단어 갯수 -->
				<div class="wordss">
				<input type="button" class="btn wordsButton v" id="<%=dl.getdictionary_num() %>" value="V">
				<input type="button" class="btn wordsButton star" id="<%=dl.getdictionary_num() %>" value="단어장 추가">
				<h4 class="words words_word"><%=dl.getdictionary_word()%> </h4>
					
					
				
				<p class="words words_content">
					<%=dl.getdictionary_content() %>
				</p>
				</div><br>
		<%} %>
		<div class="pagelist">
		<%if(nowpage<=1){ //1페이지 이하인 경우 %>
				[이전]&nbsp;
			<%}else{  //2페이지 이상인 경우 - 한 페이지 적은 페이지로 이동 %>
				<a class="pagelist" href="./DicIPE.di?page=<%=nowpage-1 %>">
				[이전]</a>&nbsp;
			<%} %>
			
			<%for (int a=startpage; a<= endpage; a++){
				if(a==nowpage){  // 현재 페이지 - 링크 걸지 않습니다. %>
					<span id="nowwordlist"><%=a %></span>
			<%	}else{ //현재 페이지 아닌 경우 링크 겁니다.	%>
				<a class="pagelist" href="./DicIPE.di?page=<%=a %>"><%=a %></a>
				<%} %>
			<%} %>
			  
		<!-- 현재 페이지가 가장 큰 페이지보다 크거나 같은 경우 [다음]링크에 걸지 않습니다.  -->
		<%if(nowpage >= maxpage){ %>
			[다음]
		<%}else{ %>
			<a class="pagelist" href="./DicIPE.di?page=<%=nowpage+1 %>">[다음]</a>
		<%} %>	
		</div>
		</div> 
		
	</div> 
			<br>
			<a href="AllTest.ts"><input type="button" class="btn nini botbutton" value="랜덤 단어 테스트" /></a>
			<input type="button" class="btn botbutton" id="sel_insert" value="선택 단어장 추가" />
			단어 전체 선택 &nbsp;<input type="button" class="btn botbutton" id="all_v" value="V" />
			<hr>
	
</body>	
</html>