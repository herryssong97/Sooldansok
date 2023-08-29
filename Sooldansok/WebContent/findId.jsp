<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>
<%@include file ="header.jsp" %>


<!-- 모달 모음 -->
<%@include file ="modal.jsp" %> 

<div class=my_wrapper>

	<h2 class = company_name>아이디 찾기</h2>
	
	 <form action="findId_2.jsp" class="login" method="post" name="find_id_email">
          <input type="text" placeholder="이름" class="in" name=name id=name>
          <input type="text" placeholder="이메일" class="in" name=email id=email> 
     	
      	<div class=modal_btn>
      		<input type="button"  onClick="id_search2()" value="아이디 찾기" class="in_btn m_login_btn mini_bk_btn" id="findId">
      		<input type="button" class="join in_btn mini_bk_btn" id="join" onclick="location.href='join.jsp'" value="회원가입">
      	</div>
</form>
	
<script>
$(document).ready(function(){
	$('#findId').click(function(){
		let form = document.find_id_email;
		
		/*alert('ddddd');*/
		
		if(form.name.value.length < 1){
			/*alert("이름을 입력해주세요.");*/
			$('.modal9').show();
			$('#close9').click(function(e){
				$(this).parents('#modal').hide();
			});
			form.name.focus();
			return false;
		} 
		if(!form.email.value){
			/*alert("이메일을 입력해주세요.");*/
			$('.modal13').show();
			$('#close13').click(function(e){
				$(this).parents('#modal').hide();
			});
			form.email.focus();
			return false;
		}
		else{
			form.method = "post";
			form.action = "./findId_2.jsp"; //넘어간화면
			form.submit(); 
		}
	});
})

</script>
	
	
	

</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>