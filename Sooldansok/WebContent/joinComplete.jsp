<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 완료 화면</title>

<%@include file ="header.jsp" %>

 <%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String address = request.getParameter("address");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
%> 

<div class=my_wrapper>

	<h2 class = login_comlete>회원가입 완료</h2>
	
	<div class = "login_comlete_bx">
		
		<p><%= name %>(<%=id %>)님,
		가입을 축하드립니다.</p> <br>
		
		<a href="index.jsp"><button class = login_comlete_btn>메인으로</button></a>
	</div>
	
	
</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>