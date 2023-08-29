<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 작성</title>

<%@include file ="header.jsp" %>

<div class=my_wrapper>

  <div class="page-title">
        <div class="container">
            <h2 class = notice_board>문의사항 작성</h2>
        </div>
    </div>

	<div class="qFrm_bx">
		<form action="QuestionInsert.question" method="post" id="qFrm">
			
			<div class=qFrm_table>
				<ul>
					<li><b class=qFrm_tit>제목</b></li>
					<li><input type="text" name="title" class="qFrm_input"></li>
				</ul>
				<ul>
					<li><b class=qFrm_con>내용</b></li>
					<li><textarea cols="50" rows="5" name="textarea1" id="idtextarea" class="qFrm_tarea"></textarea></li>
				</ul>
			</div>
			
			
			
			<div class="qFrm_btns_qsinsert">
				<input type="submit" value="제출" onclick="questionInsert()" class="in_btn mini_bk_btn">
			</div>
		</form>
	</div>
	
	
</div>	


<script>
	function questionInsert() {
		var form = document.getElementById("qFrm");
		form.action = "QuestionInsert.question";
		form.submit();
	}
	function questionMyAll() {
		var form = document.getElementById("qFrm");
		form.action = "QuestionMyAll.question";
		form.submit();
	}
</script>
<%@include file ="footer.jsp" %>
	
</body>
</html>