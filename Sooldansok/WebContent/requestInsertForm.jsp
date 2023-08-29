<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="header.jsp"%>

<div class="my_wrapper">

   <div class="page-title">
         <div class="container">
            <h2 class = notice_board>상품요청 입력</h2>
        </div>
   </div>

   <div class="qFrm_bx">
      <form action="RequestInsert.request" method="post" id="reqFrm">
         <div class=qFrm_table>
            <ul>
               <li class="qFrm_tit"><b>상품명</b></li>
               <li><input type="text" name="req_name"></li>
            </ul>
            <ul>
               <li class="qFrm_tit"><b>원산지</b></li>
               <li>
                  <select name="req_country">
                     <option value="selectCountry" selected>원산지 선택</option>
                     <option id="france" value="france">프랑스</option>
                     <option id="italy" value="italy">이탈리아</option>
                     <option id="spain" value="spain">스페인</option>
                     <option id="chile" value="chile">칠레</option>
                  </select>
               </li>                                          
            </ul>
            <ul>
               <li class="qFrm_tit"><b>생산연도</b></li>
               <li><input type="text" name="req_made_year"></li>
            </ul>
            <ul>
               <li class="qFrm_tit"><b>요청사항</b></li>
               <li><textarea name="req_comment" rows="5" cols="50" onFocus="this.select()">요청사항을 적어주세요.</textarea>
               </li>
            </ul>
         </div>
         <br>
         <div  class="qFrm_btns">
            <input type="submit" value="요청하기" onclick="requestInsert()" class="in_btn mini_bk_btn">
         </div>
      </form>
   </div>
</div>

<script>
   function requestInsert() {
      var result = confirm("작성한 내용대로 요청하시겠습니까?");
      if (result == true) {
         var form = document.getElementById("reqFrm");
         form.submit();
         alert("상품요청이 완료되었습니다.");
      } else {
         alert("상품요청이 취소되었습니다.");
      }
   }
</script>   
   
<%@include file="footer.jsp"%>
   
</body>
</html>