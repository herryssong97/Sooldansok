<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 요청상품 전체조회</title>
</head>
<body>

<%@include file ="header.jsp" %>

<div class=my_wrapper>

	<div class="page-title">
		<div class="container">
			<h2 class=notice_board>상품요청</h2>
		</div>
	</div>


	<div class=board_changes>
		<a href="questionfilter.page?question_filter=MEM_ID&question_search=${sid}&id=${sid}">
			<button class=board_change>문의사항</button></a>
		<a href="requestfilter.page?request_filter=MEM_ID&request_search=${sid}&id=${sid}">
			<button class=board_change>상품요청</button></a>
		<a href="reviewfilter.page?review_filter=MEM_ID&review_search=${sid}&id=${sid}">
			<button class=board_change>상품리뷰</button></a>
		<a href="getmyinfo.client">
			<button class=board_change>마이페이지</button></a>
	</div>


	<!-- board seach area -->
	<div id="board-search">
		<div class="container">
			<div class="search-window">
				<form action="requestfilter.page">
					<div class="search-wrap">
						<label for="search" class="blind">문의사항 내용 검색</label>
						<input id="search" type="search" name="request_search"
							placeholder="검색어를 입력해주세요." value="" style="width: 80%;">
						<button type="submit" class="b_btn btn-dark" style="margin: 0;">검색</button>
						<input type="hidden" name="request_filter" value="TITLE_COMMENT">
						<input type="hidden" name="id" value="${sid}">
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- board list area -->
			<table class="board_list">
				<thead>
					<tr>
			<th class="reqm_col11">번호</th>
			<th class="reqm_col22">상품명</th>
			<th class="reqm_col33">원산지</th>
			<th class="reqm_col44">생산연도</th>
			<th class="reqm_col55">요청사항</th>
			<th class="reqm_col66">주문상태</th>
		</tr>
		
		<c:forEach var="i" items="${reqlist}" varStatus="status">
			<tr>
				<td class="reqm_col11">
					<form action="RequestMyOne.request" method="post">
					<!-- 이 버튼을 눌렀을 때 이 튜플의 req_num 정보가 전달되어야 한다 -->
						<input type="hidden" name="req_num"  value="${i.req_num}">
						<button type="submit" name="seqBtn" value="${i.req_name}">
							${status.index+1}
						</button>
					</form>
                </td>
				<td class="reqm_col22">${i.req_name}</td>
				<td class="reqm_col33">${i.req_country}</td>
				<td class="reqm_col44">${i.req_made_year}</td>
				<td class="reqm_col55">${i.req_comment}</td>
				<td class="reqm_col66">${i.req_state}</td>
			</tr>
		</c:forEach>
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
</body>
</html>