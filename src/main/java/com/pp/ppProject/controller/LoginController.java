package com.pp.ppProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	
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
}

