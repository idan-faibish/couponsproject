<%@page import="il.ac.hit.couponsproject.configuration.constants.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%-------------------------------------------------------------------------------------------%>
<%--------This page is offering the user to go to the admin login page or guest page---------%>
<%-------------------------------------------------------------------------------------------%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coupons - Main Page</title>
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap-rtl.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/style.css" />
</head>

<body>
	<div id="main-page-container" class="container-fluid">
		<header>
			<div class="row">
				<div id="main-page-header-wrapper" class="well col-md-6 col-md-offset-3">
					<h1>ברוכים הבאים לאפליקציית הקופונים!</h1>
				</div>
			</div>
		</header>

		<section>
			<div id="main-page-buttons-wrapper" class="row">
				<button id="guest-page-anchor" class="col-md-offset-4 btn btn-primary btn-lg">
					כניסת אורח&nbsp;&nbsp;
					<span class="glyphicon glyphicon-globe"></span>
				</button>
				<button id="admin-login-page-anchor" class="col-md-offset-2 btn btn-primary btn-lg">
					כניסת מנהל&nbsp;&nbsp;

					<span class="glyphicon glyphicon-eye-open"></span>
				</button>
			</div>
			<form action="/CouponsProject/Controller/guest" method="POST">
				<input hidden type="submit" id="guest-page-button" name="<%=IConstants.ACTION%>" value="<%=IConstants.FORWARD_TO_GUEST_PAGE_ACTION%>" />
			</form>
			<form action="/CouponsProject/Controller/adminLogin" method="POST">
				<input hidden type="submit" id="admin-login-page-button" name="<%=IConstants.ACTION%>"
					value="<%=IConstants.FORWARD_TO_ADMIN_LOGIN_PAGE_ACTION%>" />
			</form>
		</section>
	</div>
	<script src="<%=contextPath%>/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="<%=contextPath%>/js/script.js"></script>
</body>
</html>