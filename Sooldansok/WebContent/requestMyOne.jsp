<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="header.jsp"%>

<div class="my_wrapper">

   <div class="page-title">
         <div class="container">
            <h2 class = notice_board>상품요청 상세 조회</h2>
        </div>
   </div>
   
    <div class="qFrm_bx">
      <form action="RequestDelete.request" method="post" id="reqFrm">
      <input type="hidden" name="req_num" value="${rmo.req_num}">
         
         <div class=qFrm_table>
            <ul>
               <li class=qFrm_tit><b>제품명</b></li>
               <li>${rmo.req_name}</li>
            </ul>
            <ul>
               <li class=qFrm_tit><b>원산지</b></li>
               <li>${rmo.req_country}</li>
            </ul>
            <ul>
               <li class=qFrm_tit><b>생산연도</b></li>
               <li>${rmo.req_made_year}</li>
            </ul>
            <ul>
               <li class=qFrm_tit><b>요청사항</b></li>
               <li>${rmo.req_comment}</li>
            </ul>
            <ul>
               <li class=qFrm_tit><b>주문상태</b></li>
               <li>${rmo.req_state}</li>
            </ul>
         </div>
         
         
         
         <div class="qFrm_btns">
            <input type="submit" value="삭제하기" onclick="requestDelete(event)" class="in_btn mini_bk_btn">
         </div>
      </form>
   </div>
   
<script>
function requestDelete(event) {
	   event.preventDefault(); // 폼 제출 이벤트를 막음

	   var result = confirm("해당 상품 요청을 삭제하시겠습니까?");
	   if (result == true) {
	      var form = document.getElementById("reqFrm");
	      form.submit();
	      alert("해당 상품 요청이 삭제되었습니다.");
	   } else {
	      alert("상품 요청 삭제가 취소되었습니다.");
	   }
	}
</script>   
   
</div>
   
<%@include file="footer.jsp"%>   
</body>
</html>