<%@page import="il.ac.hit.couponsproject.configuration.constants.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%-------------------------------------------------------------------------------------------%>
<%-------------	This is the admin login page, letting the user to login as an admin----------%>
<%-------------------------------------------------------------------------------------------%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login Page</title>
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap-rtl.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/style.css" />
</head>

<body>
	<div id="admin-login-container" class="container-fluid">
		<header>
			<div class="row">
				<a class="go-to-main-page btn btn-link" href="/CouponsProject">חזור לדף הראשי</a>
				<div id="admin-login-header-wrapper" class="well col-md-6 col-md-offset-3">
					<h1>התחברות מנהל</h1>
				</div>
			</div>
		</header>
		<section>
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="username-input" class="col-md-2 col-md-offset-3 control-label">שם משתמש:</label>
					<div class="col-md-2">
						<input id="username-input" type="text" class="form-control" name="<%=IConstants.USER_NAME%>" placeholder="הקלד שם משתמש...">
					</div>
				</div>
				<div class="form-group">
					<label for="password-input" class="col-md-2 col-md-offset-3 control-label">סיסמא:</label>
					<div class="col-sm-2">
						<input id="password-input" type="password" class="form-control" name="<%=IConstants.USER_PASSWORD%>"
							placeholder="הקלד סיסמא...">
					</div>
				</div>
				<div class="form-group">
					<div id="admin-login-submit-button" class="col-md-2 col-md-offset-5">
						<button id="submit-button" type="submit" class="btn btn-success btn-md col-md-4 col-md-offset-4">התחבר</button>
					</div>
				</div>

			</form>
			<div id="loading-gif-wrapper" class="col-md-4 col-md-offset-4">
				<img class="loading-gif col-md-offset-5" src="<%=contextPath%>/css/images/loading.gif" />
				<p id="result-from-server" class="lead"></p>
			</div>

		</section>
	</div>
	<form action="/CouponsProject/Controller/admin" method="POST">
		<input type="submit" id="hidden-submit" name="<%=IConstants.ACTION%>" value="<%=IConstants.FORWARD_TO_ADMIN_PAGE_ACTION%>" />
	</form>

	<script src="<%=contextPath%>/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="<%=contextPath%>/js/adminLoginPage.js"></script>
</body>
</html>