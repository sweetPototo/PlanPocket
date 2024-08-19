function check() { 
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
    document.f.submit();
   }
   
function reseting() {
	f.accountName.value = null;
	f.accountCategory.value = "1";
	f.accountDetail.value = null;
	return;
	
}