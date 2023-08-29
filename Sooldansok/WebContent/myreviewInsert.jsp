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
				<h2 class=notice_board>상품후기 작성</h2>
			</div>
		</div>

		<form action="ReviewInsert.review" method="post" id="reviewinsertsubmit">
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
					<td  style="width:60vw;"><textarea name="reviewcontent" id="idtextarea" class="rv_tarea"
							onclick="this.select();" placeholder="내용을 입력하세요" value="${prevalue}"></textarea></td>
				</tr>
				<tr>
					<td class="rv_col3" colspan=4>별점 &nbsp; 
						<input type="radio" name="stars" value="5" checked="checked">
							<img src="./images/stars_5.png" class="rvstars">
						<input type="radio" name="stars" value="4">
							<img src="./images/stars_4.png" class="rvstars">
						<input type="radio" name="stars" value="3">
							<img src="./images/stars_3.png" class="rvstars">
						<input type="radio" name="stars" value="2">
							<img src="./images/stars_2.png" class="rvstars">
						<input type="radio" name="stars" value="1">
							<img src="./images/stars_1.png" class="rvstars">
					</td>
				</tr>
			</table>
			<div class="rvdetail_btn">
			<input type="submit" class="rv_insert_btn" value="작성완료" onclick="reviewinsertconfirm();">
			<input type="hidden" name="od_num" value="${ov.od_num}">
			</div>
			
		</form>
	</div>
	
	<script type="text/javascript">
        function reviewinsertconfirm() {
            var result = confirm("리뷰를 등록하시겠습니까?");
            if (result == true) {
                document.getElementById("reviewinsertsubmit").submit();
                // 확인
            } else {
                event.preventDefault(); // Prevent the default form submission
            	return;
            	// 취소
            }
        }
    </script>

</body>
</html>