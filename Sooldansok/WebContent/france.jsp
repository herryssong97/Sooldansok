<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프랑스 제품</title>
</head>
<body>
<%@include file ="header.jsp" %>


<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = company_name>프랑스 제품</h2>
            <div class = search_bx>
            	<form action="productall.page" class="look_all"><button type = submit class=mini_bk_btn>전체 상품 보기</button></form>
	            <form class=search_form action="productsearchfrance.page" method="get">
		            <input class = pd_search placeholder="와인명으로 검색" name=pd_name>
		            <button type=submit class=mini_bk_btn>검색</button>
	            </form>
            </div>
        </div>
    </div>



	<div class="product_country_bx">
	

	<c:forEach var="pdVO" items="${frArr}">
	
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
	
	</div>

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
	
<%@include file ="footer.jsp" %>

</body>
</html>