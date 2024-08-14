package com.pp.ppProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pp.ppProject.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
	
	//private final TransactionService transactionService;
	
	@GetMapping("asd")
	public String main() {
		return "";
	}
	

}
