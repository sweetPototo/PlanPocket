//거래추가 유효성검사
/*function tranCheck() { 
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
*/

//계좌 관련 함수 및 필드
let accountName, accountCategory, accountBalance, accountDetail;

//계좌 유효성검사
function accountCheck() { 
	accountName = document.getElementById('accountName').value;
	accountCategory = document.getElementById('accountCategory').value;
	accountBalance = document.getElementById('accountBalance').value;
	accountDetail = document.getElementById('accountDetail').value;
	
   if(accountName==""){
          alert('계좌 이름을 지정해주세요.');
          document.getElementById('accountName').focus();
           return;
   }
   
   if(accountCategory=="no"){
          alert('소비 카테고리를 지정해주세요.');
          document.getElementById('accountCategory').focus();
           return;
   }
   
   if(accountBalance == 0){
          alert('잔액을 입력해주세요.');
          document.getElementById('accountBalance').focus();
           return;
   }
  
   if(accountDetail==""){
      accountDetail= " ";
   }
   
    accountSubmit();
   }
   
function accountReseting() {
	accountName= null;
	accountCategory= "no";
	accountDetail= null;
	accountBalance=null;
	return;
	
}

function accountSubmit() {
	//객체로 값 저장
	const accountRequest = {
		accountName : accountName, //계좌 이름
		accountCategory : accountCategory, //계좌 카테고리
		accountDetail : accountDetail, //계좌 설명
		memberNo : '${sessionScope.memberNo}', //회원번호
		accountBalance : accountBalance //계좌 잔액
	}
	const url = '/information/${sessionScope.memberNo}/account';
	const method = 'POST';
	
	fetchMethod(accountRequest, url);
}