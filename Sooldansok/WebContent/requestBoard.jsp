<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품요청 게시판</title>

<%@include file ="header.jsp" %>


<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = request_board>상품요청 게시판</h2>
        </div>
    </div>


<div class=board_changes>
	<a href="noticeBoard.jsp"><button class = board_change>
		공지사항			
	</button></a>

	<a href="qnaBoard.jsp"><button class = board_change>
		QnA			
	</button></a>
</div>


    <!-- board seach area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="">
                    <div class="search-wrap">
                        <label for="search" class="blind">리뷰 내용 검색</label>
                        <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
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
                    <th scope="col" class="th-num">번호</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-date">등록일</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>3</td>
                    <th>
                      <a href="#!">[공지사항] 개인정보 처리방침 변경안내처리방침</a>
                      <p>테스트</p>
                    </th>
                    <td>2023.06.15</td>
                </tr>

                <tr>
                    <td>2</td>
                    <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                    <td>2023.06.15</td>
                </tr>

                <tr>
                    <td>1</td>
                    <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                    <td>2023.06.15</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

	

</div>
	
<%@include file ="footer.jsp" %>

</body>
</html>