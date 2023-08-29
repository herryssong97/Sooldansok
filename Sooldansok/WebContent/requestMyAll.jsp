<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 요청상품 전체조회</title>
</head>
<body>
	<h2>내가 요청한 상품 리스트</h2>
	<table border="1">
		<!-- <tr>
			<td colspan=5>
				<form action="RequestMyAll.request"> 여길 눌러야 requestMyAll.jsp 로 이동
					.do
					<form action="Telallview" method="post">
					<input type="submit" value="전체 조회">
				</form>
			</td>
		</tr> -->
		<tr>
			<th>번호</th>
			<th>상품명</th>
			<th>원산지</th>
			<th>생산년도</th>
			<th>요청사항</th>
			<th>주문상태</th>
		</tr>
		
		<c:forEach var="i" items="${myReq}" varStatus="status">
			<tr>
				<td>
					<form action="RequestMyOne.request" method="post">
					<!-- 이 버튼을 눌렀을 때 이 튜플의 req_num 정보가 전달되어야 한다 -->
						<input type="hidden" name="req_num"  value="${i.req_num}">
						<button type="submit" name="seqBtn" value="${i.req_name}">
							${status.index+1}
						</button>
					</form>
                </td>
				<td>${i.req_name}</td>
				<td>${i.req_country}</td>
				<td>${i.req_made_year}</td>
				<td>${i.req_comment}</td>
				<td>${i.req_state}</td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>