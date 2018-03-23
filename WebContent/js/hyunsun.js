
$(function(){
	
	$("#main-search-btn").click(function(e){
		
		if($('#searchword').val().trim()==""){
			alert("검색 값을 입력해주세요.");
			return false;
		}
		
		e.preventDefault();
		
		location.href="WordSearch.di?searchword=" + $("#searchword").val();
	});
	
	$(".btn-addword").click(function(){
		
		//비로그인시
		if($('#id').val()=='null'){
			 alert('로그인이 필요한 서비스 입니다.');
			 history.go(-1);
		}
		var wordname = $(this).parents('.group').find('h2').text();
		location.href="./WordAdd.di?wordname=" + wordname;
	});
	
	
		
});
