<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function logout() {
		alert('로그아웃 되었습니다.');
	}
</script>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<%
	// 회원인증 정보 속성 삭제
	session.removeAttribute("sid");
	session.removeAttribute("spw");
	
	// 모든 속성 한꺼번에 삭제
	session.invalidate();
%>
<script>
	function logout()
</script>
<%	
	// 세션 속성 삭제 후 index.jsp 로 이동
	response.sendRedirect("index.jsp");
%>
</body>
</html>