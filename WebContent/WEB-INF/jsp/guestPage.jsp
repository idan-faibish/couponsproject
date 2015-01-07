<%@page import="il.ac.hit.couponsproject.configuration.constants.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%-------------------------------------------------------------------------------------------%>
<%-----------This page shows to the guest the different non-expired coupons------------------%>
<%-----------and lets him choose some as his favorites coupons-------------------------------%>
<%-------------------------------------------------------------------------------------------%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guest Page</title>
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap-rtl.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/jquery/jquery-ui.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/style.css" />
</head>
<body>
	<a class="go-to-main-page btn btn-link" href="/CouponsProject">חזור לדף הראשי</a>
	<div id="favorites-coupons" class="alert alert-success" role="alert">
		<span class="glyphicon glyphicon-plus" aria-hidden="true">&nbsp;</span>
		<span>בחירת קופונים מועדפים</span>
	</div>
	<form action="/CouponsProject/Controller/guest/favoritesCoupons" method="POST">
		<div id="buttons-guest-page-wrapper" class="row">
			<div id="closest-coupon-button-wrapper" class="col-md-4 col-md-offset-1">
				<button id="closest-coupon-button" class="btn btn-success btn-lg">הקופונים הקרובים ביותר אליי</button>
			</div>
			<div id="favorites-coupons-button-wrapper" class="col-md-4 col-md-offset-3">
				<button type="submit" id="favorites-coupons-button" class="btn btn-success btn-lg" name="<%=IConstants.ACTION%>"
					value="<%=IConstants.FORWARD_WITH_UPDATED_FAVORITES_COUPONS_ACTION%>">צפייה בקופונים המועדפים עליי</button>
			</div>
		</div>
	</form>
	<form action="/CouponsProject/Controller/guest/closestCoupons" method="GET">
		<input hidden type="submit" id="hidden-input-closest-coupon" name="<%=IConstants.ACTION%>"
			value="<%=IConstants.RETRIEVE_THREE_CLOSEST_COUPONS_ACTION%>" />
		<input hidden type="text" id="hidden-user-longitude" name="<%=IConstants.LONGITUDE%>" />
		<input hidden type="text" id="hidden-user-latitude" name="<%=IConstants.LATITUDE%>" />
	</form>
	<div class="accordion-wrapper">
		<div id="accordion" class="panel-group">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">
						<a href="#health" data-toggle="collapse" data-parent="#accordion">בריאות</a>
					</div>
				</div>
				<div class="collapse panel-collapse" id="health">
					<div class="panel-body"></div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">
						<a href="#restaurants" data-toggle="collapse" data-parent="#accordion">מסעדות</a>
					</div>
				</div>
				<div class="collapse panel-collapse" id="restaurants">
					<div class="panel-body"></div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">
						<a href="#leisure" data-toggle="collapse" data-parent="#accordion">פנאי</a>
					</div>
				</div>
				<div class="collapse panel-collapse" id="leisure">
					<div class="panel-body"></div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">
						<a href="#vacation" data-toggle="collapse" data-parent="#accordion">נופש</a>
					</div>
				</div>
				<div class="collapse panel-collapse in" id="vacation">
					<div class="panel-body"></div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">
						<a href="#tickets" data-toggle="collapse" data-parent="#accordion">כרטיסים</a>
					</div>
				</div>
				<div class="collapse panel-collapse" id="tickets">
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
	</div>
	<input hidden type="text" id="context-path" value="<%=contextPath%>" />
	<script src="<%=contextPath%>/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="<%=contextPath%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/jquery/jquery.geocomplete.js"></script>
	<script src="<%=contextPath%>/js/guestPage.js"></script>
</body>
</html>