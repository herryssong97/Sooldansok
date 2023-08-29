<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>

</head>
<body>

<%@include file ="header.jsp" %>

<!-- 모달 모음 -->
<%@include file ="modal.jsp" %> 

<div class=my_wrapper>

<h2 class = company_name>회원가입</h2>

		    <form class="join"action="InsertJoin.join" method = "post" name="joinForm">
		    
		    	<!-- <button type="reset" id="close2" title="클릭하면 창이 닫힙니다.">x</button> -->
	            <p id="p" ></p>
	            <input type="text" id="input_id" placeholder="아이디" class="in id" name="id" >
	            
	            <input type="text" placeholder="비밀번호" id="pw" class="in pw"  name="pw"> <br>
	            <input type="password" placeholder="비밀번호 확인" id="pw2"  class="in re_pw"  name="pw2"> <br>
	            <input type="text" placeholder="이름" class="in name"  name="name"> <br>
	            <input type="text" placeholder="생년월일" class="in birth"  name="birth" id="datepicker"readonly> <br>
	            
	            <input type="text" id="sample5_address" placeholder="주소" class="in address" name="address" readonly>
	            <input type="button" value="주소 검색" onclick="sample5_execDaumPostcode()" class="mini_bk_btn in_btn half" style="float:right;"><br>
	            <input type="text" id="sample5_address2" placeholder="상세 주소" class="in address2" name="address2">&nbsp; <br>
	            <div id="map" style="width:300px;height:300px;margin:10px 0;display:none;"></div> 
	            
	            <p id="p2"></p>
	            <input type="text" placeholder="이메일" class="in email1" id="email1" name="email1" onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');">
	            
		            <select id="email2" name="email2" title="이메일 주소 선택" class="ui_select in email2">
		                <option value="">주소 선택</option>
		                <option value="naver.com">@naver.com</option>
		                <option value="nate.com">@nate.com</option>
		                <option value="gmail.com">@gmail.com</option>
		                <option value="yahoo.com">@yahoo.com</option>
		                <option value="hanmail.net">@hanmail.net</option>
		            </select>
	            <p id="p3"></p>
	            <input type="text" placeholder="연락처" class="in phone" id="phone" name="phone" maxlength=13> <br>
        	
        	<div class=modal_join_btn>
	       		<button value = "가입하기" class="in_btn half m_join_btn mini_bk_btn" >가입하기</button>
	       		<button type="reset" value="취소"class="in_btn half m_join_cancel mini_bk_btn" onclick="location.href='index.jsp'">취소</button>
	       	</div>
	       	
        	</form>

</div>
<!-- kakao주소 api -->
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b658461c6581661e0d75f36cde2af74b&libraries=services"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
mapOption = {
    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
    level: 5 // 지도의 확대 레벨
};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
position: new daum.maps.LatLng(37.537187, 127.005476),
map: map
});
function sample5_execDaumPostcode() {
	new daum.Postcode({
	    oncomplete: function(data) {
	        var addr = data.address; // 최종 주소 변수
	
	        // 주소 정보를 해당 필드에 넣는다.
	        document.getElementById("sample5_address").value = addr;
	        // 주소로 상세 정보를 검색
	        geocoder.addressSearch(data.address, function(results, status) {
	            // 정상적으로 검색이 완료됐으면
	            if (status === daum.maps.services.Status.OK) {
	
	                var result = results[0]; //첫번째 결과의 값을 활용
	
	                // 해당 주소에 대한 좌표를 받아서
	                var coords = new daum.maps.LatLng(result.y, result.x);
	                // 지도를 보여준다.
	                mapContainer.style.display = "block";
	                map.relayout();
	                // 지도 중심을 변경한다.
	                map.setCenter(coords);
	                // 마커를 결과값으로 받은 위치로 옮긴다.
	                marker.setPosition(coords)
	            }
	        });
	    }
	}).open();
};


$('#input_id').focusout(function(){ 
	var userId = $('#input_id').val(); // input_id에 입력되는 값
	 
	$.ajax({
		type : "post",
		url : "idCheck",
		data : {userId:userId},
		/* async:false, */ // false무적권
		dataType : 'text',
		success : function(result){ // idCheck 값을 가져와야함
			if(result==0){//idCheck==0
				console.log(result);
				$("#p").text('중복및 사용불가 아이디 입니다.');
				$("#p").attr('class', 'red');
			}else {
				console.log(result);
				$("#p").text('사용가능한 아이디 입니다.');
				$("#p").attr('class', 'green');
			} 
		}
		 ,
		error : function(request, error){
			console.log("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "errer : " + error);
		} 
	}) 
	
});


$('#email2').mouseup(function(){ 
		var userEmail = $('#email1').val() + "@"+ $('#email2').val(); // input email 에 입력되는 값
		console.log(userEmail);
		$.ajax({
			type : "post",
			url : "emailCheck",
			data : {userEmail:userEmail},
			/* async:false, */ // false무적권
			dataType : 'text',
			success : function(result){ // idCheck 값을 가져와야함
				console.log(result);
				if(result==0){//idCheck==0
					console.log(result);
					$("#p2").text('중복및 사용불가 이메일 입니다.');
					$("#p2").attr('class', 'red');
				}else {
					console.log(result);
					$("#p2").text('사용가능한 이메일 입니다.');
					$("#p2").attr('class', 'green');
				} 
			}
			 ,
			error : function(request, error){
				console.log("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "errer : " + error);
			} 
		}) 
});


$('#phone').focusout(function(){ 
		var userPhone = $('#phone').val(); // input phone 에 입력되는 값
		
		$.ajax({
			type : "post",
			url : "phoneCheck",
			data : {userPhone:userPhone},
			/* async:false, */ // false무적권
			dataType : 'text',
			success : function(result){ // idCheck 값을 가져와야함
				console.log(result);
				if(result==0){//idCheck==0
					console.log(result);
					$("#p3").text('중복및 사용불가 연락처 입니다.');
					$("#p3").attr('class', 'red');
				}else {
					console.log(result);
					$("#p3").text('사용가능한 연락처 입니다.');
					$("#p3").attr('class', 'green');
				} 
			}
			 ,
			error : function(request, error){
				console.log("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "errer : " + error);
			} 
		}) 
});

	
</script>
	
<%@include file ="footer.jsp" %>

</body>
</html>