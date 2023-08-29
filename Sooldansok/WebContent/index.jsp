<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와인판매 홈</title>

<%@include file ="header.jsp" %>


<div class=my_wrapper>

		<div id = banner>
		
			<ul class = "bxslider">
				<li><img class = b_img alt="" src="./images/wine1.jpg"></li>	
				<li><img class = b_img alt="" src="./images/wine2.jpg"></li>
				<li><img class = b_img alt="" src="./images/wine3.jpg"></li>
			</ul>
      
		</div> <!-- div#banner-end -->
		
</div>	

	<div class = centerBtn>
		
		<ul>
			<li><a class="btn" id="fst" href="productall.page">둘러보기</a></li>
			<li><a class="btn" id="scd"  href="notice.page">공지사항</a></li>
		</ul>
		
	</div>


	
	
	
<%@include file ="footer.jsp" %>

