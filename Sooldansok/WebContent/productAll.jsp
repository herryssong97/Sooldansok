<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.4.1.min.js"></script> 
<title>전체 상품 출력</title>
<%@include file ="header.jsp" %>



<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = pd_name>전체 상품 출력</h2>
            <div class = search_bx>
	            <form class=search_form action="productsearchall.page" method="get">
		            <input class = pd_search placeholder="와인명으로 검색" name="pd_name" value="${param.pd_name}">
		            <button type=submit class=mini_bk_btn>검색</button>
	            </form>
            </div>
        </div>
    </div>
	
		<div class=board_changes>
			<a href="france.page"><button class = board_change style="width:8vw;">
				프랑스			
			</button></a>
			<a href="chile.page"><button class = board_change style="width:8vw;">
				칠레			
			</button></a>
			<a href="italy.page"><button class = board_change style="width:8vw;">
				이태리			
			</button></a>
			<a href="spain.page"><button class = board_change style="width:8vw;">
				스페인			
			</button></a>
		</div>
		
		
	<div class="product_all_bx">
	
	<c:forEach var="pdVO" items="${pdArr}">
	
		<div class ="card">
			<a href="Pd_InfoSearchOne.product?mem_id=${sessionScope.sid}&info_num=${pdVO.info_num}"><img src="./images/${pdVO.img}" alt="" class="card_img" ></a>
			<div>
				<p class="card_text">상품명 : ${pdVO.info_name}</p>
				<p class="card_text">종류 : ${pdVO.kind}</p>
				<p class="card_text">가격 : ${pdVO.info_price}</p>
				<p class="card_text">나라/년도 : ${pdVO.country}/${pdVO.made_year}</p>
			</div>
		</div>
		
	</c:forEach>
	
	
	
	
	
	<button class=up>up</button>	
	
	<script type="text/javascript">
		    function PageMove(page) {
		        const url = new URL(window.location.href);
		        url.searchParams.set("page", page);
		        window.location.href = url.toString();
		    }
		</script>


		<div class="product_pages">
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
	

</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>