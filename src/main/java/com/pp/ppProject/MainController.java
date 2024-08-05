package com.pp.ppProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	   public String home() {
	      return "redirect:main.do";
	   }
	
	@RequestMapping(value = "main.do")
		public String main(HttpServletRequest req) {
			return "test";
		}
}
