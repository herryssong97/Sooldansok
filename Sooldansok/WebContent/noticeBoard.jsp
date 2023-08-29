<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판</title>

<%@include file ="header.jsp" %>


<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = notice_board>공지사항 게시판</h2>
        </div>
    </div>


<div class=board_changes>
	<a href="Question.question"><button class = board_change>
		QnA			
	</button></a>
	

	<a href="Request.request"><button class = board_change>
		상품요청			
	</button></a>
</div>


    <!-- board seach area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="noticefilter.page">
                    <div class="search-wrap">
                        <label for="search" class="blind">문의사항 내용 검색</label>
                        <input id="search" type="search" name="notice_search" placeholder="검색어를 입력해주세요." value="">
                        <button type="submit" class="b_btn btn-dark" style="margin:0;">검색</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
   
  <!-- board list area -->
    <div id="board-list">
        <div class="container">
            <table class="board-table">
                <thead>
                <tr>
                    <th  class="nt_col1">번호</th>
                    <th  class="nt_col2">제목</th>
                    <th  class="nt_col3">등록일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${ntlist}">
                    <tr>
                        <td class="nt_col1">${i.n_num}</td>
                        <td class="nt_col2"><a href="NoticeSearchOne.notice?n_num=${i.n_num}">${i.n_title}</a></td>
                        <td class="nt_col3">${i.n_date}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <script type="text/javascript">
		    function PageMove(page) {
		        const url = new URL(window.location.href);
		        url.searchParams.set("page", page);
		        window.location.href = url.toString();
		    }
	</script>
    

    			<div class="board_pages_admin">
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