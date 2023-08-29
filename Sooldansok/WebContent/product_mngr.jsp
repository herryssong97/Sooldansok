<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>

</head>
<body>

	<%@include file="header_mngr.jsp"%>



	<div class="my_wrapper">

		<span class="s_title">상품 목록 </span>
		<div class="clientsum">총 상품수 : ${productsum}개</div>
		
		<form action="pd_InfoInsertForm.jsp">
			<input type="submit" value="추가하기" class="pd_insert">
		</form>

		<table class="cl_list" border=1>
			<tr>
				<th class="pd_col1">No.</th>
				<th class="pd_col2">상품명</th>
				<th class="pd_col3">가격</th>
				<th class="pd_col4">종류</th>
				<th class="pd_col5">생산지</th>
				<th class="pd_col6">용량</th>
				<th class="pd_col7">도수</th>
				<th class="pd_col8">생산년도</th>
				<th class="pd_col9">수량</th>
				<th class="pd_col10">수정하기</th>
			</tr>


			<c:forEach var="i" items="${pdlist}">
				<form action="Pd_InfoSearchOne_mngr.product" method="post">
					<tr>
						<td class="pd_col1">${i.info_num}</td>
						<td class="pd_col2">${i.info_name}</td>
						<td class="pd_col3">${i.info_price}</td>
						<td class="pd_col4">${i.kind}</td>
						<td class="pd_col5">${i.country}</td>
						<td class="pd_col6">${i.capacity}ml</td>
						<td class="pd_col7">${i.alcohol}%</td>
						<td class="pd_col8">${i.made_year}년</td>
						<td class="pd_col9">${i.stock}개</td>
						<td class="pd_col10"><input type="submit" id="searchone"
							value="수정하기"> <input type="hidden" name="info_num"
							value="${i.info_num}"></td>
					</tr>
				</form>
			</c:forEach>
		</table>

		<form action="productfilter.page" method="get">
			<div class="subm">
				<select name="product_filter">
				    <option value="INFO_NUM" ${param.product_filter eq 'INFO_NUM' ? 'selected' : ''}>상품번호</option>
				    <option value="INFO_NAME" ${param.product_filter eq 'INFO_NAME' ? 'selected' : ''}>상품명</option>
				    <option value="KIND" ${param.product_filter eq 'KIND' ? 'selected' : ''}>종류</option>
				    <option value="COUNTRY" ${param.product_filter eq 'COUNTRY' ? 'selected' : ''}>생산지</option>
				</select>
				<input type="text" name="product_search" value="${param.product_search}"> 
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


		<%-- <%@include file="footer.jsp"%> --%>
	</div>
</body>
</html>