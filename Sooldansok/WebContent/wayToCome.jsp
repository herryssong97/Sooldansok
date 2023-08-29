<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오시는 길</title>

<%@include file ="header.jsp" %>


<div class=my_wrapper>

	<h2 class = company_name>오시는 길</h2>
	
	<div class="map_bx">
	<div id="map">
		<!-- <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d235.0411616073725!2d126.
		99216966137901!3d37.57097997959123!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca
		3eb0ea1ac35%3A0x29ca1c926a027bff!2z7JWE7J207Yuw67GF7YGs!5e0!3m2!1sko!2skr!4v1686812644109!5m2!1sko!2skr" 
		width="50%" height="450" style="border:1px solid #ccc;" allowfullscreen="" 
		loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe> -->
		
		
	</div>
		<dl class="map_info">
			<dt class=foot_info>CONTACT US</dt>
			<dd class=foot_info>02. 1666. 7790</dd>
			<dd class=foot_info>kjwine@jr.com</dd> <!-- 이멜 -->
			<dd class=foot_info>서울특별시 종로구 돈화문로26,<br> 4층(묘동, 단성사)</dd> <!-- 주소 -->
		</dl>
	</div>
	
<!-- 카카오 지도 마커api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b658461c6581661e0d75f36cde2af74b"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.570985,126.9926557), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(37.570985,126.9926557); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);    
</script>
	

</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>