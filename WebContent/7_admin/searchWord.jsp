<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="admin.model.vo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>IT WORLD - [관리자] 단어검색</title>

<link rel="stylesheet" href="/SemiProject/css/cateword.css">
</head>
<body>

	<%
		List wordlist = (ArrayList) request.getAttribute("wordlist");
	%>

	<%
		if (wordlist.isEmpty()) {
			System.out.println(wordlist);
	%>
	<div align="center" style="padding: 30 0 30 0">
		<br>
		<h2>검색결과가 없습니다.</h2>
	</div>

	<%
		} else {
	%>

	<fieldset>
		<legend>검색결과</legend>
		<table width="100%" cellspacing="0">
			<tr>
				<th class="thead" width=20%>단어번호</th>
				<th class="thead" width=50%>단어 & 단어설명</th>
				<th class="thead">작업</th>
			</tr>

		</table>
		<hr>

		<div
			style="overflow: scroll; width: 100%; height: 280PX; overflow-x: hidden;">
			<%
				for (int i = 0; i < wordlist.size(); i++) {
						WordBean dl = (WordBean) wordlist.get(i);
			%>

			<table id="dataTable" width="100%" cellspacing="0">
				<tr>
					<!-- 				<td rowspan="2"><input type="checkbox" -->
					<%-- 					id="<%=dl.getWord_num()%>"></td> --%>


					<td rowspan="2" width=20%><%=dl.getWord_num()%></td>


					<td id="단어명" width=50%><%=dl.getWord_name()%></td>
					<td rowspan="2"><a id="단어수정"
						href="./updateWordPage.ad?word_num=<%=dl.getWord_num()%>"
						onClick="window.open(this.href,'','width=450, height=570'); return false">
							<button class="snip1535">단어수정</button>
					</a> <a id="단어삭제"
						href="./deleteWord.ad?word_num=<%=dl.getWord_num()%>&category=<%=session.getAttribute("category")%>">
							<button class="snip1535">단어삭제</button>
					</a></td>
				</tr>
				<tr class="a">
					<td id="단어설명" width=50%><%=dl.getWord_info()%></td>
				</tr>
			</table>
			<hr>
			<%
				}
				}
			%>
		</div>
	</fieldset>

</body>
</html>