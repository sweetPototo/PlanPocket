package com.pp.ppProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pp.ppProject.dto.enums.TransactionCategory;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.service.InformationService;
import com.pp.ppProject.service.InformationServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Controller
public class MainController {

	@GetMapping(value = { "/", "/main" } )
	   public String home(HttpServletRequest req) {
		log.info("Start PlacPocket Project");
		settingSession(req);  //login 연결 전 정보 입력을 위한 임시 메서드
	      return "main/main";
	   }
	
	private void settingSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("memberNo", 1);
	}
	
}
