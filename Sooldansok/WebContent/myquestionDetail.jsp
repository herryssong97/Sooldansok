<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%@include file="header.jsp"%>


<body>
	<div class=my_wrapper>

		<div class="page-title">
			<div class="container">
				<h2 class=notice_board>문의사항</h2>
			</div>
		</div>

		<table class="question_board">
			<tr>
				<th class="qsd_col1"><h5>번호</h5></th>
				<th class="qsd_col2"><h3>${stv.q_num}</h3></th>
				<th class="qsd_col3"><h5>제목</h5></th>
				<th class="qsd_col4"><h2>${stv.q_title}</h2></th>
				<th class="qsd_col5"><h5>내용</h5></th>
				<th class="qsd_col6"><h3>${stv.q_date}</h3></th>
			</tr>
			<tr>
				<td class="question_board" colspan="6">${stv.q_comment}</td>
			</tr>
			<tr>
				<td class="question_board" colspan="6">${stv.answer}</td>
			</tr>
		</table>
	</div>

	<div class=question_update>
		<a href="QuestionDelete.question?q_num=${stv.q_num}">
			<button class=question_button>삭제하기</button>
		</a>
	</div>

</body>
</html>