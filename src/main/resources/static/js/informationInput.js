function check() { 
   if(f.accountNo.value==""){
          alert('결제수단을 선택해주세요.');
          f.accountNo.focus();
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
   
function reseting() {
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