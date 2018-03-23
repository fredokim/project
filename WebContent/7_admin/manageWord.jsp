<!-- Ctrl + F 로 "필요" 라고 찾으면 필요한 항목 검색 가능 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>IT WORLD - [관리자] 단어관리</title>

<link rel="stylesheet" href="/SemiProject/css/manageWord.css">

<script src="./js/jquery-3.0.0.js">
	
</script>

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

	$(function() {
		$("#검색")
				.click(
						function() {
							if ($.trim($('#검색단어').val()) == "") {
								alert("검색할 단어를 입력하세요");
								return false;
							}
							$("#WordView").load(
									"./WordSearch.ad?검색단어="
											+ $.trim($('#검색단어').val()));
						});
	});
</script>
</head>
<body>

	<jsp:include page="../1_main/mheader.jsp" />
	<div id="내용">
		<br>

		<form class="form-inline my-2 my-lg-0 mr-lg-2">
			<div class="searching">
				<span> <input type="text" id="검색단어">
				</span> <input id="검색" type="button" class="btn btn-primary"
					name="wordsearch" value="검색"> <a id="단어추가"
					href="./insertWordPage.ad"
					onClick="window.open(this.href,'','width=450, height=570'); return false">
					<input class="btn btn-primary" type="button" value="단어추가">
				</a>
			</div>
			<a id="안씀" class="nav-link" data-toggle="modal"
				data-target="#exampleModal"></a>
		</form>
		<br>
		<div class="searchResult">
			<div class="wordcategory">
				<span class="animate-box fadeInUp animated-fast"> <a
					id="카테고리1" href="#">정보처리기사</a>
				</span> <span class="animate-box fadeInUp animated-fast"> <a
					id="카테고리2" href="#">sql-D</a>
				</span> <span class="animate-box fadeInUp animated-fast"> <a
					id="카테고리3" href="#">리눅스마스터</a>
				</span> <span class="animate-box fadeInUp animated-fast"> <a
					id="카테고리4" href="#">기타실무용어</a>
				</span>
			</div>
		</div>
		<br>
		<br>
		<br>
		<div>
			<div class="card-body" id="WordView"
				style="overflow: scroll; width: 100%; height: 700PX; overflow-x: hidden; overflow-y: hidden;">

			</div>
		</div>
	</div>
</body>
</html>