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
			<h2 class=notice_board>문의사항</h2>
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
				<form action="questionfilter.page">
					<div class="search-wrap">
						<label for="search" class="blind">문의사항 내용 검색</label>
						<select name="question_filter" class="board_filter">
							<option value="TITLE_COMMENT">제목+내용</option>
							<option value="MEM_ID">회원아이디</option>
						</select>
						<input id="search" type="search" name="question_search"
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
						<th class="qs_col1">번호</th>
						<th class="qs_col2">제목</th>
						<th class="qs_col3">아이디</th>
						<th class="qs_col4">등록일</th>
						<th class="qs_col5">답변여부</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach var="i" items="${qslist}">
						<tr>
							<td class="qs_col1">${i.q_num}</td>
							<td class="qs_col2"><a href="QuestionSearchOne.question?q_num=${i.q_num}">${i.q_title}</a></td>
							<td class="qs_col3">${i.mem_id}</td>
							<td class="qs_col4">${i.q_date}</td>
							<td class="qs_col5 ${empty i.answer ? 'waiting' : 'completed'}">${empty i.answer ? '답변 대기중' : '답변 완료'}</td>
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