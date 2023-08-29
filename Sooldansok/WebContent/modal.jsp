<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
<!-- modal 모음 -->
</head>
<body>

<!-- ==========회원가입 유효성 검사========== -->
<div class="modal1" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close1" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    아이디를 입력해주세요.
  </div>
</div>

<div class="modal2" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close2" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    아이디는 4자 이상, 16자 이하로 입력해주세요.
  </div>
</div>

<div class="modal3" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close3" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    아이디 중복 검사를 다시 진행해주세요.
  </div>
</div>

<div class="modal4" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close4" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    비밀번호를 입력해주세요.
  </div>
</div>

<div class="modal5" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close5" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    비밀번호를 확인해주세요.
  </div>
</div>

<div class="modal6" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close6" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    입력하신 비밀번호가 다릅니다.
  </div>
</div>

<div class="modal7" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close7" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    비밀번호는 8자리 이상이어야 하며, <br>대문자/소문자/숫자/특수문자 모두 포함해야 합니다.
  </div>
</div>

<div class="modal8" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close8" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    비밀번호에 공백 없이 입력해주세요.
  </div>
</div>

<div class="modal9" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close9" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    이름을 입력해주세요.
  </div>
</div>

<div class="modal10" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close10" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   생년월일을 입력해주세요.
  </div>
</div>

<div class="modal11" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close11" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   연나이 19세 이상부터 가입 가능합니다.
  </div>
</div>

<div class="modal12" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close12" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   주소를 입력해주세요.
  </div>
</div>

<div class="modal13" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close13" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   이메일을 입력해주세요.
  </div>
</div>

<div class="modal14" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close14" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   연락처를 입력해주세요.
  </div>
</div>

<div class="modal17" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close17" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   연락처를 다시 입력해주세요.
  </div>
</div>

<div class="modal18" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close18" class="close" title="클릭하면 창이 닫힙니다.">x</button>
   이메일을 다시 입력해주세요.
  </div>
</div>
<!-- ==========회원가입 유효성 검사-끝========== -->


<div class="modal15" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close15" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    로그인 성공
  </div>
</div>

<div class="modal16" id="modal">
  <div class="modal_content"title="클릭하면 창이 닫힙니다.">
	<button type="reset" id="close16" class="close" title="클릭하면 창이 닫힙니다.">x</button>
    로그인 실패
  </div>
</div>

</body>
</html>