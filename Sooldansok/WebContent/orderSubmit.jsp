<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>결제 결과</title>

<style>

 
        
body {
    position: relative;
    background-image: url('./images/ordersubmit.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    font-family: '맑은 고딕', Arial, sans-serif;
    background-color: #f2f2f2;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    
}

.result-container {
    text-align: center;
}

h2 {
    font-size: 24px; 
}

table {
    border-collapse: collapse;
    margin: 30px auto;
    width: auto;
    table-layout: auto;
    background-color: #ffffff;
    
}

th, td {
    border: 1px solid #ddd;
    padding: 12px; 
    text-align: left;
    font-size: 18px; 
}

th {
    background-color: #ffffff;
    font-weight: bold;
}

.table-row:nth-child(even) {
   background-color: #ffffff;
}

.main-btn {
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

   .main-btn:hover {
      background-color: gray;
      color: black;
    }

</style>
<%request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");%>
</head>
<body>
<div class="result-container">
    <h2>주문이 완료 되었습니다.</h2>

    <table>
        <tr class="table-row">
            <th>주문번호:</th>
            <td><%= request.getParameter("merchant_uid") %></td>
        </tr>
        <tr class="table-row">
            <th>회원 이름:</th>
            <td><%= request.getParameter("buyer_name") %></td>
        </tr>
        <tr class="table-row">
            <th>주소:</th>
            <td><%= request.getParameter("buyer_addr") %></td>
        </tr>
        <tr class="table-row">
            <th>결제 금액:</th>
            <td><%= request.getParameter("paid_amount") %></td>
        </tr>
    </table>

<br><br>
    <a href="index.jsp" class="main-btn">메인화면</a>
    <a href="ordersfilter.page?orders_filter=MEM_ID&orders_search=${sid}&id=${sid}"><button class="main-btn">주문목록</button></a>
</div>


</body>
</html>
