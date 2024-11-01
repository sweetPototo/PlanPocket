<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
	$("name").blur(function(){
		var sm_name = $("name").val();
		if(sm_name == '' || sm_name.length <2){
			$(".successNameChk").text("이름은 2자 이상 8자 이하로 설정해 주세요:)");
			$(".successNameChk").css("color","red")
			$("#nameDoubleChk").val(false);
		}else{
			$.ajax({url : '${pageContext.request.contextPath}/nameCheck?sm_name' + sm_name,
				type = 'post',
				cache = false,
				success : function(data){
					if(data == 0){
						$(".successNameChk").text("사용가능한 이름입니다.");
						$(".successNameChk").css("color","green");
						$(".successNameChk").val(true);
					}else{
						$(".successNameChk").text("이미 사용중인 이름입니다.");
						$(".successNameChk").css("color","red");
						$(".successNameChk").val(false);
					}
				}, error : function(){
					console.log("실패");
				}
			})
		}
		
	}
</script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
  	<label for="name">이&emsp;름</label>
    <input id="name" type="text" name="sm_name" placeholder="이름을 설정해주세요." maxlength="8" title="8자 까지 입력" required autofocus/>
    <span class="point successNameChk">이름은 2자 이상 8자 이하로 설정해주시기 바랍니다.</span>
    <input type="hidden" id="nameDoubleChk"/>
  </div>
  <div>
	  <label for="id">아 이 디</label>
	  <input id="id" type="text" name="sm_id" placeholder="아이디를 입력해주세요." title="특수문자는 사용할 수 없습니다">
</div>
</body>
</html>