<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function updateRequest() {
		alert('작성 내용으로 수정하시겠습니까?');
		var form = document.getElementById("myOneFrm");
		form.action = "RequestUpdate.request";
		form.submit();
	}
	function deleteRequest() {
		alert('해당 상품요청을 삭제하시겠습니까?');
		var form = document.getElementById("myOneFrm");
		form.action = "RequestDelete.request";
		form.submit();
	}
</script>
<meta charset="UTF-8">
<title>상품요청 수정</title>
</head>
<body>
	<h2 align="center">상품요청 수정</h2>
	<form id="myOneFrm" method="post">
		<table>
			<tr>
				<td colspan="5">
					<input type="reset" value="초기화">
				</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td><input type="text" value="${rmo.req_name}" name="req_name"></td>
			</tr>
			<tr>
				<td>원산지</td>
				<td><input type="text" value="${rmo.req_country}" name="req_country"></td>
			</tr>
			<tr>
				<td>생산연도</td>
				<td><input type="text" value="${rmo.req_made_year}" name="req_made_year"></td>
			</tr>
			<tr>
				<td>요청사항</td>
				<td><input type="text" value="${rmo.req_comment}" name="req_comment"></td>
			</tr>
			<tr>
				<td>주문상태</td>
				<td>${rmo.req_state}</td> <!-- 사용자가 변경 못함 -->
			</tr>
			<tr>
				<td>
					<input type="hidden" name="req_num" value="${rmo.req_num}">
				</td>	
			</tr>
			<tr>
				<td colspan="5">
					<input type="submit" name="udtSubmit" value="수정하기" onclick="updateRequest()">
					<input type="submit" name="delSubmit" value="삭제하기" onclick="deleteRequest()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>