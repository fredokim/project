<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dictionary.model.vo.WordBean"%>
<%
	request.setCharacterEncoding("UTF-8");
	List wordList = (List) request.getAttribute("wordlist");
%>
<!DOCTYPE html>
<html>
<head>
<title>IT WORDL - [단어 검색 결과]</title>
</head>
<body>
<jsp:include page="../1_main/header.jsp"/>

	<article id="article">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h3 id="searchword-h3">단어 검색결과</h3>
					<hr class="searchword-hr">
				</div>
			</div>
	</article>
<div class="gtco-section">
		<div class="gtco-container">
				<%
					if (!wordList.isEmpty()) {
					for (int i = 0; i < wordList.size(); i++) {
					WordBean wb = (WordBean) wordList.get(i);
				%><div class="group">
				<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h2 class="word-h2"><%=wb.getWord() %></h2>
				</div>
				</div>
			<div class="row animate-box">
					<p class="text-center"><%=wb.getContent()%></p> 
					<form id="wordsearch-form" method="get" action="WordAdd.di">
					<input type="button" id="addword-btn" class="btn btn-primary btn-addword" value="내 단어장에 추가하기"><br>
					<hr class="searchword-hr2">
					</form>
					
					
					</div>
					</div>
				
			<%
					}
				} else {
					
			%>
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
				<h2>검색결과 없음</h2>
				</div>
			</div>
			<div class="row animate-box">
					<p class="text-center">죄송합니다. 검색 결과가 없습니다.</p> 
					<br>
					</div>
					
				
					
				<%} %>
				
					<form id="wordsearch-form" method="get">
					<br>
						<input type="text" id="searchword" placeholder="&nbsp;&nbsp;&nbsp;다른 단어를 검색하시려면 단어를 입력해주세요.">
						<input type="submit" id="main-search-btn" class="btn btn-primary btn-modify" value="검색"><br>
					</form>
			</div>
		</div>
</body>
</html>