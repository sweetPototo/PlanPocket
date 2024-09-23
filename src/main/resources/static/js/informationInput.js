//거래추가 유효성검사
function tranCheck() { 
   if(f.accountNo.value==""){
          alert('결제수단을 선택해주세요.');
           return;
   }
   var amount = f.tranAmount.value;
  
   var hpExptext = /^[0-9]+/;
   if(hpExptext.test(amount)==false){
      alert("금액에는 숫자만 입력 가능합니다.");
      f.tranAmount.focus();
      return;
   }
    
   if(f.tranDetail.value==""){
      document.f.tranDetail.value = " ";
   }
    document.f.submit();
   }
   
function tranReseting() {
	f.tranDate.value = null;
	f.tranType.value = "0";
	f.tranCategory.value = "1";
	f.tranAmount.value = null;
	f.tranDetail.value = null;
	f.accountNo.value = f.accountNo.value;
	return;
	
}

function addAccount() {
	window.location.href = "/information/account";
}

//계좌 유효성검사
function accountCheck() { 
   if(f.accountName.value==""){
          alert('계좌 이름을 지정해주세요.');
          f.accountName.focus();
           return;
   }
   /* var amount = f.tranAmount.value;
  
   var hpExptext = /^[0-9]+/;
   if(hpExptext.test(amount)==false){
      alert("금액에는 숫자만 입력 가능합니다.");
      f.tranAmount.focus();
      return;
   } */
   
   if(f.accountDetail.value==""){
      document.f.accountDetail.value = " ";
   }
    submit();
   }
   
function accountReseting() {
	f.accountName.value = null;
	f.accountCategory.value = "1";
	f.accountDetail.value = null;
	return;
	
}

function accountSubmit() {
	//객체로 값 저장
	const accountRequest = {
		accountName : document.getElementById('accountName').value, //계좌 이름
		accountCategory : document.getElementById('accountCategory').value, //계좌 카테고리
		accountDetail : document.getElementById('accountDetail').value, //계좌 설명
		memberNo : '${sessionScope.memberNo}', //회원번호
		accountBalance : document.getElementById('accountBalance').value //계좌 잔액
	}
	const url = '/information/${sessionScope.memberNo}/account';
	const method = 'POST';
	
	fetchMethod(accountRequest, url, method);
}