<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[PP] 가계부 입력</title>
<style>
    #full {
        width: 100%;
        height: 100%;
        background-color: grey;
    }
    #screen_left {
        width: 48%;
        height: 100%;
        background-color: black;
        float: left;
        padding: 10px;
    }
    #screen_right {
        width: 48%;
        height: 100%;
        background-color: blue;
        float: right;
        padding: 10px;
    }
    .account, .form-row {
        margin-bottom: 10px;
        color: white;
    }
    label {
        display: inline-block;
        width: 100px;
    }
    input {
        width: 200px;
    }
</style>
</head>
<%@ include file="information_top.jsp"%>
<body>
    <div id="full">
        <div id="screen_left">
            <div class="account">계좌 1</div>
            <div class="account">계좌 2</div>
            <div class="account">계좌 3</div>
            <div class="account">계좌 4</div>
            <div class="account">계좌 5</div>
        </div>
        <div id="screen_right">
            <form name="input" method="post" action="${pageContext.request.contextPath}/">
                <div class="form-row">
                    <label>거래 일자</label>
                    <input type="text" name="tranDate">
                </div>
                <div class="form-row">
                    <label>지출/입금</label>
                    <input type="text" name="expenseIncome">
                </div>
                <div class="form-row">
                    <label>결제 수단</label>
                    <input type="text" name="paymentMethod">
                </div>
                <div class="form-row">
                    <label>카테고리</label>
                    <input type="text" name="category">
                </div>
                <div class="form-row">
                    <label>금액</label>
                    <input type="text" name="amount">
                </div>
                <div class="form-row">
                    <label>내용</label>
                    <input type="text" name="description">
                </div>
            </form>
        </div>
    </div>
</body>
<%@ include file="information_bottom.jsp"%>
</html>
