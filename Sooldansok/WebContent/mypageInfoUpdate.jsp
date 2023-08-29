<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>

<%@include file ="header.jsp" %>

<div class=my_wrapper>


<span class="s_title">회원 정보</span>

		<form action="mypageupdate.client" method="post">
		
			<table class="cl_info">
				<tr>
					<td class="col">아이디</td>
					<td class="input_info"><span class="updat_id">${getone.id}</span>
						<input type="hidden" name="id1" value="${getone.id}"></td>
				</tr>
				<tr>
					<td class="col">비밀번호</td>
					<td class="input_info"><input type="text" value="${getone.pw}" name="pw" class="clinfo_text"></td>
				</tr>
				<tr>
					<td class="col">이름</td>
					<td class="input_info"><input type="text" value="${getone.name}" name="name" class="clinfo_text"></td>
				</tr>
				<tr>
				<td class="col">생년월일</td>
				<td class="input_info"><input type="text" value="${getone.birth}" name="birth" class="clinfo_text"></td>
				</tr>
				<tr>
					<td class="col">주소</td>
					<td class="input_info"><input type="text" value="${getone.address}" name="address" class="clinfo_text"></td>
				</tr>
				<tr>
					<td class="col">이메일</td>
					<td class="input_info"><input type="text" value="${getone.email}" name="email" class="clinfo_text"></td>
				</tr>
				<tr>
					<td class="col">전화번호</td>
					<td class="input_info"><input type="text" value="${getone.phone}" name="phone" class="clinfo_text"></td>
				</tr>
			</table>
			<div class="cl_li_btn_row">
				<input type="submit" id="update" value="수정" class="cl_li_btn">
				<input type="button" value="초기화" onclick="location.href='getmyinfo.client'" class="cl_li_btn">
				<input type="button" value="취소" onclick="location.href='mypage.jsp'" class="cl_li_btn">
					<!-- (인덱스 => 마이페이지) 컨트롤러 받으면 컨트롤러 경로로 바꿀것! -->
			</div>
			
		</form>

</div>

</body>
</html>