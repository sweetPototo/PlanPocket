package com.pp.ppProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pp.ppProject.domain.MemberEntity;
import com.pp.ppProject.service.LoginService;
import com.pp.ppProject.service.RegisterMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api")
@Controller
public class LoginController {
	
	private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // 회원가입 폼을 보여주는 메서드
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // 빈 MemberEntity 객체를 모델에 추가하여 뷰에서 폼을 렌더링하도록 합니다.
        model.addAttribute("member", new MemberEntity());
        return "register"; // "register"는 JSP나 Thymeleaf 뷰의 이름입니다.
    }

    // 회원가입 요청을 처리하는 메서드
    @PostMapping("/register")
    public String registerUser(@ModelAttribute MemberEntity memberEntity, Model model) {
        try {
            boolean isRegistered = loginService.registerUser(memberEntity);
            if (isRegistered) {
                model.addAttribute("message", "회원가입 성공");
                return "redirect:/user/success"; // 회원가입 성공 페이지로 리디렉션
            } else {
                model.addAttribute("message", "회원가입 실패");
                return "register"; // 실패 시 다시 등록 페이지로 이동
            }
        } catch (Exception e) {
            model.addAttribute("message", "오류 발생");
            return "register";
        }
    }
	
	@Autowired
	RegisterMail registerMail;
	
	@GetMapping(value = { "/register" } )
	   public String transaction() {
	      return "login/register";
	   }

    @GetMapping("/checkUsername")
    @ResponseBody
    public String checkUsername(@RequestParam("username") String username) {
        
    	if (!username.matches("^[a-zA-Z0-9]*$")) {
            return "INVALID";  // 아이디가 영어와 숫자만으로 구성되지 않은 경우
        }
        
        boolean exists = checkUsernameInDatabase(username); 
        
        if (exists) {
            return "EXIST";  // 이미 아이디가 존재하는 경우
        } else {
            return "AVAILABLE";  // 사용 가능한 아이디인 경우
        }
    }

    private boolean checkUsernameInDatabase(String username) {
        return "existingUser".equals(username);
    }
    
    @GetMapping("/validateEmail")
    public ResponseEntity<String> validateEmail(@RequestParam("email") String email) {
        // 이메일 형식 검증
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (email.matches(emailRegex)) {
            return ResponseEntity.ok("VALID");
        } else {
            return ResponseEntity.ok("INVALID");
        }
    }
    @PostMapping("login/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {
       String code = registerMail.sendSimpleMessage(email);
       System.out.println("인증코드 : " + code);
       return code;
    }
}

