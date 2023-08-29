<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<script>
/*    function updateClient() {
      var result = confirm("작성한 내용대로 수정하시겠습니까?");
      if (result == true) {
         var form = document.getElementById("myPageFrm");
         form.action = "mypageupdate.client";
         form.submit();
         alert("회원정보 수정이 완료되었습니다.");
      } else {
         alert("회원정보 수정이 취소되었습니다.");
      }
   } */
   function deleteClient() {
      var result = confirm("정말로 회원탈퇴 하시겠습니까?");
      if (result == true) {
         var form = document.getElementById("myPageFrm");
         form.action = "mypagedelete.client?id1=${getone.id}";
         form.submit();
         alert("회원탈퇴가 완료되었습니다.");
      } else {
         alert("회원탈퇴가 취소되었습니다.");
      }
   }
</script>
</head>
<body>

<%@include file ="header.jsp" %>
<%@include file ="modal.jsp" %> 

<div class=my_wrapper>

   <div class="page-title">
      <div class="container">
         <h2 class=notice_board>마이페이지</h2>
      </div>
   </div>

   
   <div class = "my_page_bx">
   
   <div class="board_changes"> <!-- 내 정보 수정, 장바구니, 주문목록, 게시글 관리 -->
         <a href="getmyinfo.client"><button class=board_change>내 정보 수정</button></a>
         <a href="BasketGetAll.basket?mem_id=${sid}"><button class=board_change>장바구니</button></a>
         <a href="ordersfilter.page?orders_filter=MEM_ID&orders_search=${sid}&id=${sid}"><button class=board_change>주문목록</button></a>
         <a href="questionfilter.page?question_filter=MEM_ID&question_search=${sid}&id=${sid}"><button class=board_change>게시글 관리</button></a>
   </div>
   
   <!-- 
      <div class=delevery_bx>
         <ul>
            <li class=select>배송 준비중</li>
            <li>배송중</li>
            <li>배송완료</li>
            <li>픽업예정</li>
            <li>픽업완료</li>
            <li>주문취소</li>
         </ul>
      </div> -->

      <div class = "clientinfo_bx my_page_bx">
      <form id="myPageFrm" name="myPageForm" method="post">
      <input type="hidden" name="id" value="${getone.id}">
            <ul>
               <li>기본 회원 정보</li>
               <li class="id-info">아이디 <span>${getone.id}</span></li>
               <li>비밀번호 <input type=password name="pw"></li>
               <li>이름   <input type=text value="${getone.name}" name="name"></li>
               <li class="birth-info">생년월일 <span>${getone.birth}</span></li>
               <li>주소 <input type=text value="${getone.address}" name="address"></li>
               <li>이메일 <input type=text value="${getone.email}" name="email"></li>
               <li>연락처 <input type=text value="${getone.phone}" name="phone"></li>
            </ul>
            <div>
            
               <button type="submit" onclick="updateClient()" id="update" value="수정" class="cl_li_btn mini_bk_btn class m_mp_btn">수정</button>         
               <button type="submit" onclick="deleteClient()" value="회원탈퇴" class="cl_li_btn mini_bk_btn">회원탈퇴</button>
            
            </div>
      </form>
      </div> 
   

   </div>

</div>
   
<%@include file ="footer.jsp" %>

</body>
</html>