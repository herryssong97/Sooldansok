<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%@include file ="header_mngr.jsp" %>


<body>
<%
  String title = request.getParameter("title");
  String comment = request.getParameter("comment");
  String answer = request.getParameter("answer");
  int num=Integer.parseInt(request.getParameter("num"));
%>
<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = notice_board>문의사항 답변</h2>
        </div>
    </div>


<div class=answer_div>
<form action="answer_update.question" method="post">
<table class="answer_board">
	<tr>
		<th class="answer_col1"><h4><%= title %></h4></th>
	</tr>
	<tr>
		<td class="answer_board"><%= comment %></td>
	</tr>
	<tr>
		<td class="answer_board"><textarea cols="128" rows="12" name="answer" id="idtextarea" onclick="this.select();"><%=answer %></textarea></td>
	</tr>
</table>
	<input type="submit" value="변경하기" id="searchone">
	<input type="hidden" value="<%=num %>" name="q_num">
</form>
</div>

</div>
</body>
</html>