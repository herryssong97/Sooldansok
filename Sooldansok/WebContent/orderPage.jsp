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
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>

    <meta charset="UTF-8">
    <title>주문 페이지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
        color: #888; 
        font-size: 0.75rem; 
    }
   .order-container {
       display: flex;
       flex-direction: column; 
       align-items: center; 
    }
    
    
 .order-form-container2 { 
        margin-top: 20px; 
        width: 40%; 
         display: flex;
         
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
                <th>No.</th>
                <th>상품번호</th>
                <th>아이디</th>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
                <th>합계</th>
                <input type="hidden" name="mem_id" value="${sessionScope.sid }">
           
            <%!
                int numOfCheckedItems = 0;
            String PD_name = "";
            %>
            <%
            
            if (numOfCheckedItems > 0) {
            	PD_name += request.getParameter("selectedPdName" + (numOfCheckedItems - 1));
            }
                if (request.getParameter("numOfCheckedItems") != null) {
                    numOfCheckedItems = Integer.parseInt(request.getParameter("numOfCheckedItems"));
                }
            %>
            <%int totalPrice = 0;
            for (int i = 0; i < numOfCheckedItems; i++) {
                String bas_num = request.getParameter("selectedBasNum" + i);
                String mem_id = request.getParameter("selectedMemId" + i);
                String pd_name = request.getParameter("selectedPdName" + i);
                PD_name += pd_name + ", ";
                String price = request.getParameter("selectedPrice" + i).replaceAll(",", ""); // 쉼표 제거
                int cnt = Integer.parseInt(request.getParameter("selectedCnt" + i));           
                int tmpPrice = Integer.parseInt(price) * cnt;
                totalPrice += tmpPrice;
                int pd_id = Integer.parseInt(request.getParameter("selectedPd_id" + i));
                
               
         %>
            <tr>
                <td><%= bas_num %></td>
                <td><%= pd_id %></td>
                <td><%= mem_id %></td>
                <td><%= pd_name %></td>
                <td><%= price %></td>
                <td><%= cnt %></td>
                <td><%= tmpPrice %></td> <!-- 여기에 합계 금액 표시 -->
            </tr>
            <% } %>
        </tbody>
        </table>
     
        
<div class="order-form-container2">  
      <form id="orderForm" action="OrderSubmit.jsp" method="post">
        이름<input type="text" name="name" value="<%= name %>"><br>
      주소<input type="text" name="address" value="<%= address %>"><br>
       핸드폰<input type="text" name="phone" value="<%= phone %>"><br> 
       이메일<input type="text" name="email" value="<%= email %>"><br>
   
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
      var buyer_email = $("input[name='email']").val();
      var buyer_name = $("input[name='name']").val();
      var buyer_tel = $("input[name='phone']").val();
      var buyer_addr = $("input[name='address']").val();
      var mem_id = $("input[name='mem_id']").val();
      var delivery = $('input[name="deliverymethod"]:checked').val();
     
      if (delivery == "delivery" && total_amount < 100000) {
          delivery_fee = 2500;
      } else if (delivery == "픽업예정") {
          delivery_fee = 0;
      }

      
updateTotalAmount();
    

      var orders = [];

      <% for (int i = 0; i < numOfCheckedItems; i++) {
          String cnt = request.getParameter("selectedCnt" + i);
          String pd_id = request.getParameter("selectedPd_id" + i);
          String bas_num = request.getParameter("selectedBasNum" + i);
         
      %>
      var cnt<%= i %> = <%= cnt %>;
      var pd_id<%= i %> = <%= pd_id %>;
      var bas_num<%= i %> = <%= bas_num %>;
      orders.push({
        cnt: cnt<%= i %>,
        pd_id: pd_id<%= i %>,
        bas_num: bas_num<%= i %>
       
      });
      <% } %>



       IMP.request_pay({
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: "ORD_" + new Date().getTime(),
        name:  '<%= PD_name %>',
        amount: total_amount ,
        buyer_email: buyer_email,
        buyer_name: buyer_name,
        buyer_tel: buyer_tel,
        buyer_addr: buyer_addr,
        buyer_postcode: "06018",
        mem_id: mem_id,
        delivery: delivery,
      }, function (rsp) {
    	  if (rsp.success) {
    		  var requests = []; // 요청을 담을 빈 배열

    		  for (var i = 0; i < orders.length; i++) {
    		    // 각 주문에 대한 요청을 배열에 추가
    		    var request = $.ajax({
    		      url: "orderinsert.orders",
    		      method: "POST",
    		      data: {
    		        imp_uid: rsp.imp_uid,
    		        merchant_uid: rsp.merchant_uid,
    		        name: rsp.name,
    		        paid_amount: rsp.paid_amount,
    		        apply_num: rsp.apply_num,
    		        pay_method: rsp.pay_method,
    		        mem_id: mem_id,
    		        buyer_addr: rsp.buyer_addr,
    		        buyer_name: rsp.buyer_name,
    		        delivery: delivery,
    		        cnt: orders[i].cnt,
    		        pd_id: orders[i].pd_id,
    		        bas_num: orders[i].bas_num
    		      }
    		    });
    		    requests.push(request);
    		  }

    		  // 모든 요청이 완료되면
    		  Promise.all(requests).then(function (responses) {
    		    // 주문 완료 페이지 orderSubmit.jsp로 이동
    			  var form = document.createElement("form");
    			    form.setAttribute("method", "POST");
    			    form.setAttribute("action", "orderSubmit.jsp");
    			    
    			    var merchant_uid = document.createElement("input");
    			    merchant_uid.setAttribute("type", "hidden");
    			    merchant_uid.setAttribute("name", "merchant_uid");
    			    merchant_uid.setAttribute("value", rsp.merchant_uid);
    			    
    			    var buyer_name = document.createElement("input");
    			    buyer_name.setAttribute("type", "hidden");
    			    buyer_name.setAttribute("name", "buyer_name");
    			    buyer_name.setAttribute("value", rsp.buyer_name);
    			    
    			    var buyer_addr = document.createElement("input");
    			    buyer_addr.setAttribute("type", "hidden");
    			    buyer_addr.setAttribute("name", "buyer_addr");
    			    buyer_addr.setAttribute("value", rsp.buyer_addr);

    			    var paid_amount = document.createElement("input");
    			    paid_amount.setAttribute("type", "hidden");
    			    paid_amount.setAttribute("name", "paid_amount");
    			    paid_amount.setAttribute("value", rsp.paid_amount);
    			    
    			    form.appendChild(merchant_uid);
    			    form.appendChild(buyer_name);
    			    form.appendChild(buyer_addr);
    			    form.appendChild(paid_amount);

    			    document.body.appendChild(form);

    			    form.submit();
    		  }).catch(function (error) {
    		    console.log("AJAX request failed:", error);
    		  });
    		} else {
    		  alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
    		}
          });
        });
      }); 
</script>
    
</body>
</html>
