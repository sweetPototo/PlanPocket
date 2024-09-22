<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[PP] 가계부 입력</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/info/information.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="/js/calender.js"></script>
<script type="text/javascript" src="/js/informationInput.js"></script>
</head>

<%@ include file="information_top.jsp"%>
<body>
	<form name="f" method="post" action="${pageContext.request.contextPath}/information/${memberNo}/input">
	<div class="container">
		<div class="top">
			<div class="left">
				<c:forEach var="a" items="${account}">
					<div class="account-list">
						<input type="radio" name="accountNo" value="${a.accountNo}">${a.accountName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${a.accountBalance}&nbsp;원&nbsp;&nbsp;
					</div>
				</c:forEach>
				<div class="button-container">
					<button type="button" class="button" onclick="addAccount()"> 추가 </button>
				</div>
			</div>
			<div class="center">
				<div class = "form-row">
					<label>계좌 이름</label>
					<input type="text" name="accountName">
				</div>
				<div class="form-row">
					<label>소비 카테고리</label> 
					<select name="accountCategory">
						<c:forEach var="cate" items="${aCate}">
							<option value="${cate.accountCategoryCode}">${cate.accountCategoryName}
						</c:forEach>
					</select>
					</div>
				<div class = "form-row">
					<label>계좌 잔액</label>
					<input type="number" name="accountBalance">
				</div>
				<div class = "form-row">
					<label>계좌 설명</label>
					<input type="text" name="accountDetail">
				</div>
					<!--  <input type="hidden" name="memberNo" value="${sessionScope.memberNo}">-->
				<div class="form-row">
					<button type="button" class="button" onclick="check()"> 입력 </button>
					<button type="button" class="button" onclick="reseting()"> 초기화 </button>
				</div>
			</div>
			<div class="right">
				<div class="form-row">
					<label>거래 일자
					<input type="text" id="calender" name="tranDate">
					</label>
				</div>
				<div class="form-row">
					<label>지출/입금
						<input type="radio" name="tranType" value="0" checked>지출
						<input type="radio" name="tranType" value="1">입금
					</label> 
				</div>
				<div class="form-row">
					<label>소비 카테고리
					<select name="tranCategoryCode">
						<c:forEach var="cate" items="${tCate}">
							<option value="${cate.tranCateCode}">${cate.tranCateName}
						</c:forEach>
					</select>
					</label>
				</div>
				<div class="form-row">
					<label>금액 
						<input type="number" name="tranAmount">&nbsp;원
					</label>
				</div>
				<div class="form-row">
					<label>내용
						<input type="text" name="tranDetail">
					</label> 
				</div>
				<div class="button-container">
					<button type="button" class="button" onclick="check()"> 입력 </button>
					<button type="button" class="button" onclick="reseting()"> 초기화 </button>
				</div>
			</div>
		</div>
		<div id="bottom">
			<div class="form-row">
				<label>거래내역</label>
			</div>
		</div>
	</div>
	</form>
</body>
<%@ include file="information_bottom.jsp"%>
</html>