<%@ page import="telInfoVO.BasketVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

    <meta charset="UTF-8">
    <title>주문 페이지</title>
    <style>
         header,
        footer {
        
            background-color: black;
          left:0;
            color: white;
            width: 100%;
            height : 20vh;
            text-align: center;
            font-family: 'Diphylleia', serif;
            position: fixed;
          
        }
        
        /* header에만 top 0 추가 */
        header {
            top: 0;
              
        }

        /* footer에만 bottom 0 추가 */
        footer {
            bottom: 0;
        }
        
        h1 {
	font-size : 30px;
	padding-top: 50px;
	text-align : center;
	height : auto;
	font-weight:normal;
	color:#fff;
}
    
    body {
            padding-top: 20vh; /* 헤더의 높이와 동일한 값을 설정하여 헤더 아래로 내용이 시작되도록 함 */
        }
    button[type="button"] { 
    background-color: black; 
    color: white; 
    font-size: 16px; 
    padding: 10px 20px; 
    border: none; 
    cursor: pointer; 
    text-align: center; 
    text-decoration: none; 
    display: inline-block; 
    margin: 4px 2px; 
    transition-duration: 0.4s; 
    } 
    
    button[type="button"]:hover { 
    background-color: gray; 
    color: black; 
    } 
        table {
      
            width: 40%;
            border-collapse: collapse;
          
            
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f3f3f3;
            font-weight: normal;
        }
        
        .total-price {
        font-weight: bold;
    }
    
     .free-shipping-info {
        color: #888; /* 글자 색상을 조금 흐리게 설정 */
        font-size: 0.75rem; /* 글자 크기를 0.8배 감소시킴 */
    }
   .order-container {
       display: flex;
       flex-direction: column; /* 추가 */
       align-items: center; /* 추가 */
    }
    
    .order-form-container { /* 추가 */
    
       display: flex;
       flex-direction: column; /* 추가 */
       align-items: center; 테이블 크기와 동일하게 설정 */
    }
 .order-form-container2 { 
        margin-top: 20px; 
        width: 40%; 
         display: flex;
        justify-content: space-between;
        gap: 10px;
        margin-bottom: 20px;
        
    }
    
        .order-item-center {
        justify-content: center;
         margin-bottom: 100px;
    }
    </style>
    <%request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");%>
</head>
<body>
<%
   String name = request.getParameter("name");
   String address = request.getParameter("address");
   String phone = request.getParameter("phone");
  String email = request.getParameter("email");
%>

<header> <h1>주문 상품 목록</h1></header>
     
   
  <br><br>
    <div class="order-container">
        <table border="1">
            <tr>
                
                <th>상품번호</th>
                <th>아이디</th>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
                <th>합계</th>         
<input type="hidden" name="mem_id" value="${sessionScope.sid }">
            </tr>
            
    <%
    int totalPrice = 0;
    String mem_id = request.getParameter("mem_id");

    String pd_id = request.getParameter("pd_id");
    int cnt = request.getParameter("cnt") != null ? Integer.parseInt(request.getParameter("cnt")) : 1; // 기본값으로 1 설정
    String pd_name = request.getParameter("pd_name");

    
    String price = request.getParameter("price").replaceAll(",","");
  
    int tmpPrice = Integer.parseInt(price) * cnt;
    totalPrice += tmpPrice;
%>
            <tr>
        
      
        <td><%= pd_id %></td>
        <td><%= mem_id %></td>
        <td><%= pd_name %></td>
        <td><%= price %></td>
        <td><%= cnt %></td>
        <td><%= tmpPrice %></td>
    </tr>
        </table>
        <br />
 <div class="order-form-container2">  
      <form id="orderForm" action="OrderSubmit.jsp" method="post">
        이름<input type="text" name="name" value="${getone.name}"><br>	    
       주소<input type="text" name="address" value="${getone.address}"><br>
       핸드폰<input type="text" name="phone" value="${getone.phone}"><br>    
       이메일<input type="text" name="email" value="${getone.email}"><br>
        <input type="hidden" name="pd_id" value="<%= pd_id %>">
        <input type="hidden" name="pd_name" value="<%= pd_name %>">
        <input type="hidden" name="cnt" value="<%= cnt %>">
        <input type="hidden" name="mem_id" value="<%= mem_id %>">
        <input type="radio" name="deliverymethod" value="배송준비중" checked>배송
        <input type="radio" name="deliverymethod" value="픽업예정">픽업<br>
           <br/>
           <span class="free-shipping-info">●100,000원 이상 주문시 배송비 무료<br><br></span>
  상품합계: <%= totalPrice %>원<br><br>
    배송비: <span id="deliveryFee">0</span>원<br><br>
    총 결제금액: <span id="totalAmount"><%= totalPrice %></span>원<br><br>
    </div>
 <div class="order-item-center"> 
                        <button type="button" id="payButton">결제하기</button>
                    </div>
        
      </form>
      </div>

