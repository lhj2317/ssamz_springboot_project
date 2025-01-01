<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- 로그인 인증에 성공한 브라우저만 접근할 수 있는 영역 -->
<sec:authorize access="isAuthenticated()">
	<!-- principal은 로그인 성공한 사용자(User) 객체에 접근할 수 있는 변수다. -->
	<sec:authentication var="principal" property="principal"/> 
</sec:authorize>
			
<!DOCTYPE html>
<html lang="en">
<head>
<title>JBlog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
<!-- summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<!-- summernote css/js end -->

</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<c:if test="${principal == null}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/insertUser">회원가입</a></li>
				</ul>
			</c:if>
			<c:if test="${principal != null}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/user/updateUser">회원상세</a></li>
					<li class="nav-item"><a class="nav-link" href="/post/insertPost"> 포스트등록</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/logout">로그아웃</a></li>
				</ul>
			</c:if>
		</div> 
	</nav>
	<br>