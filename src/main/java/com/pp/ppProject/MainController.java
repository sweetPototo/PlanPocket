package com.pp.ppProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pp.ppProject.domain.category.TransactionCategory;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
	
	private final Logger Logger = LoggerFactory.getLogger(MainController.class.getName());

	@GetMapping(value = { "/", "/main" } )
	   public String home() {
	      return "main/main";
	   }
	
	@GetMapping("/information")
	   public String transaction(HttpServletRequest req) {
		req.setAttribute("tCate", TransactionCategory.values());
	      return "information/information_input";
	   }

}
