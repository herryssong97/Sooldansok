<%@ page import="telInfoVO.BasketVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 관리</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   
    <style>
    
       header {
	position: fixed;
	z-index:3;
	top : 0;
	left: 0;
	width: 100%;
	height : 20vh;
	background-color:#000;
	font-family: 'Diphylleia', serif; /* 폰트설정 */
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
            font-family: Arial, sans-serif;
            
        }

        .my_wrapper {
            max-width: 800px;
            margin: 0 auto;
         
  padding-top: 20vh;
  padding-bottom: 20vh; 

        }

        span {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            display: inline-block;
            text-align: center;

        }

        table {
            width: 100%;
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

        .action-btn {
            background-color: black;
            color: white;
            font-size: 16px;
            padding: 10px;
            border: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 4px 2px;
            transition-duration: 0.4s;
        }

        .action-btn:hover {
             background-color: gray;
            color: black;
        }

        .total-wrapper {
            float: left;
        }

        .buy-btn-wrapper {
            float: right;
        }

        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }
        .button-container {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        .total-container {
            float: right;
        }
        .center-span{
            text-align: center;
        }

        .actions {
            display: none;
        }
    </style>

    <script>
    $(document).ready(function() {
    	  var tableRows = document.getElementById("basketTable").getElementsByTagName("tr").length;
    	  if (tableRows <= 1) {
    	    document.getElementById("basketTable").style.display = "none";
    	    document.getElementById("empty-basket").style.display = "block";
    	    
    	    // 버튼들을 숨김
    	    document.getElementById("total").style.display = "none";
    	    document.getElementById("toggleSelectAllBtn").style.display = "none";
    	    document.getElementById("buyBtn").style.display = "none";
    	    document.querySelector(".delete-btn-wrapper > input").style.display = "none";
    	  } else {
    	    $(".actions").show();
    	  }
    	});

        function showMessage() {
            var message = "${msg}";
            if (message) {
                alert(message);
            }
        }

        var allSelected = false;

        function toggleSelectAllButton() {
            allSelected = !allSelected;
            var checkboxes = document.getElementsByName("selectedBasNums");

            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = allSelected;
            }
            calculateTotal(); 
        }

        function calculateTotal() {
            let total = 0;
            $("input[name='selectedBasNums']:checked").each(function() {
                const rowIndex = $(this).closest('tr').index() - 2; 
                let subTotal = $("#totPrice" + rowIndex).text();
                subTotal = parseFloat(subTotal.replace(/,/g, '')); 
                total += subTotal;
            });

            $("#total").text("총합: " + total.toLocaleString()+ "원");
        }

        function buyButton() {
            const checkedItems = $("input[name='selectedBasNums']:checked");
            if (checkedItems.length < 1) {
                alert("구매할 상품을 선택해주세요.");
                return;
            }

            const form = $('<form method="post" action="orderPage.jsp"></form>');
            checkedItems.each(function(index, element) {
                const rowIndex = $(element).closest('tr').index()-1;
                const bas_num = $("#basketTable tr:eq(" + rowIndex + ") td:eq(1)").text();
                const pd_id = $("#basketTable tr:eq(" + rowIndex + ") td:eq(2)").text();
                const mem_id = $("#basketTable tr:eq(" + rowIndex + ") td:eq(3)").text();
                const pd_name = $("#basketTable tr:eq(" + rowIndex + ") td:eq(4)").text();
                const cnt = $("#basketTable tr:eq(" + rowIndex + ") td:eq(5)").text();
                const price = $("#basketTable tr:eq(" + rowIndex + ") td:eq(6)").text();

                form.append('<input type="hidden" name="selectedBasNum' + index + '" value="' + bas_num + '" />');
                form.append('<input type="hidden" name="selectedMemId' + index + '" value="' + mem_id + '" />');
                form.append('<input type="hidden" name="selectedPdName' + index + '" value="' + pd_name + '" />');
                form.append('<input type="hidden" name="selectedPrice' + index + '" value="' + price + '" />');
                form.append('<input type="hidden" name="selectedCnt' + index + '" value="' + cnt + '" />');
                form.append('<input type="hidden" name="selectedPd_id' + index + '" value="' + pd_id + '" />');

            });
            form.append('<input type="hidden" name="numOfCheckedItems" value="' + checkedItems.length + '" />');
            form.append('<input type="hidden" name="name" value="${clist1.name}" />');
            form.append('<input type="hidden" name="address" value="${clist1.address}" />');
            form.append('<input type="hidden" name="phone" value="${clist1.phone}" />');
            form.append('<input type="hidden" name="email" value="${clist1.email}" />');

            $('body').append(form);
            form.submit();
        }


        function delete_selected_items(event) {
            event.preventDefault(); // 삭제를 확인하지 않고 폼 제출을 막습니다.

            if($("input[name='selectedBasNums']:checked").length < 1) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            // 삭제 확인 후, 폼을 제출합니다.
            if(confirm("선택한 항목을 삭제하시겠습니까?")) {
                document.getElementById("basket-form").submit();
            }
        }
    </script>
</head>
<header>
        <h1 id = head>
			장바구니
		</h1>
			
    </header>
<body onload="showMessage()">

    <div class="my_wrapper">
       

        <div id="empty-basket" style="display: none; text-align: center;">
            <img src="./images/basketempty.jpg"  style="width: 100%; height: auto;" />
            <div>
                <button class = "action-btn" onclick="location.href='productall.page'">장바구니 담으러 가기</button>
            </div>
        </div>
        <form id="basket-form" method="post" action="BasketDeleteSelected.basket">
            <div class="button-container">
                <div class="select-all-btn-wrapper">
                    <button type="button" class="action-btn" id="toggleSelectAllBtn" onclick="toggleSelectAllButton()">전체선택</button>
                </div>
                <div class="delete-btn-wrapper">
                    <input type="submit" class="action-btn" value="선택한 항목 삭제" onclick="delete_selected_items(event)" />
                </div>
            </div><br>

            <table class="list_mngr" border="1" id="basketTable">
                <colgroup>
                    <col class="col1">
                    <col class="col2">
                    <col class="col3">
                    <col class="col4">
                    <col class="col5">
                    <col class="col6">
                    <col class="col7">
                    <col class="col8">
                </colgroup>
                <tr>
                    <th>선택</th>
                    <th>No.</th>
                    <th>상품번호</th>
                    <th>아이디</th>
                    <th>상품명</th>
                    <th>수량</th>
                    <th>가격</th>
                    <th>합계</th>
                </tr>
                <input type="hidden" name="mem_id" value="${sessionScope.sid}" />

                <c:forEach var="i" items="${requestScope.alist1}" varStatus="status">
                    <tr>
                        <td><input type="checkbox" name="selectedBasNums" value="${i.bas_num}" onchange="calculateTotal()" /></td>
                        <td>${i.bas_num}</td>
                        <td>${i.pd_id}</td>
                        <td>${i.mem_id}</td>
                        <td>${i.pd_name}</td>
                        <td>${i.cnt}</td>
                        <td>${i.price}</td>
                        <td id="totPrice${status.index}">${i.totprice}</td>
                    </tr>
                </c:forEach>
            </table>

            <div class="total-buy-container clearfix">
                <div class="total-wrapper">
                    <br><span id="total">총합: 0원</span>
                </div>
                <div class="buy-btn-wrapper">
                    <button type="button" class="action-btn" id="buyBtn" onclick="buyButton()">구매하기</button>
                </div>
            </div>
        </form>
    </div>
    
</body>
</html>
