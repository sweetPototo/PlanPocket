package com.pp.ppProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	   public String transaction() {
	      return "information/information_input";
	   }

}
