function fetchMethod_post(req, url){
	fetch(url, {
		method: 'POST',  //HTTP 메서드 post로 지정
		headers: {
			'Content-Type' : 'application/json' //보낼 데이터 json으로 설정
		},
		body: JSON.stringify(req)
	})
	.then(response => { 
		if (!response.ok) {  //응답상태코드가 성공이 아닐 경우(!200~299)이 아닐 경우
			throw new Error('Network response was not ok');  //에러 발생 -> catch
		}
		return response.json(); //응답 데이터를 json 형식으로 변환하여 반환
	})
	.then(data => {  //서버로부터 받은 응답 메세지
		alert(data.message);
		window.location.reload(); //
	})
	.catch(error => {
		alert('죄송합니다. 다시 시도해주십시오')
		console.error('sever having error', error);
	})
}
    
function fetchMethod_get(url, nextUrl){
	fetch(url)
	.then(response => {
		if (!response.ok) {
     	 	throw new Error('Network response was not ok');
    	}
  		return response.json()
  	})
	.then(data => {
		console.log(data)
        window.location.href = nextUrl;  // 성공 시 페이지 전환
    })
    .catch(error => {
		alert('죄송합니다. 다시 시도해주십시오')
        console.error('information_account having error', error);
		window.location.href = '/main';  // 실패 시 에러 페이지로 전환
	})
}