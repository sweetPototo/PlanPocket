<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[PP] 가계부 입력</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/information/information.css" />
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calender.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/informationInput.js"></script>
</head>

<%@ include file="information_top.jsp"%>
<body>
	<form name="f" method="post" action="${pageContext.request.contextPath}/">
		<div id="full">
			<div id="screen_left">
				<!-- 계좌 for문 돌릴 자리 -->
				<c:forEach var="a" items="${account}">
					<div class="account">
						<input type="radio" name="accountNo" value="${a.accountNo}">${a.accountName}
					</div>
				</c:forEach>
				<div class="form-row">
					<button type="button" class="button" onclick="addAccount()"> 추가 </button>
				</div>
			</div>
			<div id="screen_right">
				<div class="form-row">
					<label>거래 일자</label> <input type="text" id="calender"
						name="tranDate">
				</div>
				<div class="form-row">
					<label>지출/입금</label> 
						<input type="radio" name="tranType" value="0" checked>지출
						<input type="radio" name="tranType" value="1">입금
				</div>
				<div class="form-row">
					<label>소비 카테고리</label>
					<select name="tranCategory">
						<c:forEach var="cate" items="${tCate}">
							<option value="${cate.tranCateCode}">${cate.tranCateName}
						</c:forEach>
					</select>
				</div>
				<div class="form-row">
					<label>금액</label> <input type="number" name="tranAmount">&nbsp;원
				</div>
				<div class="form-row">
					<label>내용</label> <input type="text" name="tranDetail">
				</div>
				<div class="form-row">
					<button type="button" class="button" onclick="check()"> 입력 </button>
					<button type="button" class="button" onclick="reseting()"> 초기화 </button>
				</div>
			</div>
		</div>
	</form>
</body>
<%@ include file="information_bottom.jsp"%>
</html>