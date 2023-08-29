<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%@include file="header.jsp"%>


	<div class=my_wrapper>

		<div class="page-title">
			<div class="container">
				<h2 class=notice_board>상품후기</h2>
			</div>
		</div>

		<form action="ReviewMyOne1.review?OD_NUM=${ov.od_num}&REV_NUM=${rv.rev_num}" method="post" id="reviewinsertsubmit">
			<table class="review_insertboard">
				<tr>
					<td class="rv_col1">주 문 번 호</td>
					<td class="rv_col2">${ov.od_num}</td>
					<td class="rv_col1">주 &nbsp; 문 &nbsp; 일</td>
					<td class="rv_col2">${ov.od_date}</td>
				</tr>
				<tr>
					<td class="rv_col1">상 &nbsp; 품 &nbsp; 명</td>
					<td class="rv_col2">${ov.info_name}</td>
					<td class="rv_col1">개 당 가 격</td>
					<td class="rv_col2">${ov.info_price}</td>
				</tr>
				<tr>
					<td class="rv_col1">종 &nbsp; &nbsp; &nbsp; &nbsp; 류</td>
					<td class="rv_col2">${ov.kind}</td>
					<td class="rv_col1">생 &nbsp; 산 &nbsp; 지</td>
					<td class="rv_col2">${ov.country}</td>
				</tr>
				<tr>
					<td class="rv_col1">도 &nbsp; &nbsp; &nbsp; &nbsp; 수</td>
					<td class="rv_col2">${ov.alcohol}</td>
					<td class="rv_col1">제 작 년 도</td>
					<td class="rv_col2">${ov.made_year}</td>
				</tr>
				<tr>
					<td  style="width:60vw;"><div id="idtextarea" class="rv_tarea">${rv.comments}</div></td>
				</tr>
				<tr>
					<td class="rv_col3" colspan=4>별점 &nbsp; &nbsp; 
							<img src="./images/stars_${rv.stars}.png" class="rvstars">
					</td>
				</tr>
			</table>
			<div class="rvdetail_2btn">
				<input type="submit" class="rv_insert_btn" value="수정하기">
				<input type="button" class="rv_insert_btn" value="삭제하기" onclick="reviewdeleteconfirm();">
			</div>
			
		</form>
	</div>
	
	<script type="text/javascript">
        function reviewdeleteconfirm() {
            var result = confirm("리뷰를 삭제하시겠습니까?");
            if (result == true) {
                location.href='ReviewDelete.review?rev_num=${rv.rev_num}';
            } else {
            	
            } // 취소
        }
        // 
    </script>

</body>
</html>