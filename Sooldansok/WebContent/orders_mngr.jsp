<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 관리</title>
</head>

<body>
    <%@include file="header_mngr.jsp" %>
    
	
    <div class=my_wrapper>
        <span class="s_title">주문 목록</span>
        <div class="clientsum">총 주문수 : ${orderssum}건</div>

        <table class="cl_list" border=1>
            
            <tr>
                <th class="od_col1">No.</th>
                <th class="od_col2">아이디</th>
                <th class="od_col3">상품명</th>
                <th class="od_col4">수량</th>
                <th class="od_col5">결제방식</th>
                <th class="od_col6">배송상태</th>
                <th class="od_col7">총금액</th>
                <th class="od_col8">주문일자</th>
            </tr>
            <c:forEach var="order" items="${odlist}">
                <tr>
                    <td class="od_col1">${order.od_num}</td>
                    <td class="od_col2">${order.mem_id}</td>
                    <td class="od_col3">${order.info_name}</td>
                    <td class="od_col4">${order.cnt}</td>
                    <td class="od_col5">${order.pay}</td>
                    <td class="od_col6">
                        <form action="OrdersUpdate.orders">
                            <select class="delivery-select" name="new_delivery" onchange="this.form.submit()">
                                <option value="배송준비중" ${order.delivery eq '배송준비중' ? 'selected' : ''}>배송준비중</option>
                                <option value="배송중" ${order.delivery eq '배송중' ? 'selected' : ''}>배송중</option>
                                <option value="배송완료" ${order.delivery eq '배송완료' ? 'selected' : ''}>배송완료</option>
                                <option value="픽업예정" ${order.delivery eq '픽업예정' ? 'selected' : ''}>픽업예정</option>
                                <option value="픽업완료" ${order.delivery eq '픽업완료' ? 'selected' : ''}>픽업완료</option>
                                <option value="주문취소" ${order.delivery eq '주문취소' ? 'selected' : ''}>주문취소</option>
                            </select>
                            <input type="hidden" name="od_num" value="${order.od_num}">
                            <input type="hidden" name="delivery" value="${order.delivery}">
                        </form>
                    </td>
                    <td class="od_td od_col7">${order.info_price}</td>
                    <td class="od_col8">${order.od_date}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="ordersfilter_mngr.page" method="get">
        <div class="subm">
            <select name="orders_filter">
			    <option value="OD_NUM" ${param.orders_filter eq 'OD_NUM' ? 'selected' : ''}>주문번호</option>
			    <option value="MEM_ID" ${param.orders_filter eq 'MEM_ID' ? 'selected' : ''}>아이디</option>
			    <option value="INFO_NAME" ${param.orders_filter eq 'INFO_NAME' ? 'selected' : ''}>상품명</option>
			    <option value="PAY" ${param.orders_filter eq 'PAY' ? 'selected' : ''}>결제방식</option>
			    <option value="DELIVERY" ${param.orders_filter eq 'DELIVERY' ? 'selected' : ''}>배송상태</option>
			</select>
            <input type="text" name="orders_search" value="${param.orders_search}">
            <input type="submit" value="검색" id="search_in">
        </div>
    </form>
        
        	     
 	     <script type="text/javascript">
		    function PageMove(page) {
		        const url = new URL(window.location.href);
		        url.searchParams.set("page", page);
		        window.location.href = url.toString();
		    }
		</script>
 	     
    

    			<div class="pages">
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