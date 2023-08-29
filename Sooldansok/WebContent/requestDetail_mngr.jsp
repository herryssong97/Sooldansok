<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="header_mngr.jsp"%>

<div class="my_wrapper">

   <span class="s_title">상품요청 상세 정보</span>

   <div>
   
         <table class="cl_info">
            <tr class="pd_row">
               <td class="pd_col">신청자</td>
               <td class="input_pdinfo">${rv.mem_id}</td>         
            </tr>
            <tr class="pd_row">
               <td class="pd_col">제품명</td>
               <td class="input_pdinfo">${rv.req_name}</td>         
            </tr>
            <tr class="pd_row">
               <td class="pd_col">원산지</td>
               <td class="input_pdinfo">${rv.req_country}</td>         
            </tr>
            <tr class="pd_row">
               <td class="pd_col">생산연도</td>
               <td class="input_pdinfo">${rv.req_made_year}</td>         
            </tr>
            <tr class="pd_row">
               <td class="pd_col">요청사항</td>
               <td class="input_pdinfo">${rv.req_comment}</td>         
            </tr>
            <tr class="pd_row">
               <td class="pd_col">주문상태</td>
               <td class="input_pdinfo">${rv.req_state}</td>         
            </tr>
         </table>
      
   </div>
   
</div>
   
</body>
</html>