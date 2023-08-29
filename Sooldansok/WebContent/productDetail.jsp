<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- 리뷰 조회를 위해 필요 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%@include file="header.jsp" %>

</head>
<body>

<div class="my_wrapper">

  <h2 class="pd_name">${stv.info_name}</h2>

  <div class="pdDetail_bx">
  
    <!-- 장바구니 담기 폼 -->
    <form id="addToBasketForm" action="BasketInsert.basket">
      <img class="product_detail" alt="" src="./images/${stv.img}"/>
      <dl>
        <input type="hidden" name="mem_id" value="${sessionScope.sid}" />
        <input type="hidden" name="pd_id" value="${stv.info_num}" /> <!-- 클릭하면 info_num 값이 컨트롤러로 전달됨 -->
        <dd>가격 : ${stv.info_price}원</dd>
        <dd>종류 : ${stv.kind}</dd>
        <dd>생산지 : ${stv.country}</dd>
        <dd>용량 : ${stv.capacity}ml</dd>
        <dd>도수 : ${stv.alcohol}%</dd>
        <dd>제작년도 : ${stv.made_year}년</dd>
        <dd>재고 : ${stv.stock}개</dd>
        <dd>
    <input type="number" id="cntInput" name="cnt" class="buy_soo"
        placeholder="수량 입력" required>
        <input type="button" value="구매하기" class="styled-button"
        onclick="purchase();">
    <input type="button" value="장바구니 담기" class="styled-button"
        onclick="addToBasket();">
    
  </dd>
      </dl>
      
       
    </form>

    <!-- 구매하기 폼 -->
    <form id="orderForm">
  <input type="hidden" name="mem_id" value="${sessionScope.sid}" />
  <input type="hidden" name="pd_id" value="${stv.info_num}" />
  <input type="hidden" name="pd_name" value="${stv.info_name}" />
  <input type="hidden" name="price" value="${stv.info_price}" />
  <input type="hidden" id="purchaseCnt" name="cnt" value="1"> <!-- 초기값은 1로 설정 -->
  
</form>

    
  </div> <!-- pdDetail_bx - end -->
 
 	<div id="board-list">
 		<div class="container">
 			<table class="board-table under_review">
 				<thead>
 					<tr> <!-- 일단 사진은 제외 -->
 						<th scope="col" class="th-rv-id">아이디</th>
	                    <th scope="col" class="th-rv-con">내용</th>	      
	                    <th scope="col" class="th-rv-star">별점</th>
 					</tr>
 				</thead>
 				<tbody>
 					<c:forEach var="pr" items="${pdRev}">
 						<tr>
 							<td class="th-rv-id">${pr.mem_id}</td>
 							<td class="th-rv-con">${pr.comments}</td>
 							<td class="th-rv-star">${pr.stars}</td>
 						</tr>
 					</c:forEach>
 				</tbody>
 			</table>
 		</div>
 	</div>
 
</div> <!-- my_wrapper - end -->




<script>
  // 장바구니 담기 폼에서 값이 변경될 때마다 구매하기 폼의 cnt 값을 업데이트합니다.
  function addToBasket() {
  if (validateCnt()) {
    if (confirm("상품을 장바구니에 담으시겠습니까?")) {
      // 입력한 수량을 가져옵니다
      var cntInputValue = parseInt($("#cntInput").val());
      $('#purchaseCnt').val(cntInputValue);

      // AJAX 요청
      var formData = $('#orderForm').serialize();
      formData += "&action=BasketAddTo.basket";
      $.ajax({
        type: "POST",
        url: "BasketAddTo.basket",
        data: formData + "&cnt=" + cntInputValue,
        dataType: "text",
        success: function (response) {
          console.log("Received response:", response);

          if (response === "success") {
            alert("상품이 장바구니에 담겼습니다");
          } else {
            alert("장바구니 담기 실패 다시 시도해주세요");
          }
        }
      });
    }
  } else {
    alert('수량을 1 이상 재고보다 작거나 같게 입력해주세요.');
  }
}

function purchase() {
  if (validateCnt()) {
    if (confirm("구매하시겠습니까?")) {
      // 입력한 수량을 폼 요소에 설정합니다
      var cntInputValue = $("#cntInput").val();
      $('#purchaseCnt').val(cntInputValue);
      
      $('#orderForm').attr('action', 'orderclient.client');
      $('#orderForm').submit();
    }
  } else {
    alert('수량을 1 이상 재고보다 작거나 같게 입력해주세요.');
  }
}


function validateCnt() {
  var cntInput = document.getElementById('cntInput');
  var stock = ${stv.stock};
  return cntInput.value !== '' && parseInt(cntInput.value) > 0 && parseInt(cntInput.value) <= stock;
}

</script>

<%@include file="footer.jsp" %>
</body>
</html>