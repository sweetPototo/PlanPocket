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

import com.pp.ppProject.domain.category.AccountCategory;
import com.pp.ppProject.domain.category.TransactionCategory;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.InputTransactionRequestDTO;
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
	
	@GetMapping("users")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(HttpStatus.OK);
    }
	
	@PostMapping("/{memberNo}/input")
	public ResponseEntity<String> inputTransaction(@RequestBody InputTransactionRequestDTO inputDto, 
			HttpServletRequest req, @PathVariable("memberNo") int memberNo) {
		log.info("Controller accountNo = {}", inputDto.getAccountNo());
		inputDto.setMemberNo(memberNo);
		DepoWithdDTO dto = DepoWithdDTO.createTranDTO(inputDto);
		String msg, url = "";
		boolean isAdd = infoService.addTran(dto);
		if(isAdd){
			msg = "가계부가 입력되었습니다.";
			url = "/information/" + dto.getMemberNo();
		}else {
			msg = "가계부가 입력되지 않았습니다. 다시 시도해주세요.";
			url = "";
		}
		HashMap<String, String> map = setMessage(msg, url);
		return map;
	}
	
//	@GetMapping("/{memberNo}/account")
//	public String accountGet(HttpServletRequest req) {
//		log.info("start account method");
//		req.setAttribute("aCate", AccountCategory.values());
//		return "information/information_account";
//	}
	
	//Response Entity 만든 후 httpstatus에 따른 결과값 지정해주기
	@PostMapping("/{memberNo}/account")
	public HashMap<String, String> accountPost(@RequestBody AccountRequestDTO dto, HttpServletRequest req) {
		log.info("계좌 등록 시작");
		boolean isAdd = infoService.addAccount(dto);
		HashMap<String, String> map = new HashMap<>();
		if(isAdd){
			//map.put("msg", "계좌 등록이 완료되었습니다.");
			map.put("Success", HttpStatus.OK);
		}else {
			//map.put("msg", "계좌 등록이 완료되지 않았습니다. 다시 시도해주세요.");
			map.put("msg", "계좌 등록이 완료되지 않았습니다. 다시 시도해주세요.");
		}
		return map;
	}
	
}
