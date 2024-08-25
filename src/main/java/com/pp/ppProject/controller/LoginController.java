package com.pp.ppProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pp.ppProject.domain.MemberEntity;
import com.pp.ppProject.repository.MemberRepository;
import com.pp.ppProject.service.LoginService;
import com.pp.ppProject.service.RegisterMail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final LoginService loginService;
    private final RegisterMail registerMail;
    private final MemberRepository memberRepository;

    @Autowired
    public LoginController(LoginService loginService, RegisterMail registerMail, MemberRepository memberRepository) {
        this.loginService = loginService;
        this.registerMail = registerMail;
        this.memberRepository = memberRepository;
    }

    // 회원가입 폼을 보여주는 메서드
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new MemberEntity());
        return "login/register";
    }

    // 회원가입 요청을 처리하는 메서드
    @PostMapping("/register/submit")
    @ResponseBody
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody MemberEntity memberEntity) {
        Map<String, String> response = new HashMap<>();        
        try {       
            boolean isRegistered = loginService.registerUser(memberEntity);
            if (isRegistered) {
                response.put("message", "회원가입 성공");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "회원가입 실패");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.put("message", "오류 발생");
            e.printStackTrace();  // 스택 트레이스를 로그에 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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

        boolean exists = memberRepository.existsByMemberId(username);
        if (exists) {
            return "EXIST";  // 이미 아이디가 존재하는 경우
        } else {
            return "AVAILABLE";  // 사용 가능한 아이디인 경우
        }
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
    
    @PostMapping("/verifyCode")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> verifyCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String verificationCode = request.get("verificationCode");

        boolean isValid = loginService.verifyCode(email, verificationCode);

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);

        return ResponseEntity.ok(response);
    }
}
