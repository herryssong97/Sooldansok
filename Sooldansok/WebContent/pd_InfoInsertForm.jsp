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

		<span class="s_title">상품 추가</span>

		<form method="post" action="img.upload" enctype="multipart/form-data">
			<table class="cl_info">
				<tr class="pd_row">
					<td rowspan="9" class="pd_uploadimg">
						<img src="http://localhost:8080/Project2_v6/images/${img}" alt="" class="pd_image">
						<input type="hidden" value="${img}" name="img">
					</td>
					<td class="pd_col">상품명</td>
					<td class="input_pdinfo"><input type="text" name="info_name" class="pdinfo_text"></td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">가격</td>
					<td class="input_pdinfo"><input type="text" name="info_price" class="pdinfo_text"> 원</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">종류</td>
					<td class="input_pdinfo"><select name="kind" class="country-select">
							<option selected>종류 선택</option>
							<option>RED</option>
							<option>WHITE</option>
					</select></td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">생산지</td>
					<td class="input_pdinfo"><select name="country" class="country-select">
							<option selected>생산지 선택</option>
							<option>ITALY</option>
							<option>FRANCE</option>
							<option>CHILE</option>
							<option>SPAIN</option>
					</select></td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">용량</td>
					<td class="input_pdinfo"><input type="text" name="capacity" class="pdinfo_text"> ml</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">도수</td>
					<td class="input_pdinfo"><input type="text" name="alcohol" class="pdinfo_text"> %</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">수확년도</td>
					<td class="input_pdinfo"><input type="text" name="made_year" class="pdinfo_text"> 년</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">수량</td>
					<td class="input_pdinfo"><input type="text" name="stock" class="pdinfo_text"> 개</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">상품이미지</td>
					<td class="input_pdinfo"></td>
				</tr> 
			</table>
	
			<input type="file" name="file1" class="fileup">
			<div class="pd_up_btn_row">
			<input type="submit" value="추가" class="cl_li_btn">
			</div>
		</form>
	</div>
	
	<script>
        var isSubmitted = <%= request.getAttribute("isSubmitted") %>;
        if (isSubmitted) {
            alert("입력이 완료되었습니다.");
            window.location.href = "product.page";
        }
    </script>

	
</body>
</html>

