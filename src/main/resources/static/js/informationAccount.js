function accountCheck(memberNo) { 
   if(document.getElementById('accountName')==""){
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
   if(document.getElementById('accountCategory')=="no"){
	   alert('카테고리를 지정해주세요.');
          document.getElementById('accountCategory').focus();
           return;
   }
   if(document.getElementById('accountDetail')==""){
      document.getElementById('accountDetail') = " ";
   }
    submit(memberNo);
   }
   
function reseting() {
	document.getElementById('accountName').value = null;
	document.getElementById('accountCategory') = "1";
	document.getElementById('accountDetail') = null;
	return;
	
}

function submit(memberNo) {
	//객체로 값 저장
	const accountRequest = {
		accountName : document.getElementById('accountName').value, //계좌 이름
		accountCategory : document.getElementById('accountCategory').value, //계좌 카테고리
		accountDetail : document.getElementById('accountDetail').value, //계좌 설명
		memberNo : memberNo, //회원번호
		accountBalance : document.getElementById('accountBalance').value //계좌 잔액
	}
	const url = '/information/' + accountRequest.memberNo + '/account';
	console.log(url);
	
	fetchMethod_post(accountRequest, url);
}