<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- register.jsp -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[PP]회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <div class="register-container">
        <h2>회원가입</h2>
        <form action="register" method="post">
            <input type="text" name="username" placeholder="아이디" required>
            <div class="message"></div> <!-- 아이디 메시지 -->
            <input type="password" name="password" placeholder="비밀번호" required>
            <div class="password-message" style="color: red; font-size: 12px;"></div> <!-- 비밀번호 유효성 메시지 -->
            <input type="password" name="confirm_password" placeholder="비밀번호 확인" required>
            <div class="confirm-password-message" style="color: red; font-size: 12px;"></div> <!-- 비밀번호 일치 메시지 -->
            <input type="text" name="name" placeholder="이름" required>
            <div class="name-message" style="color: red; font-size: 12px;"></div> <!-- 이름 유효성 메시지 -->
            <input type="tel" name="phone" placeholder="전화번호 ( - 없이 숫자만 입력해주세요)" required>
            <div class="phone-message" style="color: red; font-size: 12px;"></div> <!-- 전화번호 유효성 메시지 -->
            <div class="email-section">
                <input type="email" name="email" placeholder="이메일" required>
                <button type="button" id="send-code">인증번호 받기</button>
            </div>
            <div class="email-message" style="color: red; font-size: 12px;"></div> <!-- 이메일 메시지 -->
            <input type="text" name="verification_code" placeholder="인증번호 입력">
            <div class="verification-message" style="color: red; font-size: 12px;"></div> <!-- 인증번호 메시지 -->
            <input type="submit" value="회원가입">
        </form>
    </div>
</body>
</html>
