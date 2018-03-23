<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%
	String nickname = request.getParameter("member_nickname");
	System.out.println(nickname);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String message = "";
	String color = "";
	int result = 0;
	try {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();

		pstmt = conn.prepareStatement("select * from wordmember where member_nickname=? ");
		pstmt.setString(1, nickname);
		rs = pstmt.executeQuery();

		//입력한 ID가 Db애 있는 경우
		if (rs.next()) {
			message = "이미 있는 닉네임 입니다.";
			color = "red";
		} else {
			message = "사용할 수 있는 닉네임입니다";
			result = 1;// 사용가능한 아이디인 경우 값는 
			color = "white";
		}
		out.println(message);
	} catch (Exception e) {
		out.println("<h3>커넥션 풀에 연결실패 했습니다.</h3>");
		e.printStackTrace();
	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>IT WORLD - [멤버] 닉네임 확인</title>
</head>
<body>
	<input type="button" value="닫기" id="end">


	<script src="../js/jquery-3.2.1.js"></script>
	<!-- 
	window.속성을 이용한 윈도우 접근
	window.self 또는 self : 현재 열여있는 윈도우
	window.opener 또는 opener :
	open() 메소드를 이용해서 새로운 창을 연 윈도우
	 -->
	<script type="text/javascript">
		$(function(){
			$("#end").click(function(){
				$(window.opener.document)
				.find("#nickcheck")
				.text('')
				.css('color', '<%=color%>' ) 
				.append('<%=message%>');
				
				$(window.opener.document)
				.find("#nicknamecheckresult")
				.val('<%=result%>');
						window.self.close();
					});
		});
	</script>
</body>
</html>

