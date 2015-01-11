<%@page import="java.util.List"%>
<%@page import="il.ac.hit.couponsproject.model.dto.Coupon"%>
<%@page import="il.ac.hit.couponsproject.configuration.constants.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag"%>
<%
	String contextPath = request.getContextPath();
%>
<%-----------------------------------------------------------------------------------------------%>
<%----------------This page shows to the guest the coupons he chose in the guest page------------%>
<%-----------------------------------------------------------------------------------------------%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Favorites Coupons of The User</title>
<%------------------%>
<%--------CSS-------%>
<%------------------%>
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap-rtl.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/style.css" />
</head>
<body>
	<%--shows the favorites coupons of the guest user--%>
	<%
		List<Coupon> favoritesCoupons = (List<Coupon>) session.getAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST);
		if (favoritesCoupons != null && favoritesCoupons.size() != 0) {
	%>
	<form action="/CouponsProject/Controller/guest" method="POST">
		<button type="submit" class="go-to-guest-page btn btn-link" name="<%=IConstants.ACTION%>"
			value="<%=IConstants.FORWARD_TO_GUEST_PAGE_ACTION%>">חזור אל עמוד האורח</button>
	</form>
	<div id="favorites-coupons" class="alert alert-success" role="alert">
		<span class="glyphicon glyphicon-info-sign" aria-hidden="true">&nbsp;</span>
		<span>הקופונים המועדפים עלייך הם:</span>
	</div>
	<%--------showCouponsList custom tag-------%>
	<customtag:showCouponsList listOfCoupons="<%=favoritesCoupons%>" />
	<%
		} else {
	%>
	<%--shows an error message in case the list is empty or not exists--%>
	<div id="wrong-session-coupons" class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">&nbsp;</span>
		<span>משתמש יקר, לא ניתן היה להחזיר את הקופונים המועדפים עלייך (לא הוספת אף קופון לרשימה או שאין נתונים במערכת הקופונים)</span>
		<br />
		<form action="/CouponsProject/Controller/guest" method="POST">
			<button type="submit" class="btn btn-link" name="<%=IConstants.ACTION%>" value="<%=IConstants.FORWARD_TO_GUEST_PAGE_ACTION%>">חזור
				אל עמוד האורח</button>
		</form>
	</div>
	<%
		}
	%>
	<%------------------%>
	<%------Scripts-----%>
	<%------------------%>
	<script src="<%=contextPath%>/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="<%=contextPath%>/js/favoritesCoupons.js"></script>
</body>
</html>