<script>
  $(document).ready(function () {
	  function updateTotalAmount() {
	        var delivery_method = $('input[name="deliverymethod"]:checked').val();

	        if (delivery_method == "배송준비중" && initial_amount < 100000) {
	            delivery_fee = 2500;
	        } else {
	            delivery_fee = 0;
	        }

	        total_amount = initial_amount + delivery_fee;
	        $("#deliveryFee").text(delivery_fee);
	        $("#totalAmount").text(total_amount);
	    }

	    $('input[name="deliverymethod"]').on("change", updateTotalAmount);

	    var initial_amount = parseInt(<%= totalPrice %>, 10);
	    var delivery_fee = 0;
	    var total_amount = initial_amount;

	    updateTotalAmount();
    IMP.init("imp76264221"); 

    $("#payButton").on("click", function () {
      // Form 입력값 가져오기
      var buyer_email = $("input[name='email']").val();
      var buyer_name = $("input[name='name']").val();
      var buyer_tel = $("input[name='phone']").val();
      var buyer_addr = $("input[name='address']").val();
      var mem_id = $("input[name='mem_id']").val();
      var cnt = $("input[name='cnt']").val();
      var pd_id = $("input[name='pd_id']").val();
      var pd_name = $("input[name='pd_name']").val();
      var delivery = $('input[name="deliverymethod"]:checked').val();
      if (delivery == "delivery" && total_amount < 100000) {
          delivery_fee = 2500;
      } else if (delivery == "픽업예정") {
          delivery_fee = 0;
      }
     /*  total_amount = initial_amount + delivery_fee; */
      
updateTotalAmount();

      IMP.request_pay({
        pg: "html5_inicis", // PG사
        pay_method: "card", // 결제 수단
        merchant_uid: "ORD_" + new Date().getTime(), // 상점에서 관리하는 고유 주문 번호
        name: pd_name, // 주문명
        amount: total_amount, // 결제 금액 수정
        buyer_email: buyer_email, // 구매자 이메일
        buyer_name: buyer_name, // 구매자 이름
        buyer_tel: buyer_tel, // 구매자 전화번호
        buyer_addr: buyer_addr, // 구매자 주소
        buyer_postcode_meta: "06018", // 구매자 우편번호
        delivery: delivery,
        cnt: cnt,
        pd_id:pd_id,
        mem_id:mem_id

      }, function (rsp) {
        if (rsp.success) {
                $.ajax({
                  url: "orderoneinsert.orders",
                  method: "POST",
                  data: {
                    imp_uid: rsp.imp_uid,
                    merchant_uid: rsp.merchant_uid,
                    name: rsp.pd_name,
                    paid_amount: rsp.paid_amount,
                    apply_num: rsp.apply_num,
                    delivery: delivery,
                    cnt:cnt,
                    pd_id:pd_id,
                    mem_id:mem_id,
                    buyer_addr: rsp.buyer_addr,
    		        buyer_name: rsp.buyer_name
                  }
                }).done(function (data) {
                  document.write(data); // AJAX 요청이 성공한 경우 처리
                }).fail(function (jqXHR, textStatus, errorThrown) {
                  console.log("AJAX request failed:", textStatus, errorThrown); // AJAX 요청이 실패한 경우 에러 처리
                });
              
            } else {
              alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
            }
          });
        });
      });
</script>
    </div>
</body>
</html>