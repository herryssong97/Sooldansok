<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와인판매 홈</title>
</head>
<body>
<%
HttpSession ses1 = request.getSession();

ses1.setAttribute("sid", "admin"); // 세션 아이디 생성
ses1.setAttribute("spw", "admin"); // 세션 비밀번호 생성
ses1.setMaxInactiveInterval(1800); // 30분 동안 세션 유지
%>

<%@include file="header_mngr.jsp"%>

<div class=my_wrapper>

	<div id=banner>

		<ul class="bxslider">
			<li><img class=b_img alt="" src="./images/blank.jpg"></li>
		</ul>

	</div>
	<!-- div#banner-end -->

</div>

<%@include file="footer.jsp"%>

</body>
</html>