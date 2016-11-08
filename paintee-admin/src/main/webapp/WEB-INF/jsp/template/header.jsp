<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.1.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div id="wrap">
	<div class="navbar navbar-default navbar-fixed-top">
	  <div class="container">
	    <div class="navbar-header">
		    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Paintee Home</a>
	    </div>
	    
	    <div class="gnb collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<c:if test="${not empty user}">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/admin/painting/list">Upload</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/purchase/list">Purchase</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/reward/list">Reward</a></li>
			</ul>
			</c:if>
		    <ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty user}">
						<li><a href="${pageContext.request.contextPath}/admin/login/loginForm">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/admin/login/logout">Logout</a></li>
					</c:otherwise>
				</c:choose>
		    </ul>
		</div>
	  </div>
	</div>
	<div class="fixbox"></div>
	<div class="container">