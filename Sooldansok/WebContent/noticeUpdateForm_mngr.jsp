<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%
  String title = request.getParameter("title");
  String comment = request.getParameter("comment");
  int num=Integer.parseInt(request.getParameter("num"));
%>

<%@include file="header_mngr.jsp"%>

    
<div class=my_wrapper>

 <div class="page-title">
        <div class="container">
            <h2 class = notice_board>공지사항 수정</h2>
        </div>
    </div>

<div class="notice_updateform">
<form action="NoticeUpdate.notice" method="post">
<table class="notice_insertboard">
	<tr>
		<th><input type="text" name="title" value="<%= title %>" style="width: 874px; height: 40px;" ></th>
	</tr>
	<tr>
		<td><textarea cols="128" rows="25" name="textarea1" id="idtextarea" ><%= comment %></textarea></td>
	</tr>
</table>
	<input type="hidden" value="<%=num %>" name="n_num">
	<input type="submit" value="변경하기" id="searchone">
</form>
</div>

</div>
</body>
</html>