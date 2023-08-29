<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/productDetail.css">
<link rel="stylesheet" type="text/css" href="css/MY_jquery.bxslider.css">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="js/jquery-3.4.1.min.js"></script> <!-- 복붙 필수 -->
<script src="js/jquery.bxslider.min.js"></script>
<script src="js/index.js"></script>

<!-- 달력 plugin : datepicker -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
   function qLogChk() {
      var form = document.getElementById("qFrm");
      form.action = "Question.question";
      form.submit();
   }
   function reqLogChk() {
      var form = document.getElementById("reqFrm");
      form.action = "Request.request";
      form.submit();
   }
</script>
</head>
<body>
<script>
/* history.pushState(null, null, 'index.jsp'); 

//뒤로가기 이벤트감지 -> 현재페이지로 이동
window.onpopstate = function() { 
	history.go(1); // == 동일한 한 페이지 앞으로 가기
} */


$(document).ready(function(){
     var sid = '<%=session.getAttribute("sid")%>';
	//alert(uid);
      if(sid=="null"){ // 로그아웃 상태
  		$("a.login").addClass('display_block');
		$("a.join").addClass('display_block');
		$("a.mypage").addClass('display_none');
		$("a.logout").addClass('display_none');
		
      }
      else{ // 로그인 상태
    	  //alert(sid);
   		$("a.login").addClass('display_none');
		$("a.join").addClass('display_none');
		$("a.mypage").addClass('display_block');
		$("a.logout").addClass('display_block'); 
		
	}
      
}) 
	
</script> 

<%  
response.setHeader("cache-control","no-store"); // http 1.1  
response.setHeader("Pragma","no-cache"); // http 1.0  
response.setDateHeader("Expires",0); // proxy server 에 cache방지.  
%>
	<header>
		<a href="index.jsp"><h1 id = head>
			개발자의 술단속
		</h1></a>
	</header>
	
	
	<nav>	
		<ul id = menu_box>
			<li class = t_menu>
				<a class=white href="aboutCompany.jsp">회사 소개</a>
				<ul class=s_menu>
					<li><a href="aboutCompany.jsp">회사 소개</a></li>
					<li><a href="wayToCome.jsp">오시는 길</a></li>
				</ul>
			</li>
			
			<li class = t_menu>
				<a class=white href="france.page">프랑스</a>
				<ul class=s_menu>
				</ul>
			</li>
			
			<li class = t_menu>
				<a class=white href="chile.page">칠레</a>
				<ul class=s_menu>
				</ul>
			</li>
			
			<li class = t_menu>
				<a class=white href="italy.page">이태리</a>
				<ul class=s_menu>
				</ul>
			</li>
			
			<li class = t_menu>
				<a class=white href="spain.page">스페인</a>
				<ul class=s_menu>
				</ul>
			</li>
			
			<li class = t_menu>
				<a class=white href="notice.page">커뮤니티</a>
				<ul class=s_menu>
					<li><a href="notice.page">공지사항</a></li>
					<li>
						<form id="qFrm">
                    		<a href="#" onclick="qLogChk()">QnA</a>
                  		</form>
					</li>
					<li>
						<form id="reqFrm">
                     		<a href="#" onclick="reqLogChk()">상품요청</a>
                  		</form>
					</li>
				</ul>
			</li>
		</ul>
		
<%
	String sid2 = (String)session.getAttribute("sid");
%>

			<div class = lj_line>
				<a  class = "login white" href="login.jsp"><span>
					login
				</span>
				</a>
				<a  class = "join white" href="join.jsp"><span>
					회원가입
				</span>
				</a>
				<!-- <a class=white href="mypage.jsp">마이페이지</a> 
               <!-- (인덱스 => 마이페이지) 컨트롤러 받으면 컨트롤러 경로로 바꿀것! -->
				<a class = "mypage white" href="getmyinfo.client"><span id=name><!-- style="display:none;" -->
					<%= sid2%>님
				</span>
				</a>
				<a class = "logout white" href="logout.jsp"><span>  <!-- style="display:none;" -->
					로그아웃 <!-- -->
				</span>
				</a>
			</div>
	</nav>


</body>
</html>