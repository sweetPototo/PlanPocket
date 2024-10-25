package com.pp.ppProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pp.ppProject.domain.enums.AccountCategory;
import com.pp.ppProject.domain.enums.TransactionCategory;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.InputTransactionRequestDTO;
import com.pp.ppProject.dto.response.Message;
import com.pp.ppProject.dto.request.DepoWithdDTO;
import com.pp.ppProject.service.InformationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/information")
@Slf4j
public class InformationController {
	
	public final InformationService infoService;
	
	private HashMap<String, String> setMessage(String msg, String url) {
		HashMap<String, String> map = new HashMap<>();
		map.put("msg", msg);
		map.put("url", url);
		return map;
	}
	
	@GetMapping("/{memberNo}")
	   public ModelAndView input(HttpServletRequest req, @PathVariable("memberNo") int memberNo) {
		List<AccountDTO> account = infoService.selectMemberNo(memberNo);
		Map<String,Object> result = new HashMap<>();
		req.setAttribute("account", account);
		req.setAttribute("tCate", TransactionCategory.values());
		req.setAttribute("aCate", AccountCategory.values());
		ModelAndView mav = new ModelAndView("information/information_input");
		return mav;
	   }
	
	@PostMapping("/{memberNo}/input")
	public ResponseEntity<Message> inputTransaction(@RequestBody InputTransactionRequestDTO inputDto, 
			HttpServletRequest req, @PathVariable("memberNo") int memberNo) {
		
		log.info("Controller accountNo = {}", inputDto.getAccountNo());
		inputDto.setMemberNo(memberNo);
		DepoWithdDTO dto = DepoWithdDTO.createTranDTO(inputDto);
		String msg, url = "";
//		boolean isAdd = infoService.addTran(dto);
//		if(isAdd){
//			msg = "가계부가 입력되었습니다.";
//			url = "/information/" + dto.getMemberNo();
//		}else {
//			msg = "가계부가 입력되지 않았습니다. 다시 시도해주세요.";
//			url = "";
//		}
//		HashMap<String, String> map = setMessage(msg, url);
		return null;
	}
	
	//Response Entity 만든 후 httpstatus에 따른 결과값 지정해주기
	//@PostMapping(value = "/{memberNo}/account", consumes = "application/json")
	@PostMapping(value = "/{memberNo}/account")
	public ResponseEntity<Message> accountPost(@RequestBody AccountRequestDTO dto) {
		log.info("계좌 등록 시작");
		Message msg = infoService.addAccount(dto);
		return new ResponseEntity<Message>(msg, HttpStatus.OK);
	}
	
}
