/**
 * jQuery
 */
$(document).ready(function(){
      // PLUG-IN //////////////////////////////
    $(".bxslider").bxSlider({
        auto: true, // 자동넘기기설정(true/false)
        pause: 2000, // 슬라이드 넘기기 사이간격(초)
        speed: 2500, //슬라이드 넘어가는 속도(초)
        mode: 'horizontal', //넘기기 방식옵션
        //'horizontal', 'vertical', 'fade'
      slideWidth: 2000,   // 슬라이드 너비
    });
   // 메뉴 제일 상단 //////////////////////////////
   $('#menu_box>li.t_menu').mouseover(function(){
       $(this).find("li").slideDown(450);   
   });
   
   $('#menu_box>li.t_menu').mouseleave(function(){
       $(this).find("li").slideUp(450);   
   });
   
   // 버튼 up클릭시 제일 상단으로 //////////////////////////////
   $('button.up').click(function(){
      $('html').scrollTop('0');
   });
   
   $('th.th-rv-con>a').click(function(){
       $('.review_modal').show();
   });//rv_close
   $('#rv_close').click(function(){
       $(this).parents('.review_modal').hide();
   });
   // 생년월일 datepicker
    //input을 datepicker로 선언
    $("#datepicker").datepicker({
        dateFormat: 'yy/mm/dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
       ,yearRange: "-100:-19"
    });                    
    
    //초기값을 오늘 날짜로 설정
   /*  $('#datepicker').datepicker('setDate', 'today'); */  //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            

   // 연락처 하이폰 자동생성 //////////////////////////////
    $('.phone').keydown(function (event) {
     var key = event.charCode || event.keyCode || 0;
     $text = $(this); 
     if (key !== 8 && key !== 9) {
         if ($text.val().length === 3) {
             $text.val($text.val() + '-');
         }
         if ($text.val().length === 8) {
             $text.val($text.val() + '-');
         }
     }

     return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
    // Key 8번 백스페이스, Key 9번 탭, Key 46번 Delete 부터 0 ~ 9까지, Key 96 ~ 105까지 넘버패트
    // 한마디로 JQuery 0 ~~~ 9 숫자 백스페이스, 탭, Delete 키 넘버패드외에는 입력못함
    })

   // 회원가입 유효성 검사 //////////////////////////////
   
   $('.m_join_btn').click(function(){
      
   let myTarget = $('#p');
   let myTarget2 = $('#p2');
   let myTarget3 = $('#p3');
   let form = document.joinForm;
   let date = new Date();
   let thisYear = date.getFullYear();
   let year = $('.birth').val().substring(0,4);
      
      if(!form.id.value || form.id.value==""){
         /*alert("아이디를 입력해주세요.");*/
         $('.modal1').show();
         $('#close1').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.id.focus();
         return false;
      }
      if(form.id.value.length < 4 || form.id.value.length > 16){
         /*alert("아이디는 4자 이상, 16자 이하로 입력해주세요.");*/
         $('.modal2').show();
         $('#close2').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.id.focus();
         return false;
      }

      if(myTarget.hasClass("red")){ // 중복된
         /*alert("아이디 중복 검사를 다시 진행해주세요.");*/
         $('.modal3').show();
         $('#close3').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.id.focus();
         return false;
      }
      
      if(!form.pw.value){
         /*alert("비밀번호를 입력해주세요.");*/
         $('.modal4').show();
         $('#close4').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.pw.focus();
         return false;
      }
      if(!form.pw2.value){
         /*alert("비밀번호를 확인해주세요.");*/
         $('.modal5').show();
         $('#close5').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.pw2.focus();
         return false;
      }
      //비밀번호에 공백을 포함할 수 없다.
      if(form.pw.value.search(/\s/) != -1){
         /*alert("비밀번호에 공백 없이 입력해주세요.");*/
         $('.modal8').show();
         $('#close8').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.pw.focus();
         return false;
      }
      if(form.pw.value != form.pw2.value){
         /*alert("입력하신 비밀번호가 다릅니다.");*/
         $('.modal6').show();
         $('#close6').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.pw2.focus();
         return false;
      }
      //8자리 이상, 대문자, 소문자, 숫자, 특수문자 모두 포함되어 있는 지 검사
      let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      /*let hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/*/
      
      if(!reg.test(form.pw.value)){
         /*alert("비밀번호는 8자리 이상이어야 하며, 대문자/소문자/숫자/특수문자 모두 포함해야 합니다.");*/
         $('.modal7').show();
         $('#close7').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.pw.focus();
         return false;
      }

      if(!form.name.value){
         /*alert("이름을 입력해주세요.");*/
         $('.modal9').show();
         $('#close9').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.name.focus();
         return false;
      }
      if(!form.birth.value){
         /*alert("생년월일을 입력해주세요.");*/
         $('.modal10').show();
         $('#close10').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.name.focus();
         return false;
      }
      
      /*미성년자 유효성 검사*/
      if(thisYear-year < 19){
         /*alert("미성년자는 가입이 불가능합니다.");*/
         $('.modal11').show();
         $('#close11').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.name.focus();
         return false;
      }
      
      if(!form.address.value){
         /*alert("주소를 입력해주세요.");*/
         $('.modal12').show();
         $('#close12').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.address.focus();
         return false;
      }
      if(!form.email1.value){
         /*alert("이메일을 입력해주세요.");*/
         $('.modal13').show();
         $('#close13').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.email1.focus();
         return false;
      }
      if(myTarget2.hasClass("red")){ // 중복된
         /*alert("이메일을 다시 입력해주세요.");*/
         $('.modal18').show();
         $('#close18').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.email1.focus();
         return false;
      }      
      if(!form.phone.value){
         /*alert("연락처를 입력해주세요.");*/
         $('.modal14').show();
         $('#close14').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.phone.focus();
         return false;
      }
      if(myTarget3.hasClass("red")){ // 중복된
         /*alert("연락처를 다시 입력해주세요.");*/
         $('.modal17').show();
         $('#close17').click(function(e){
            $(this).parents('#modal').hide();
         });
         form.phone.focus();
         return false;
      }
      
      form.submit();
   
   });
   
   $("#fn_CheckId").on("click", function(){
      alert("중복체크 눌렀음");
      var joinForm = document.joinForm;
      var id = joinForm.id.value;
      
      if(id.length==0 || id==""){
         alert("아이디를 입력해주세요.");
         joinForm.id.focus();
      }else{
         window.open("${contextPath}/dbCheckId.join?id="+id,"","width=500, height=300");
      }
      
   });
   
   
   ////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////
   
   ////// 회원가입 조건-end /////////////////////////////////////////
      
    // 마이페이지 비밀번호 입력조건 //////////////////////////////
   // 마이페이지 비밀번호 입력조건 //////////////////////////////
   $('.m_mp_btn').click(function(){ // 수정버튼 눌렀을때
         //alert('ok');
      let form2 = document.myPageForm;
   
      if(!form2.pw.value || form2.pw.value==""){
         /*alert("비밀번호를 입력해주세요.");*/
         $('.modal4').show();
         $('#close4').click(function(e){
            $(this).parents('#modal').hide();
         });
         form2.pw.focus();
         return false;
      }
         //비밀번호에 공백을 포함할 수 없다.
      if(form2.pw.value.search(/\s/) != -1){
         /*alert("비밀번호에 공백 없이 입력해주세요.");*/
         $('.modal8').show();
         $('#close8').click(function(e){
            $(this).parents('#modal').hide();
         });
         form2.pw.focus();
         return false;
      }
      //8자리 이상, 대문자, 소문자, 숫자, 특수문자 모두 포함되어 있는 지 검사
      let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      /*let hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/*/
      
      if(!reg.test(form2.pw.value)){
         /*alert("비밀번호는 8자리 이상이어야 하며, 대문자/소문자/숫자/특수문자 모두 포함해야 합니다.");*/
         $('.modal7').show();
         $('#close7').click(function(e){
            $(this).parents('#modal').hide();
         });
         form2.pw.focus();
         return false;
      }


      var result = confirm('작성한 내용대로 수정하시겠습니까?');
      if (result == true) {
         form2.action = "mypageupdate.client";
         form2.submit();
         alert("회원정보 수정이 완료되었습니다.");
      } else {
         alert("회원정보 수정이 취소되었습니다.");
      }

   });
   
   
});
