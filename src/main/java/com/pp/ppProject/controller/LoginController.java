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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final LoginService loginService;
    private final RegisterMail registerMail;

    @Autowired
    public LoginController(LoginService loginService, RegisterMail registerMail) {
        this.loginService = loginService;
        this.registerMail = registerMail;
    }

    // 회원가입 폼을 보여주는 메서드
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new MemberEntity());
        return "login/register";
    }

    // 회원가입 요청을 처리하는 메서드
    @PostMapping("/submit")
    public String registerUser(@ModelAttribute MemberEntity memberEntity, Model model) {
        try {
            boolean isRegistered = loginService.registerUser(memberEntity);
            if (isRegistered) {
                model.addAttribute("message", "회원가입 성공");
                return "redirect:/user/success"; // 회원가입 성공 페이지로 리디렉션
            } else {
                model.addAttribute("message", "회원가입 실패");
                return "login/register"; // 실패 시 다시 등록 페이지로 이동
            }
        } catch (Exception e) {
            model.addAttribute("message", "오류 발생");
            return "login/register"; // 오류 발생 시 다시 등록 페이지로 이동
        }
    }

    // 이메일 인증 코드 발송
    @PostMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email) {
        try {
            // 이메일 발송
            String code = registerMail.sendSimpleMessage(email);

            if (code != null && !code.isEmpty()) {
                return ResponseEntity.ok("SENT");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
        }
    }

    // 아이디 중복 확인
    @GetMapping("/register/checkUsername")
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
        // 실제 DB 조회 로직으로 변경 필요
        return "existingUser".equals(username);
    }

    // 이메일 유효성 검사
    @GetMapping("/register/validateEmail")
    public ResponseEntity<String> validateEmail(@RequestParam("email") String email) {
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (email.matches(emailRegex)) {
            return ResponseEntity.ok("VALID");
        } else {
            return ResponseEntity.ok("INVALID");
        }
    }
}
