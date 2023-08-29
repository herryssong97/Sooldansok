<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>



<%@include file="header_mngr.jsp"%>

    
<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = notice_board>공지사항 작성</h2>
        </div>
    </div>

<div class="notice_insertform">
<form action="NoticeInsert.notice" method="post">
<table class="notice_insertboard">
	<tr>
		<th><input type="text" name="title" style="width: 874px; height: 40px;" onclick="this.select();" value="제목을 입력하세요"></th>
	</tr>
	<tr>
		<td><textarea cols="128" rows="25" name="textarea1" id="idtextarea" onclick="this.select();" >내용을 입력하세요</textarea></td>
	</tr>
</table>
<input type=submit id="searchone" value="작성완료"></td>
</form>
</div>
	
</div>
</body>
</html>