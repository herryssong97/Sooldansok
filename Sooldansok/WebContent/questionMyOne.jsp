<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
    }

    h2 {
      text-align: center;
      margin-top: 20px;
    }

    form {
      width: 500px;
      margin: 0 auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    table td {
      padding: 10px;
    }

    input[type="text"] {
      width: 100%;
      padding: 8px;
      border-radius: 3px;
      border: 1px solid #ccc;
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #4caf50;
      color: #fff;
      border: none;
      padding: 10px 20px;
      border-radius: 3px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
<script>
	function updateQuestion() {
		// alert('작성 내용으로 수정하시겠습니까?');
		var form = document.getElementById("myQFrm");
		form.action = "QuestionUpdate.question";
		form.submit();
	}
	function deleteQuestion() {
		// alert('해당 상품요청을 삭제하시겠습니까?');
		var form = document.getElementById("myQFrm");
		form.action = "QuestionDelete.question";
		form.submit();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>나의 문의사항</h2>
	<form id="myQFrm" method="post">
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" value="${qmo.q_title}" name="q_title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" value="${qmo.q_comment}" name="q_comment"></td>
			</tr>
			<tr>
				<td>답변</td>
				<td>${qmo.answer}</td> <!-- 사용자가 변경 못함 -->
			</tr>
			<tr>
				<td>최종 수정 날짜</td>
				<td>${qmo.q_date}</td>
			</tr>
			<tr>
				<td>답변 날짜</td>
				<td>${qmo.answer_date}</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="q_num" value="${qmo.q_num}">
				</td>	
			</tr>
			<tr>
				<td colspan="5">
					<input type="submit" name="udtSubmit" value="수정하기" onclick="updateQuestion()">
					<input type="submit" name="delSubmit" value="삭제하기" onclick="deleteQuestion()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>