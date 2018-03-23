<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>IT WORLD - [관리자] 단어추가</title>
<link href="./css/insertword.css" type="text/css" rel="stylesheet">
<link href="./css/insertword2.css" type="text/css" rel="stylesheet">
</head>
<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">단어추가</div>
      <div class="card-body">
        <form action="./insertWordDB.ad">
          <div class="form-group">
            <label for="exampleInputEmail1">카테고리</label><br>
            <select name="WordTable">
					<option value="word_ipe">정보처리기사</option>
					<option value="word_sql">sql-d</option>
					<option value="word_linux">리눅스 마스터</option>
					<option value="word_etc">기타실무용어</option>
			</select> <br>
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">단어번호</label>            
            <input class="form-control" name="word_num" type="text" placeholder="1~9999">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">단어명</label>            
            <input class="form-control" name="word_name" type="text" >
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">단어설명</label>            
            <input class="form-control" name="word_info" type="text" >
          </div>
          <div class="form-group">            
          </div>
          <input class="btn btn-primary btn-block" type="button" value="단어추가" onclick="javascript:submit()">					
		  <input class="btn btn-primary btn-block" type="button" value="취소" onclick="self.close()">         
          

        </form>        
      </div>
    </div>
  </div>
</body>
</html>