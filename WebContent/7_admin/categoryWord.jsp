<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="admin.model.vo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>IT WORLD - [관리자] 단어관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="/SemiProject/css/cateword.css">


</head>
<body>

	<%
		List dicdatalist = (ArrayList) request.getAttribute("dicdatalist");
		String category = (String) session.getAttribute("category");
	%>

	<fieldset>
		<legend>검색결과</legend>		
		<table width="100%" cellspacing="0">
		<tr>
		<th class="thead" width=20%><font color="black">단어번호</font></th>
		<th class="thead" width=50%><font color="black">단어 & 단어설명</font></th>
		<th class="thead"><font color="black">작업</font></th>
		</tr>
		
		</table>
		<hr>

<div style="overflow:scroll; width:100%; height:700PX; overflow-x:hidden;">
		<%
			for (int i = 0; i < dicdatalist.size(); i++) {
				WordBean dl = (WordBean) dicdatalist.get(i);
		%>

		<table id="dataTable" width="100%" cellspacing="0">
			<tr>
<!-- 				<td rowspan="2"><input type="checkbox" -->
<%-- 					id="<%=dl.getWord_num()%>"></td> --%>


				<td rowspan="2" width=20%><%=dl.getWord_num()%></td>


				<td id="단어명" width=50%><%=dl.getWord_name()%></td>				
				<td rowspan="2">
				<a id="단어수정" href="./updateWordPage.ad?word_num=<%=dl.getWord_num()%>"
					onClick="window.open(this.href,'','width=450, height=570'); return false">
				<button class="snip1535">단어수정</button></a> 
				
				
				<a id="단어삭제"
					href="./deleteWord.ad?word_num=<%=dl.getWord_num()%>&category=<%=session.getAttribute("category")%>">
				<button class="snip1535">단어삭제</button>	</a>
				
				</td>
			</tr>
			<tr class="a">
				<td id="단어설명" width=50%><%=dl.getWord_info()%></td>
			</tr>
		</table>
		<hr>
		<%
			}
		%>
	</div>
	</fieldset>
</body>
</html>





