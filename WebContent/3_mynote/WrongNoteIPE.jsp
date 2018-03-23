<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mynote.model.vo.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ITWORLD - [ 오답노트 정보처리기사 ]</title>
	<link rel="stylesheet" type="text/css" href="./css/wrongnote.css" />
	<script src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/wrongnote.js"></script>
	<jsp:include page="../1_main/header.jsp"/>
</head>
<body>
		<input type="hidden" id="category" value="word_ipe">
	<article id="article">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h3 id="searchword-h3">오답 노트</h3>
					<hr class="searchword-hr">
				</div>
			</div>
	</article>
		<div id="dictionary">
				<span class="animate-box"><a class="dictionary_name now_page">정보처리기사</a></span> 
				<span class="animate-box"><a href="WrongNoteViewSql.no" class="dictionary_name"> SQL-D</a></span> 
				<span class="animate-box"><a href="WrongNoteViewLinux.no" class="dictionary_name"> 리눅스 마스터</a></span> 
				<span class="animate-box"><a href="WrongNoteViewETC.no" class="dictionary_name"> 기타실무용어</a> &nbsp;</span>
		</div>
		
	<%  List<mynoteBean> mylist = (ArrayList<mynoteBean>)request.getAttribute("mylist");%>	
			
		<% if(mylist.size() == 0){	%>
			<div class="animate-box no-words"> 등록된 오답노트가 없습니다.</div>
		<% } else{
			
			for(int i=0;i<mylist.size();i++){
			mynoteBean ml = (mynoteBean)mylist.get(i);%>
			<% if(ml.getNum() == 0){	%>
			<div class="animate-box no-words"> 등록된 오답노트가 없습니다.</div>
			
			<%}else {%>
		
	<div class="all">
			<input type="hidden" id="v_length" value="<%=mylist.size() %>">	<!-- 화면에 표시될 단어 갯수 -->
			<div class="animate-box">
				<div class="wordss">
				<input type="button" class="btn wordsButton x" id="<%=ml.getNum() %>" value="X">
				<input type="button" class="btn wordsButton v" id="<%=ml.getNum() %>" value="V" name="v">
				<h4 class="words words_word"><%=ml.getWord()%> </h4>
				
				<p class="words words_content">
					<%=ml.getContent() %>
				</p>
				</div><br>
				
		<%}
			}
		}%>
		</div>
	</div> 
			<br>
			<input type="button" class="btn nini2 botbutton" id="word_test" value="선택 단어 테스트" />
			<input type="button" class="btn asas1" id="sel_del" value="선택 삭제" />
			단어 전체 선택 &nbsp;<input type="button" class="btn asas1" id="all_v" value="V" />
			<hr>
	
	
</body>	
</html>