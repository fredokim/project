
$(function(){
	var sel_words = [];	//v체크 된 단어들 번호를 담는 변수
	var all_click = 0;	//모두선택 클릭시 사용될 변수.
	
	
	$('.x').click(function(){	//x클릭
		var go = confirm('선택한 단어를 삭제하시겠습니까?');
		
		if(go){
			var wordnum = $(this).attr('id'); 
			location.href="seldelAction.no?wordnum="+wordnum+"&category="+ $('#category').val();
			alert("삭제되었습니다");
		}else{
			
		}
		
		
	});
	
	$('.v').click(function(){	//v클릭
		
		if(sel_words.indexOf($(this).attr('id')) > -1){	//v가 체크상태일 경우 체크 해제
			$(this).css('color','white');	
			
			var index = sel_words.indexOf($(this).attr('id'));
			if(index > -1){
				sel_words.splice(index,1);
			}
			
			
		}else if(sel_words.indexOf($(this).attr('id')) == -1){	//v 체크하기
			$(this).css('color','yellow');
			sel_words.push($(this).attr('id'));
		}
	});
	
	
	
	$('#sel_del').click(function(){	//선택 삭제 버튼 클릭
		var go = confirm('선택하신 단어를 모두 삭제하시겠습니까?');
		
		if(go){
			if(sel_words.length == 0){
				alert("선택된 단어가 없습니다");
			}else{
				location.href="alldelAction.no?sel_words="+sel_words+"&category="+ $('#category').val();;
				alert("모두 삭제되었습니다.");
				
			}
		}else{
			
		}
		
	});
	
	
	//※※※※※※※※※※※※※※※※※※현선추가※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※
	
	
	$('#word_test').click(function(){	//선택 삭제 버튼 클릭
		var go = confirm('선택하신 단어의 테스트를 보시겠습니까?');
		
		if(go){
			if(sel_words.length == 0){
				alert("선택된 단어가 없습니다");
			}else{
				location.href="SelTest.ts?sel_words="+sel_words;
				
			}
		}else{
			
		}
		
	});
	
	//※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※
	
	
	
	$('#all_v').click(function(){	//모두 선택 버튼 클릭
		if(all_click == 0){	//모두선택을 처음 눌럿을 때 단어모두 선택된다.
			$('.v').css('color','yellow');
			sel_words = [];
			
			var vlength = $('#v_length').val()
			var go = '';
			
			for(var n=0; n<vlength; n++){
				go = '.v:eq('+n +')';
				sel_words.push($(go).attr('id'));
			}
			all_click = 1;			
		}else if(all_click == 1) { //모두 선택을 두번째 눌렀을 때
			sel_words = [];
			$('.v').css('color','white');
			all_click = 0;
		}
	});
	
	
	$('.seloption').change(function(){
		if($('.seloption').val() == "모두 보기"){
			$('.words_word').show("fast");
			$('.words_content').show("fast");
		}else if($('.seloption').val() == "단어만 보기"){
			$('.words_word').show("fast");
			$('.words_content').hide("slow");
		}else if($('.seloption').val() == "해설만 보기"){
			$('.words_word').hide("slow");
			$('.words_content').show("fast");
		}
	});
	
	
	$('.memo').click(function(){	//모두 선택 버튼 클릭
		var id = $(this).attr('id');
		$('#w'+id).append('<input type="text" value="메모장입니다" class="mymemo" id="hhh">'); 
		$('#hhh').css("display","none")
		$('#hhh').show("fast");
	});
	
	
	
});


