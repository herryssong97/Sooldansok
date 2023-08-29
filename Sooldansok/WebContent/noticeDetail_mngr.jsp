<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file ="header_mngr.jsp" %>

<body>
<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = notice_board>공지사항</h2>
        </div>
    </div>


<div class="notice_div">
    <table class="notice_board"  >

	<tr>
	    <th class="ntd_col1"><h4>${stv.n_num}</h4></th>
	    <th class="ntd_col2"><h2>${stv.n_title}</h2></th>
	    <th class="ntd_col3"><h4>${stv.n_date}</h4></th>
	</tr>
	<tr>
		<td class="notice_board" colspan="3">${stv.n_comment}</td>
	</tr>	
</table> 
</div>

<div class=notice_update>
		<a href="noticeUpdateForm_mngr.jsp?comment=${stv.n_comment}&title=${stv.n_title}&num=${stv.n_num}">
	<button class = notice_button>
		수정하기			
	</button></a>

	<a href="NoticeDelete.notice?n_num=${stv.n_num}">
	<button class = notice_button>
		삭제하기			
	</button></a>
</div>



</div>



</body>
</html>