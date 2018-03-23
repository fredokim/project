<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dictionary.model.vo.WordBean"%>
<%@ page import="common.action.ActionForward" %>
	<% 
	List testlist = (List)session.getAttribute("testlist");
	String id = (String)session.getAttribute("id");
	int rightAnswer = 0;
	
   %>
<!DOCTYPE html>
<html>
<head>
<title>IT WORLD - [단어테스트]</title>
</head>
<!-- 새로고침 방지(브라우저 상은 불가) -->
<body onkeydown="javascript:doNotReload()">
<jsp:include page="../1_main/header.jsp"/>
<article id="article">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h3 id="searchword-h3">단어 테스트</h3>
					<hr class="searchword-hr">
				</div>
			</div>
	</article>

	<% 
		try{
		int test_num = Integer.parseInt((String)request.getAttribute("test_num"));
		System.out.println("<alltest.jsp> 현재 문제번호는 : " + test_num);
		
		
		if(test_num > 0){
			System.out.println("여기까지는 오나");
			rightAnswer = Integer.parseInt((String)request.getAttribute("rightAnswer"));
		}
		
		if(test_num<testlist.size()){
			System.out.println("testlist 사이즈" + testlist.size());
			WordBean testword = (WordBean)testlist.get(test_num);
			
			
			String categoryName = "";
			System.out.println("alltest.jsp 현재페이지 : " + categoryName);
			
			
			int categoryNum = Integer.parseInt(String.valueOf(testword.getNumber()).substring(0, 1));
			
			System.out.println("카테고리 번호 : " + categoryNum);
			switch (categoryNum) {
			case 1:
				categoryName="정보처리기사";
				break;
			case 2:
				categoryName="SQL-D";
				break;
			case 3:
				categoryName="리눅스 마스터";
				break;
			case 4:
				categoryName="기타실무용어";
				break;
			}
			System.out.println(categoryName);
		%>
	<div class="gtco-container">
		<div class="animate-box">
			<div class="countdown_div">
				<div class="category"><%=categoryName%></div>
				<div class="reamainingtime question">남은 문제
				<h5><%=test_num+1 %>/<%=testlist.size() %></h5>
				</div>
				<div class="reamainingtime">남은시간
				<h5 class="countdown">10</h5>
				</div>
				
			</div>
			<div class="text-center gtco-heading gtco-heading-sm">
		 	<%
		 	String wordname = testword.getWord();
		 	//가로를 공백 한칸으로 바꾸고
		 	wordname = wordname.replaceAll("\\(", " ");
		 	wordname = wordname.replaceAll("\\)", " ");
		 	wordname = wordname.replaceAll("-", " ");
		 	
		 	//공백 한칸을 기준으로 잘라서 배열에 넣음
		 	String wordName[] = wordname.split(" ");
		 	
			/* for(int i =0; i<wordName.length; i++){
				System.out.println(wordName[i]);
			} */
			
			//랜덤으로 단어를 잘라서 문제 제출
			int random = (int)(Math.random()*wordName.length);
			
			System.out.println("split 단어갯수 ? : " + wordName.length);
			System.out.println("랜덤 값은 ? : " + random);
			
			//잘라낸 단어 길이만큼 대시(_)넣음
			String dash = "";
			for(int i = 0; i<wordName[random].length(); i++) {
				//마지막 번호일 경우 띄어쓰기 없이
				if(i==wordName[random].length()-1){
					dash += "_";
				} else {
					dash += "_ ";
				}
			}
			
			//최종으로 테스트할 단어를 담음
			String resultWord = testword.getWord().replaceFirst(wordName[random], dash);
			
			System.out.println("최종으로 테스트 할 단어 : " + resultWord);
			System.out.println("정답은 : " + wordName[random]);
			%> 
				<h2 class="word-h2"><%=resultWord%></h2>
				
				<!-- hidden으로 값 넘길 것들 -->
				<input type="hidden" id="test_num" value="<%=++test_num %>">
				<input type="hidden" id="test_splitword" value="<%=wordName[random] %>">
				<input type="hidden" id="rightAnswer" value="<%=rightAnswer %>">
				<input type="hidden" id="wrongAnswerNum" value="<%=testword.getNumber() %>">
				<input type="hidden" id="testlist-size" value="<%=testlist.size() %>">
				
			</div>
		</div>
		<div class="row animate-box">
			<p class="text-center" id="test_content"><%=testword.getContent()%></p>
			<form id="wordsearch-form" method="get">
				<input type="text" id="confirm_answer" placeholder="&nbsp;&nbsp;&nbsp;정답을 입력해주세요."> <br> 
				<input type="submit" id=answer_btn class="btn btn-primary btn-modify" value="입력"><br>
			</form>
		</div>
	</div>
	<% } else { 
			/*  맞춘갯수 받아오기  */
			rightAnswer = Integer.parseInt((String)request.getAttribute("rightAnswer"));

			double percent = (double)rightAnswer/(double)testlist.size()*100;
			System.out.println(rightAnswer + "/" + testlist.size() + "맞춘 퍼센트 " + percent);
			String lastComment = "";
			
			if(percent<=20){
				lastComment = "공부 좀 하고 오세요!<br>틀린문제는 오답노트에 적어놨어요!";
			} else if(percent<=50){
				lastComment = "아쉽지만 합격은 멀었네요. 공부하세요!<br>틀린문제는 오답노트에 적어놨어요!";
			} else if(percent<=70){
				lastComment = "그래도 잘하셨네요! 조금 더 노력해보세요!<br>틀린문제는 오답노트에 적어놨어요!";
			} else if(percent<=99){
				lastComment = "대단하세요! 조금만 더하면 100점!<br>틀린문제는 오답노트에 적어놨어요!";
			}  else if(percent==100){
				lastComment = "다 맞추셨네요! 축하해요 합격할 수 있겠어요!";
			}
		%>
		<br><br><br><br>
		<div class="gtco-container">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h2 class="word-h2">맞춘 문제 개수는 <%=testlist.size() %>개 중 <%=rightAnswer %>개 입니다.</h2>
				</div>
			</div>
			<div class="row animate-box">
				<p class="text-center" id="test_content"><%=lastComment %></p>
				<%if(id!=null) { %>
				<div class="text-center">
					<a href="./MynoteIPE.no"><input type="submit" id="mynote-move-btn"
						class="btn btn-primary" value="내 단어장으로 이동"></a>
					<a href="./WrongNoteView.no"><input type="submit" id="wrongnote-move-btn"
						class="btn btn-primary" value="오답노트로 이동"></a><br>
				</div>
				<%} %>
			</div>
		</div>

	<%	}
		} catch (Exception e){
			System.out.println("alltest.jsp Exception : " + e.getMessage() + e.getStackTrace());
	%>
		<br><br><br><br>
		<div class="gtco-container">
			<div class="animate-box">
				<div class="text-center gtco-heading gtco-heading-sm">
					<h2 class="word-h2">죄송합니다.</h2>
				</div>
			</div>
			<div class="row animate-box">
				<p class="text-center" id="test_content">잘못된 요청입니다.</p>
				<div class="text-center">
					<a href='main.ma'><input type="button"
						class="btn btn-primary" value="메인 페이지로 돌아가기"></a>
				</div>
			</div>
		</div>
		
	<%
		}
	%>
	
	
	<footer>
				<br>
				<br>
		  		<div class="row copyright">
				<div class="col-md-12">
					<p class="text-center">
						<small class="block">&copy; 2018 IT WORLD. All Rights Reserved.</small> 
					</p>
				</div>
			</div>
		</footer>		
</body>
<script src="js/test.js"></script>
</html>