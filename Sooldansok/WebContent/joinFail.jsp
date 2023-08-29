<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 완료 화면</title>

<%@include file ="header.jsp" %>



<div class=my_wrapper>

	<h2 class = login_comlete>회원가입 실패</h2>
	
	<div class = "login_comlete_bx">
		
		<p>회원가입에 실패하였습니다</p> <br>
		
		<a href="index.jsp"><button class = login_comlete_btn>메인으로</button></a>
	</div>
	
	
</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>