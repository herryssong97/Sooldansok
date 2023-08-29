<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="USER.UserMgr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<%@include file ="header.jsp" %>


<div class=my_wrapper>


	<h2 class = company_name>아이디 찾기</h2>
<%
	request.setCharacterEncoding("UTF-8");
	String name1 = request.getParameter("name");
	String email1 = request.getParameter("email");
	UserMgr um = new UserMgr();
	String id1 = um.id_search2(name1, email1);//아이디를 디비에서 가져옴..실패시 널
%>





 <%
       if (id1 != null) {
      %>
      
      <div class=find_id_bx>
		<p style="font-family: 'SUITE-Regular';">
			<%=name1 %>님의 아이디는 <%=id1%> 입니다.
		<div class=modal_btn>
 			<input type="button" class="in_btn m_login_btn mini_bk_btn" value="로그인하러 가기" onclick="location.href='login.jsp'">
      		<input type="button" value="메인으로" class="in_btn m_login_btn mini_bk_btn" onclick="location.href='index.jsp'" value="회원가입">
      	</div>
		</p>      
      </div>
      
      <%
  } else {
 %>
 
 <div class=find_id_bx>
 	<p style="font-family: 'SUITE-Regular';">아이디 찾기를 실패하였습니다.</p>
 		<div class=modal_btn>
 			<input type="button" class="in_btn m_id_btn mini_bk_btn" value="다시 아이디 찾기" onclick="location.href='findId.jsp'">
      		<input type="button" class="join in_btn mini_bk_btn" id="join" onclick="location.href='join.jsp'" value="회원가입">
      		<input type="button" value="메인으로" class="in_btn m_login_btn mini_bk_btn" onclick="location.href='index.jsp'">
      	</div>
 </div>
 
  <%
  }
 %>
	
</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>