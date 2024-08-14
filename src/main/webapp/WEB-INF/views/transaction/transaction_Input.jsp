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
        width: 50%;
        height: 100%;
        background-color: black;
        float: left;
    }
    #screen_right {
        width: 50%;
        height: 100%;
        background-color: blue;
        float: right;
    }
</style>
</head>
<%@ include file="transaction_top.jsp"%>
<body>
	<div id="full">
		<div id="screan_left">
			<div style="display:table; width:100%;">
				<div style="display:table-row">
					<div style="display:table-cell"> 계좌 1</div>
					<div style="display:table-cell"> 계좌 2</div>
					<div style="display:table-cell"> 계좌 3</div>
					<div style="display:table-cell"> 계좌 4</div>
					<div style="display:table-cell"> 계좌 5</div>
				</div>
			</div>
		</div>
		<div id="screan_right">
			<form name="input" method="post" action="${pageContext.request.contextPath}/">
			<div style="display:table; width:100%;">
				<div style="display:table-row">
					<div style="display:table-cell"> 거래 일자 </div>
					<div style="display:table-cell"> 
						<input type="text" name="tranDate">
					</div>
					<div style="display:table-cell"> 지출/입금 </div>
					<div style="display:table-cell"> 결제 수단 </div>
					<div style="display:table-cell"> 카테고리 </div>
					<div style="display:table-cell"> 금액 </div>
					<div style="display:table-cell"> 내용 </div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
<%@ include file="transaction_bottom.jsp"%>
</html>