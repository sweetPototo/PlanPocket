package com.pp.ppProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pp.ppProject.dto.enums.AccountCategory;
import com.pp.ppProject.dto.enums.ResultCode;
import com.pp.ppProject.dto.enums.TransactionCategory;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.InputTransactionRequestDTO;
import com.pp.ppProject.dto.response.ResponseObject;
import com.pp.ppProject.exception.UndeterminedException;
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
	
	@GetMapping("/{memberNo}")
	ModelAndView input(HttpServletRequest req) {
		List<AccountDTO> account = infoService.selectMemberNo((int) req.getSession().getAttribute("memberNo"));
		Map<String,Object> result = new HashMap<>();
		req.setAttribute("account", account);
		req.setAttribute("tCate", TransactionCategory.values());
		req.setAttribute("aCate", AccountCategory.values());
		ModelAndView mav = new ModelAndView("information/information_input");
		return mav;
	   }
	
	@PostMapping("/transaction")
	ResponseEntity<ResponseObject> inputTransaction(
				@Valid @RequestBody InputTransactionRequestDTO inputDto, HttpServletRequest req) {
		log.info("Controller accountNo = {}", inputDto.getAccountNo());
		inputDto.setMemberNo((int) req.getSession().getAttribute("memberNo"));
		DepoWithdDTO dto = new DepoWithdDTO();
		try {
			dto = DepoWithdDTO.of(inputDto);
		} catch (UndeterminedException e) {
			return new ResponseEntity<ResponseObject>(ResultCode.FAILED_UNDETERMINEDTYPE.getStatus());
		}
		ResponseObject responseObject = infoService.addTran(dto);
		return new ResponseEntity<ResponseObject>(responseObject, responseObject.getCode().getStatus());

	}
	
	@PostMapping(value = "/account")
	ResponseEntity<ResponseObject> accountPost(@Valid @RequestBody AccountRequestDTO dto) {
		log.info("계좌 등록 시작");
		ResponseObject responseObject = infoService.addAccount(dto);
		return new ResponseEntity<ResponseObject>(responseObject, responseObject.getCode().getStatus());
	}
	
}
