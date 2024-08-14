<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--home.jsp-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>[PP]메인페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fafafa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: #fff;
            padding: 100px 30px; /* 위아래로 100px, 양옆으로 30px */
            border: 1px solid #ddd;
            width: 400px; /* 너비를 400px로 설정 */
            text-align: center;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .login-container h2 {
            margin-bottom: 30px; /* 제목 아래의 여백을 약간 늘림 */
            font-size: 40px; /* 제목 크기를 더 키움 */
        }
        .login-container input[type="text"], 
        .login-container input[type="password"], 
        .login-container input[type="submit"] {
            width: 100%; /* 모든 입력 필드와 버튼의 너비를 100%로 설정 */
            padding: 15px; /* 패딩을 약간 늘림 */
            margin: 15px 0; /* 입력 필드 간의 간격을 늘림 */
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box; /* 패딩과 보더가 width에 포함되도록 설정 */
            font-size: 16px; /* 입력 필드의 텍스트 크기를 키움 */
        }
        .login-container input[type="submit"] {
            background-color: #3897f0;
            color: white;
            border: none;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #3181c3;
        }
        .login-container .signup-link {
            margin-top: 25px; /* 회원가입 링크 위의 여백을 늘림 */
            font-size: 14px; /* 회원가입 링크의 텍스트 크기를 키움 */
        }
        .login-container .signup-link a {
            color: #3897f0;
            text-decoration: none;
        }
    </style>
</head>
<%@ include file="main_top.jsp"%>
<body>
    <div class="login-container">
        <h2>PlanPocket</h2>
        <form action="login" method="post">
            <input type="text" name="username" placeholder="아이디" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <input type="submit" value="로그인">
        </form>
        <div class="signup-link">
            <p>계정이 없으신가요? <a href="register">회원가입</a></p>
        </div>
    </div>
</body>
</html>
