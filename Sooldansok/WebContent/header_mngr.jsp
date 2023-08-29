<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/index_mngr.css">
<link rel="stylesheet" type="text/css" href="css/productDetail.css">
<link rel="stylesheet" type="text/css" href="css/MY_jquery.bxslider.css">
<script src="js/jquery-3.4.1.min.js"></script>
<!-- 복붙 필수 -->
<script src="js/jquery.bxslider.min.js"></script>
<script src="js/index.js"></script>
</head>
<body>

	<header>
		<a href="index_mngr.jsp"><h1 id=head>개발자의 술단속</h1></a>
	</header>


	<nav>
		<ul id=menu_box>
			<li class=t_menu><a href="client.page">회원 관리</a></li>

			<li class=t_menu><a href="product.page">상품 관리</a></li>

			<li class=t_menu><a href="orders.page">주문 관리</a></li>

			<li class=t_menu>커뮤니티 관리
				<ul class=s_menu>
					<li><a href="notice.page">공지사항</a></li>
					<li><a href="question.page">고객센터</a></li>
					<li><a href="request.page">상품요청</a></li>
				</ul>
			</li>
		</ul>


		<div class=lj_line>
			<span class=logout> <!-- style="display:none;" --> <a href="logout.jsp">로그아웃</a>
			</span>
		</div>
	</nav>


</body>
</html>