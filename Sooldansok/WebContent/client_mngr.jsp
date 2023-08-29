<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
</head>
<body>


<%@include file="header_mngr.jsp"%>

<div class="my_wrapper">
    <span class="s_title">회원 목록</span>
    <div class="clientsum">총 회원수 : ${clientsum}명</div>

    <table class="cl_list" border=1>
        <tr>
            <th class="cl_col1">아이디</th>
            <th class="cl_col2">이름</th>
            <th class="cl_col3">생년월일</th>
            <th class="cl_col4">주소</th>
            <th class="cl_col5">이메일</th>
            <th class="cl_col6">전화번호</th>
            <th class="cl_col7">상세정보</th>
        </tr>
        <c:forEach var="client" items="${clist}"> <!-- list1 -->
            <form action="clientgetone.client">
                <tr>
                    <td></td>
                    <td class="cl_col1">${client.id}</td>
                    <td class="cl_col2">${client.name}</td>
                    <td class="cl_col3">${client.birth}</td>
                    <td class="cl_col4">${client.address}</td>
                    <td class="cl_col5">${client.email}</td>
                    <td class="cl_col6">${client.phone}</td>
                    <td class="cl_col7">
                        <input type="submit" id="searchone" value="상세정보">
                        <input type="hidden" name="id" value="${client.id}">
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>

    <form action="clientfilter.page" method="get">
        <div class="subm">
            <select name="client_filter">
			    <option value="ID" ${param.client_filter eq 'ID' ? 'selected' : ''}>아이디</option>
			    <option value="NAME" ${param.client_filter eq 'NAME' ? 'selected' : ''}>이름</option>
			    <option value="ADDRESS" ${param.client_filter eq 'ADDRESS' ? 'selected' : ''}>주소</option>
			    <option value="PHONE" ${param.client_filter eq 'PHONE' ? 'selected' : ''}>전화번호</option>
			</select>
            <input type="text" name="client_search" value="${param.client_search}">
            <input type="submit" value="검색" id="search_in">
        </div>
    </form>


<script type="text/javascript">
    function PageMove(page) {
        const url = new URL(window.location.href);
        url.searchParams.set("page", page);////////////////////////////////////////////
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