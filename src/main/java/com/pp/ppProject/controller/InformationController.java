package com.pp.ppProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pp.ppProject.domain.category.AccountCategory;
import com.pp.ppProject.domain.category.TransactionCategory;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.InputTransactionRequestDTO;
import com.pp.ppProject.dto.request.DepoWithdDTO;
import com.pp.ppProject.service.InformationService;
import com.pp.ppProject.service.InformationServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/information")
@Slf4j
public class InformationController {
	
	public final InformationService infoService;
	
	private void setMessage(String msg, String url, HttpServletRequest req) {
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
	}
	
	@GetMapping("/{memberNo}")
	   public String input(HttpServletRequest req, @PathVariable("memberNo") int memberNo) {
		List<AccountDTO> account = new ArrayList<>();
		account = infoService.selectMemberNo(memberNo);
		req.setAttribute("account", account);
		req.setAttribute("tCate", TransactionCategory.values());
		return "information/information_input";
	   }
	
	@PostMapping("/{memberNo}/input")
	public String inputTransaction(HttpServletRequest req, @PathVariable("memberNo") int memberNo, @ModelAttribute InputTransactionRequestDTO inputDto) {
		DepoWithdDTO dto = DepoWithdDTO.createTranDTO(inputDto, memberNo);
		String msg, url = "";
		boolean isAdd = infoService.addTran(dto);
		if(isAdd){
			msg = "가계부가 입력되었습니다.";
			url = "/information/" + dto.getMemberNo();
		}else {
			msg = "가계부가 입력되지 않았습니다. 다시 시도해주세요.";
			url = "";
		}
		setMessage(msg, url, req);
		return "message";
	}
	
	@GetMapping("/account")
	public String accountGet(HttpServletRequest req) {
		log.info("start account method");
		req.setAttribute("aCate", AccountCategory.values());
		return "information/information_account";
	}
	
	@PostMapping("/account")
	public String accountPost(@ModelAttribute AccountRequestDTO dto, HttpServletRequest req) {
		boolean isAdd = infoService.addAccount(dto);
		String msg, url = "";
		if(isAdd){
			msg = "계좌 등록이 완료되었습니다.";
			url = "/information/" + dto.getMemberNo();
		}else {
			msg = "계좌 등록이 완료되지 않았습니다. 다시 시도해주세요.";
			url = "";
		}
		setMessage(msg, url, req);
		return "message";
	}
	
	
}
