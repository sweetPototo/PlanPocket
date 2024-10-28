package com.pp.ppProject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.ppProject.dto.enums.ResultCode;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.response.ResponseObject;
import com.pp.ppProject.service.InformationService;

@WebMvcTest(InformationController.class)
public class InformationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	/* 
	 * 컨트롤러의 api를 테스트하기 위해 사용되는 객체
	 * 서블릿 컨테이너의 구동 없이 가상의 MVC 환경에서 
	 * 모의 HTTP 서블릿을 요청하는 유틸리티 클래스
	 * HTTP 요청 모방
	 */
	
	@MockBean  //@MockBean : 통합 테스트(하나의 로직) @Mock : 단위 테스트(하나의 메서드)
	public InformationService infoService;
	/*
	 * 실제 빈 객체가 아닌 Mock(가짜) 객체를 생성해서 주입하는 역할
	 * @MockBean이 선언된 객체는 실제 행위를 수행 X
	 *  -> 개발자가 Mockito의 given() 메서드를 통해 동작을 정의해야 함.
	 * 
	 * final 키워드 사용 X
	 *  => final : 불변, 객체 생성 전에 필드 초기화가 완료되어야 함.
	 *  @MockBean을 사용하게 되면 스프링 컨텍스트에 의해 필드가 할당되므로 
	 *  필드 초기화 전에 객체가 생성되어서 컴파일 에러 발생
	 */
	
	/*
	 * @Mock을 사용하여 만든 객체는 @InjectMocks로 만들어진 객체에 자동주입됨.
	 * 하지만 @MockBean은 X
	 */
	
	/* @Autowired
	 * public InformationController infoController;
	 * 해당 코드는 필요 X
	 * @WebMvcTest("테스트 대상 클래스")가 "테스트 대상 클래스(컨트롤러)"를 자동으로 객체화하고 의존성을 주입하기 때
	 */
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
    public void setup() {
		objectMapper = new ObjectMapper();  //Java <-> Json 변환
    }
	/*
	 * @Autowired로 ObjectMapper를 주입하지 않는 이유 : 
	 * @Autowired로 주입하게 되면 Spring 컨텍스트가 ObjectMapper를 관리하기 때문 다른 테스트와 자원을 공유하게 되기 때문
	 * 테스트간 독립성이 중요한 경우 @BeforeEach로 초기화 하는 것이 좋다.
	 * (@Autowired를 사용하면 싱글톤 패턴으로 객체를 운용하기 때문 / Spring의 빈은 기본적으로 싱글톤 스코프)
	 */
	
	@Test
	public void addAcount() throws Exception{
		//given : 사용할 객체들 설정
		AccountRequestDTO accountRequestDto = AccountRequestDTO.builder()
				.accountName("account_1")
				.accountCategory("1")
				.accountDetail("월급계좌")
				.memberNo("1")
				.accountBalance("1000")
				.build();
		
		ResponseObject responseObject = ResponseObject.settingMsg(ResultCode.SUCCESS_CREATE, "등록 완료");
		
		//given : 동작할 메서드 설정 -> 해당 메서드가 실행되면(when) 이 객체를 반환하라(thenReturn)
		when(infoService.addAccount(accountRequestDto)).thenReturn(responseObject);
		
		//when : API 호출
		mockMvc.perform(post("/information/account")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(accountRequestDto)))
		//then : 검증
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value(responseObject.getMessage()));
		/*
		 * mockMvc.perform(...<요청에 대한 설정>) 
		 * 	- MockMvc를 사용하여 HTTP 요청을 모방하고, 해당 요청에 대한 결과를 테스트
		 *  - 이 메서드는 실제 API 호출을 시뮬레이션함.
		 * 
		 * <요청에 대한 설정>
		 * post("/information/1/account")
		 *  - HTTP POST 요청을 생성. 
		 *  - "/1/account"는 요청할 URL 경로
		 * .contentType(MediaType.APPLICATION_JSON)
		 * 	- 요청의 Content-Type 헤더를 application/json으로 설정
		 *  - 컨트롤러에서 @RequestBody를 사용하여 JSON 데이터를 처리할 수 있도록 해줌
		 * .content(objectMapper.writeValueAsString(accountRequestDto))
		 *  - 요청 본문에 포함될 데이터를 설정 (with. objectMapper.writeValueAsString
		 *  - AccountRequestDTO 객체를 JSON 문자열로 변환
		 *  - 이 변환된 JSON 문자열은 API 요청의 본문에 포함되어 서버로 전송
		 *		[관련된 다른 메서드들]
		 *			1) writeValueAsString(Object value) : Java 객체 -> Json 문자열, 로그나 HTTP 응답
		 *			2) writeValueAsBytes(Object value) : Java 객체 -> Json 바이트 배열, 파일 저장이나 네트워크 전송
		 *			3) writeValue(File resultFile, Object value) : Java 객체 -> Json 형식으로 파일 저장
		 *			4) writeValue(OutputStream out, Object value) : Java 객체 -> Json 형식으로 주어진 출력 스트림에 기록
		 *			5) readValue(String content, Class<T> valueType) : Json 문자열 -> 주어진 Java type 변환
		 *			6) readValue(byte[] src, Class<T> valueType) : Json 바이 배열 -> 주어진 Java type 변환
		 *
		 * mockMvc.perform()의 반환값은 ResultActions.class라는 인터페이스이다. 
		 * 해당 인터페이스에 대한 설명은 아래와 같다.
		 * Allows applying actions, such as expectations, on the result of an executed request.
		 * (실행된 요청의 결과에 대한 기대값과 같은 작업을 적용할 수 있습니다.)
		 * 		[ResultActions.class의 메서드]
		 * 			1) andExpect(...): 요청의 결과에 대한 기대값 설정.
		 *			2) andReturn(): 요청 결과를 반환하여 추가 처리를 가능하게 함.
		 *			3) andDo(...): 요청 및 응답에 대한 추가 동작 수행.
		 * 그래서 .andExpect() 사용 가능
		 *
		 * status().isOk() : 요청에 대한 응답의 HTTP 상태 코드를 검증. HTTP 200 상태 코드 의미	
		 * jsonPath("$.message").value(responseObject.getMessage()) : 
		 * 		jsonPath("$.message") : json 객체의 각 필드에 접근할 수 있도록 해줌. $ : json 객체 / . : 객체 속성 / [] : 배열의 인덱스
		 * 		.value(...) : jsonPath()를 통해 찾은 필드의 값이 매개값과 같은지 확인
		 */
		
	}


}
