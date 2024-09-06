package com.pp.ppProject.controller;

import com.pp.ppProject.domain.MemberEntity;
import com.pp.ppProject.repository.MemberRepository;
import com.pp.ppProject.service.LoginService;
import com.pp.ppProject.service.RegisterMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new MemberEntity());
        return "login/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody MemberEntity memberEntity) {
        Map<String, String> response = new HashMap<>();

        // 비밀번호 유효성 검사 추가
        if (memberEntity.getMemberPasswd() == null || memberEntity.getMemberPasswd().trim().isEmpty()) {
            response.put("message", "비밀번호는 null이거나 빈 문자열일 수 없습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email) {
        try {
            String code = registerMail.sendSimpleMessage(email);
            if (code != null && !code.isEmpty()) {
                return ResponseEntity.ok("SENT");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
        }
    }

    @GetMapping("/checkUsername")
    @ResponseBody
    public ResponseEntity<String> checkUsername(@RequestParam("username") String username) {
        if (!username.matches("^[a-zA-Z0-9]*$")) {
            return ResponseEntity.ok("INVALID");
        }

        boolean exists = memberRepository.existsByMemberId(username);
        if (exists) {
            return ResponseEntity.ok("EXIST");
        } else {
            return ResponseEntity.ok("AVAILABLE");
        }
    }

    @GetMapping("/validateEmail")
    @ResponseBody
    public ResponseEntity<String> validateEmail(@RequestParam("email") String email) {
        System.out.println("Received email for validation: " + email);  // 로그 출력
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (email.matches(emailRegex)) {
            System.out.println("Email is valid.");
            return ResponseEntity.ok("VALID");
        } else {
            System.out.println("Email is invalid.");
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
