
var interval = "";
var count = 20;
var rightAnswer = 0;
var wrongAnswerNum = "";

$(function(){
	document.onkeydown = doNotReload();
    
	if($('#test_num').val()=="1"){
		alert('테스트를 시작합니다.\n 로그인 상태일 경우 틀린 단어는 오답노트로 이동됩니다.');
	} 
	
	if($('#test_num').val()*1<=$('#testlist-size').val()*1){
		interval = setInterval("tTimer()",1000);
	}
	
	$('#answer_btn').click(function(e){
		
		e.preventDefault();
		
				//공백 체크
			if($.trim($('#confirm_answer').val())==""){
				alert('답을 입력해주세요');
				
			} else {
				//숫자 카운트 멈추기
				stopInterval();
				//alert($('#test_num').val());
				//마지막 문제가 아닐경우
				
				if($('#test_num').val()*1<$('#testlist-size').val()*1){
					//정답 일 경우 - 맞춘 개수 +1, 랭크 포인트 +1;
					if($('#confirm_answer').val().toLowerCase()==$('#test_splitword').val().toLowerCase()){
					alert('축하해요! 정답이에요. 다음 문제로 넘어갈게요.');
					rightAnswer = $('#rightAnswer').val()*1+1;
					
					} else {
						alert('틀렸네요.\n다음 문제로 넘어갈게요.');
						rightAnswer = $('#rightAnswer').val();
						wrongAnswerNum = $('#wrongAnswerNum').val();
					}
						
				//마지막 문제일 경우
				} else if($('#test_num').val()*1==$('#testlist-size').val()*1){
					
					if($('#confirm_answer').val().toLowerCase()==$('#test_splitword').val().toLowerCase()){
						alert('축하해요! 정답이에요.');
						rightAnswer = $('#rightAnswer').val()*1+1;
						
					} else {
						alert('아쉽네요. 틀렸어요!');
						rightAnswer = $('#rightAnswer').val()
						wrongAnswerNum = $('#wrongAnswerNum').val();
					}
				}
				
				//문제 번호(인덱스번호), 맞은 문제 개수, 오답단어 번호 쿼리스트링으로 보냄 
				location.href="./AllTest.ts?test_num=" + $('#test_num').val() 
							+ "&rightAnswer=" + rightAnswer 
							+ "&wrongAnswerNum=" + wrongAnswerNum;
			}
				
	});
	
});


function tTimer() {
	
    $('.countdown').text(--count);

	 if (count <= 10) {
		$('.countdown').css('color', '#DF0101');
	}
	if (count == 0) {
		stopInterval();
		alert("시간을 초과해서 다음문제로 넘어갑니다.");
		rightAnswer = $('#rightAnswer').val()
		wrongAnswerNum = $('#wrongAnswerNum').val();
		location.href='AllTest.ts?test_num=' + $('#test_num').val() 
				    + "&rightAnswer=" + rightAnswer
					+ "&wrongAnswerNum=" + wrongAnswerNum;
	
	}
   
}

function stopInterval() {
    window.clearInterval (interval);
    interval="";
}


function doNotReload(){
	if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) //ctrl+N , ctrl+R 
		 || (event.keyCode == 116) ) { // function F5 
		
	alert('죄송합니다. 새로고침은 할 수 없습니다.');
	event.keyCode = 0;
	event.cancelBubble = true;
	event.returnValue = false;
	return false;
	}
	}
	
