<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>
<%@include file ="header.jsp" %>
<div class=my_wrapper>

<h2 class = company_name>로그인</h2>

 <form class="login"action="LoginSession" method="post">
          <input type="text" placeholder="아이디" class="in" name=id id=input_id>
          <input type="password" placeholder="비밀번호" class="in" name=pw id=pw> 
     	
      	<div class=modal_btn>
      		<button type="submit" class="in_btn m_login_btn mini_bk_btn">로그인</button>
      		<input type="button" class="in_btn m_id_btn mini_bk_btn" value="아이디 찾기" onclick="location.href='findId.jsp'">
      		<!-- <input type="button" class="in_btn m_id_btn mini_bk_btn half" value="비밀번호 찾기" onclick="location.href='findPw.jsp'"> -->
      		<!-- <input type="button" class="join in_btn mini_bk_btn" id="join" onclick="location.href='join.jsp'" value="회원가입"> -->
      	</div>
</form>

</div>
	
<%@include file ="footer.jsp" %>
</body>
</html>