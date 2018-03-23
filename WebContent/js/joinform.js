$(document).ready(function() {
	var sid, sid2;
	$("#check").click(function() {
		if ($("input[name='member_id']").val() == '') {
			alert("ID를 입력하세요.");
			$("input[name=member_id]").focus();
			return false;
		} else {
			sid = $("#member_id").val();
			var ref = "./6_member/idcheck.jsp?member_id=" + sid;
			window.open(ref, "idcheck", "width=350,height=200");
		}
	});
	$("#nicknamecheck").click(function() {
		if ($("input[name='member_nickname']").val() == '') {
			alert("닉네임을 입력하세요.");
			$("input[name=member_nickname]").focus();
			return false;
		} else {
			sid = $("#member_nickname").val();
			var ref = "./6_member/nickcheck.jsp?member_nickname=" + sid;
			window.open(ref, "nickcheck", "width=350,height=200");
		}
	});
	var idck = $("#idcheckresult").val();
	var nick = $("#nicknamecheckresult").val();
	$("#member_id").keyup(function() {
		idck == '0';
	});
	$("#nicknamecheckresult").keyup(function() {
		nick == '0';
	})

	// 회원가입 버튼 클릭시 이벤트 처리
	$("form[name='joinform']").submit(function(event) {
		if ($("#member_id").val() == '') {
			alert("id를 입력하세요.");
			$("input[name=member_id]").focus();
			return false;
		} else if ($("input[name='member_pw']").val() == '') {
			alert("비밀번호를 입력하세요.");
			$("input[name=member_pw]").focus();
			return false;
		} else if ($("input[name='member_nickname']").val() == '') {
			alert("닉네임을 입력하세요.");
			$("input[name=member_nickname]").focus();
			return false;
		} else if (idck != "1") {
			alert("ID 중복검사를 해주세요.");
			$("input[name=member_id]").focus();
			return false;
		} else if (nick != "1") {
			alert("닉네임 중복 체크 하세요.");
			$("input[name=member_nickname]").focus();
			return false;
		} else {
			if (confirm("회원가입에 성공했습니다. 로그인하시겠습니까?")) {
				location.href = "./MemberLogin.me";
			} else {
				location.href = "./main.ma";
			}
		}
	});
});
