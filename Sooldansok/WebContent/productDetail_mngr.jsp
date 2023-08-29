<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>


<%@include file="header_mngr.jsp"%>


<div class="my_wrapper">

	<span class="s_title">상품 정보</span>

	<div>

		<form action="Pd_InfoUpdate.product" method="post">
			<table class="cl_info">
				<tr class="pd_row">
					<td rowspan="9" class="pd_uploadimg">
						<img src="http://localhost:8080/Project2_final_v9/images/${stv.img}" alt="" class="pd_image" style="width: 240px; height: 360px;">
						<input type="hidden" value="${img}" name="img">
					</td>
					<td class="pd_col">상품명</td>
					<td class="input_pdinfo"><input type="text" value="${stv.info_name}" name="info_name" class="pdinfo_text"></td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">가격</td>
					<td class="input_pdinfo"><input type="text" value="${stv.info_price}" name="info_price" class="pdinfo_text"> 원</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">종류</td>
					<td class="input_pdinfo"><select name="kind" class="country-select">
							<option value="RED" ${stv.kind eq 'RED' ? 'selected' : ''}>RED</option>
							<option value="WHITE" ${stv.kind eq 'WHITE' ? 'selected' : ''}>WHITE</option>
					</select></td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">생산지</td>
					<td class="input_pdinfo"><select name="country" class="country-select">
							<option value="ITALY" ${stv.country eq 'ITALY' ? 'selected' : ''}>ITALY</option>
						<option value= ${stv.country eq 'FRANCE' ? 'selected' : ''}>FRANCE</option>
						<option value="CHILE" ${stv.country eq 'CHILE' ? 'selected' : ''}>CHILE</option>
						<option value="SPAIN" ${stv.country eq 'SPAIN' ? 'selected' : ''}>SPAIN</option>
					</select></td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">용량</td>
					<td class="input_pdinfo"><input type="text" value="${stv.capacity}" name="capacity" class="pdinfo_text"> ml</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">도수</td>
					<td class="input_pdinfo"><input type="text" value="${stv.alcohol}" name="alcohol" class="pdinfo_text"> %</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">생산년도</td>
					<td class="input_pdinfo"><input type="text" value="${stv.made_year}" name="made_year" class="pdinfo_text"> 년</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">수량</td>
					<td class="input_pdinfo"><input type="text" value="${stv.stock}" name="stock" class="pdinfo_text"> 개</td>
				</tr>
				<tr class="pd_row">
					<td class="pd_col">상품이미지</td>
					<td class="input_pdinfo"></td>
				</tr>
			</table>

				<input type="hidden" value="${stv.info_num}" name="info_num">
				<input type="submit" value="변경하기" onclick="return confirm('변경하시겠습니까?')" class="pd_in_btn1">
		</form>

		<form method="post" action="img.imgchange" enctype="multipart/form-data">
			<input type="file" name="file1" class="fileup">
			<input type="hidden" value="${stv.info_num}" name="info_num">
			<input type="submit" value="적용" class="imgsubmit">
		</form>

		<form action="Pd_InfoDelete.product" method="post" id="delete-form">
			<input type="hidden" value="${stv.info_num}" name="info_num">
			<input type="submit" value="삭제하기"
				onclick="return confirm('삭제하시겠습니까?')" class="pd_in_btn2">
		</form>
	</div>


</div>



</body>
</html>

