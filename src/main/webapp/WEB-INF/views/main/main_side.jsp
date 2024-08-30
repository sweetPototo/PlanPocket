<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> side </title>

	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/Calendar.css">
	<script src="${pageContext.request.contextPath}/js/Calendar.js"></script>
	
</head>
<body>

      <div class="side_All">
      <div class="side_logo">
      	<div class="side_img">
			<img src="${pageContext.request.contextPath}/img/PPlogo.png">
     	</div>
     	<div class="side_p">
     		<p> PlanPocket </p>
		</div>
		</div>
		
		<div class="menu_list">
            <a href="member_list.do"> ☺ 로그인 유무? </a>
            <a href="admin_board_list.do"> ☺ 가계부?ㅋㅋㅋ </a>
            <a href="admin.do"> ☺ 게시판 </a>
         </div>        	
      </div>  
      
       
