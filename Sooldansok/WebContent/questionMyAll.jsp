<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 문의사항</title>
</head>
<body>
	<h2>내가 쓴 문의사항</h2>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th> <!-- 일련번호 -->
			<th>내용</th>
			<th>답변</th>
			<th>최종 수정한 날짜</th>
			<th>답변 날짜</th>
		</tr>
		
		<c:forEach var="q" items="${myQ}" varStatus="status">
			<tr>
				<td>
					<form action="QuestionMyOne.question" method="post">
					<!-- 이 버튼을 눌렀을 때 이 튜플의 q_num 정보가 전달되어야 한다 -->
						<input type="hidden" name="q_num"  value="${q.q_num}">
						<button type="submit" name="seqBtn" value="${q.q_title}">
							${status.index+1}
						</button>
					</form>
                </td>
				<td>${q.q_title}</td>
				<td>${q.q_comment}</td>
				<td>${q.answer}</td>
				<td>${q.q_date}</td>
				<td>${q.answer_date}</td>
			</tr>
		</c:forEach>
	</table>
	<table border=0>
		<tr>
			<td><a href="questionInsertForm.jsp">[새 문의사항 작성]</a></td>
		</tr>
	</table>
</body>
</html>