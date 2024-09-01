function fetchMethod(req, url, method){
	//fetch api를 이용하여 전송
	fetch(url, { //요청보낼 서버 URL
		method: method,  //HTTP 메서드로 post 사용
		headers: {
			'Content-Type' : 'application/json' //헤더를 설정하여 보낼 데이터가 json임을 알림
		},
		body: JSON.stringify(req) //객체를 json 문자열로 변환하여 전송  ----> 이부분 get방식때 어떻게 할건지 수정해야 
	})
	.then(response => {  //서버에서의 응답 처리하는 코드
		if (!response.ok) {  //응답상태코드가 성공이 아닐 경우(!200~299)이 아닐 경우
			throw new Error('Network response was not ok');  //에러 발생 -> catch로 넘어감
		}
		return response.json(); //응답 데이터를 json 형식으로 변환하여 반환
	})
	.then(data => {  //서버로부터 받은 응답 메세지
		alert(data.msg);
		window.location.href = data.url;
	})
	.catch(error => {
		alert('죄송합니다. 다시 시도해주십시오')
		console.error('information_account having error', error);
		window.history.back();
	})
}