<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}">
		
		<div class="mb-3">
			<label for="uname">Username:</label> 
			<input type="text" class="form-control" id="username" value="${principal.user.username}">
		</div>
		<div class="mb-3">
			<label for="pwd">Password:</label> 
			<input type="password" class="form-control" id="password" placeholder="Enter password" >
		</div>

		<div class="mb-3 mt-3">
			<label for="email">Email:</label> 
			<input type="email" class="form-control" id="email"  value="${principal.user.email}">
		</div>
	</form>
	
	<button id="btn-update" class="btn btn-secondary">회원 정보 수정</button>
</div>

<script src="/js/user.js"></script>

<%@include file="../layout/footer.jsp" %> 