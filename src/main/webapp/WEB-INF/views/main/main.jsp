<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--home.jsp-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="css/main.css">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>[PP]메인페이지</title>
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
