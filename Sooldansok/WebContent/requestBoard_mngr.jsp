<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항</title>

<%@include file="header_mngr.jsp"%>

<div class=my_wrapper>

   <div class="page-title">
      <div class="container">
         <h2 class=notice_board>상품요청</h2>
      </div>
   </div>


   <div class=board_changes>
      <a href="notice.page"><button class=board_change>공지사항</button></a>
      <a href="question.page"><button class=board_change>문의사항</button></a>
      <a href="request.page"><button class=board_change>상품요청</button></a>
   </div>


   <!-- board seach area -->
   <div id="board-search">
      <div class="container">
         <div class="search-window">
            <form action="requestfilter.page">
               <div class="search-wrap">
                  <label for="search" class="blind">문의사항 내용 검색</label>
                  <select name="request_filter" class="board_filter">
                     <option value="REQ_STATE">상태</option>
                     <option value="MEM_ID">회원아이디</option>
                  </select>
                  <input id="search" type="search" name="request_search"
                     placeholder="검색어를 입력해주세요." value="${param.question_search}">
                  <button type="submit" class="b_btn btn-dark" style="margin: 0;">검색</button>
                  <input type="hidden" name="id" value="">
               </div>
            </form>
         </div>
      </div>
   </div>

   <!-- board list area -->
         <table class="board_list_mngr">
            <thead>
               <tr>
                  <th class="reqm_col1">번호</th>
                  <th class="reqm_col2">상품명</th>
                  <th class="reqm_col3">원산지</th>
                  <th class="reqm_col4">생산년도</th>
                  <th class="reqm_col5">내용</th>
                  <th class="reqm_col6">아이디</th>
                  <th class="reqm_col7">상태</th>
               </tr>

            </thead>
            <tbody>  
               <c:forEach var="i" items="${reqlist}">
                  <tr>
                     <td class="reqm_col1">${i.req_num}</td>
                     <td class="reqm_col2">${i.req_name}</td>
                     <td class="reqm_col3">${i.req_country}</td>
                     <td class="reqm_col4">${i.req_made_year}</td>
                     <td class="reqm_col5">
                        <a href="RequestGetOne_mngr.request?req_num=${i.req_num}">${i.req_comment}</a>
                     </td>
                     <td class="reqm_col6">${i.mem_id}</td>
                     <td class="reqm_col7">
                     <form action="RequestUpdate_mngr.request">
                            <select class="delivery-select" name="new_state" onchange="this.form.submit()">
                                <option value="확인 중" ${i.req_state eq '확인 중' ? 'selected' : ''}>확인 중</option>
                                <option value="접수 완료" ${i.req_state eq '접수 완료' ? 'selected' : ''}>접수 완료</option>
                                <option value="주문 불가" ${i.req_state eq '주문 불가' ? 'selected' : ''}>주문 불가</option>
                            </select>
                            <input type="hidden" name="req_num" value="${i.req_num}">
                            
                        </form></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>

   
   <script type="text/javascript">
          function PageMove(page) {
              const url = new URL(window.location.href);
              url.searchParams.set("page", page);
              window.location.href = url.toString();
          }
   </script>


   <div class="board_pages">
      <ul class="pagination">
         <li><a href="javascript:PageMove(${paging.firstPageNo})">맨앞으로</a></li>
         <li><a href="javascript:PageMove(${paging.prevPageNo})">앞으로</a></li>
         <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
            <c:choose>
               <c:when test="${i eq paging.pageNo}">
                  <li class="active"><a href="javascript:PageMove(${i})">${i}</a></li>
               </c:when>
               <c:otherwise>
                  <li><a href="javascript:PageMove(${i})">${i}</a></li>
               </c:otherwise>
            </c:choose>
         </c:forEach>
         <li><a href="javascript:PageMove(${paging.nextPageNo})">뒤로</a></li>
         <li><a href="javascript:PageMove(${paging.finalPageNo})">맨뒤로</a></li>
      </ul>
   </div>

</div>

</body>
</html>