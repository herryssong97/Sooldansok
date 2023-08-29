<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%@include file="header.jsp"%>


   <div class=my_wrapper>

   <div class="page-title">
      <div class="container">
         <h2 class=notice_board>주문목록</h2>
      </div>
   </div>
   
      <div class="ordersum">총 주문수 : ${orderssum}건</div>
      
      <div class="board_changes"> <!-- 내 정보 수정, 장바구니, 주문목록, 게시글 관리 -->
         <a href="getmyinfo.client"><button class=board_change>내 정보 수정</button></a>
         <a href="BasketGetAll.basket?mem_id=${sid}"><button class=board_change>장바구니</button></a>
         <a href="ordersfilter.page?orders_filter=MEM_ID&orders_search=${sid}&id=${sid}"><button class=board_change>주문목록</button></a>
         <a href="questionfilter.page?question_filter=MEM_ID&question_search=${sid}&id=${sid}"><button class=board_change>게시글 관리</button></a>
      </div>


      <div id="board-search">
         <div class="container">
            <div class="search-window">
               <form action="ordersfilter.page">
                  <div class="search-wrap">
                     <label for="search" class="blind">주문목록 검색</label>
                     <input id="search" type="search" name="orders_search"
                        placeholder="상품명 또는 주문일(예: 20230101)을 입력해주세요." value="" style="width: 80%;">
                     <button type="submit" class="b_btn btn-dark" style="margin: 0;">검색</button>
                     <input type="hidden" name="orders_filter" value="DATE_PD">
                     <input type="hidden" name="id" value="${sid}">
                  </div>
               </form>
            </div>
         </div>
      </div>


      <table class="board_list" border=1>

         <tr>
            <th class="od_col11">No.</th>
            <th class="od_col22">주문일</th>
            <th class="od_col33">상품명</th>
            <th class="od_col44">수량</th>
            <th class="od_col55">총금액</th>
            <th class="od_col66">결제방식</th>
            <th class="od_col77">배송상태</th>
            <th class="od_col88">리뷰작성</th>
         </tr>
         <c:forEach var="order" items="${odlist}">
            <tr>
               <td class="od_col11">${order.od_num}</td>
               <td class="od_col22">${order.od_date}</td>
               <td class="od_col33">${order.info_name}</td>
               <td class="od_col44">${order.cnt}</td>
               <td class="od_col55">${order.info_price}</td>
               <td class="od_col66">${order.pay}</td>
               <td class="od_col77">${order.delivery}</td>
               <td class="od_col88">
                  <c:set var="yet" value="false" />
                  <c:set var="rev" value="false" />
                  <c:forEach var="rv" items="${od_rv}">
                     <c:if test="${rv.od_num eq order.od_num}">
                        <c:set var="rev" value="true" />
                     </c:if>
                     <c:if test="${order.delivery eq '배송완료' or order.delivery eq '픽업완료'}">
                        <c:set var="yet" value="true" />
                     </c:if>
                  </c:forEach>
                  <c:choose>
                      <c:when test="${yet and rev}">
                        <button value="작성완료" disabled class="rvin_btn mini_dis_btn">작성완료</button>
                     </c:when>
                     <c:when test="${yet and not rev}">
                        <button value="리뷰작성"
                           onclick="location.href='reviewinfo.orders?OD_NUM=${order.od_num}'"
                           class="rvin_btn mini_bk_btn">리뷰작성</button>
                     </c:when>
                     <c:otherwise>
                        <button value="작성불가" onclick="yet()" class="rvin_btn mini_dis_btn">작성불가</button>
                     </c:otherwise>
                  </c:choose>
               </td>
            </tr>
         </c:forEach>
      </table>

      <script type="text/javascript">
          function PageMove(page) {
              const url = new URL(window.location.href);
              url.searchParams.set("page", page);
              window.location.href = url.toString();
          }
          function yet(){
             alert("제품의 배송이 완료되지 않아 리뷰를 작성할 수 없습니다.");
          }
      </script>


      <div class="board_pages">
         <ul class="pagination">
            <li><a href="javascript:PageMove(${paging.firstPageNo})">맨앞으로</a></li>
            <li><a href="javascript:PageMove(${paging.prevPageNo})">앞으로</a></li>
            <c:forEach var="i" begin="${paging.startPageNo}"
               end="${paging.endPageNo}" step="1">
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