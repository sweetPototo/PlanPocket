package com.pp.ppProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@GetMapping(value = { "/", "/main" } )
	   public String home() {
	      return "main/main";
	   }
	
	@GetMapping(value = { "/transaction" } )
	   public String transaction() {
	      return "transaction/transaction_input";
	   }
	
//	@GetMapping("main.do")
//		public String main(HttpServletRequest req) {
//			return "test";
//		}
}
