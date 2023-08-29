<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>

    <%@include file="header_mngr.jsp" %>
</head>
    
<body>
<div class="my_wrapper">
    <div class="page-title">
        <div class="container">
            <h2 class="notice_board">공지사항</h2>
        </div>
    </div>

    <div class="board_changes">
       <a href="notice.page"><button class=board_change>공지사항</button></a>
		<a href="question.page"><button class=board_change>문의사항</button></a>
		<a href="request.page"><button class=board_change>상품요청</button></a>
    </div>

    <!-- board search area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="noticefilter.page">
                    <div class="search-wrap">
                        <label for="search" class="blind">문의사항 내용 검색</label>
                        <input id="search" type="search" name="notice_search" placeholder="검색어를 입력해주세요." value="${param.notice_search}">
                        <button type="submit" class="b_btn btn-dark" style="margin:0;">검색</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- board list area -->
    
      
            <table class="board_list_mngr">
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
       
   
     <div class="nt_insert">
     <form action="noticeInsertForm_mngr.jsp">
                <input type="submit" id="searchone" value="작성하기">
     </form>	
	 </div>
   
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