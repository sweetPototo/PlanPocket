<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[PP] 거래계좌 추가</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/information/information.css" />
<script type="text/javascript" src="/js/informationAccount.js"></script>
<script type="text/javascript" src="/js/fetchApi.js"></script>
</head>
<%@ include file="information_top.jsp"%>
<body>
	<!-- <form name="f" method="post" action="${pageContext.request.contextPath}/information/account"> -->
		<div id = "full">
			<div id="screen_center">
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
		</div>
	<!--  </form>-->
</body>
</html>