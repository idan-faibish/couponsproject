<%@page import="il.ac.hit.couponsproject.configuration.constants.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%------------------------------------------------------------------------------------------------------%>
<%-------------	This is the admin page, letting the admin to make different manageable operations-------%>
<%------------------------------------------------------------------------------------------------------%>
<!DOCTYPE html>
<html dir="rtl">
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<%------------------%>
<%--------CSS-------%>
<%------------------%>
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap/bootstrap-rtl.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/jquery/jquery-ui.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/select2/select2.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/select2/select2-bootstrap.css" />
<link rel="stylesheet" href="<%=contextPath%>/css/style.css" />
</head>
<body>
	<%
		String loginStatus = (String) session.getAttribute(IConstants.ATTR_LOGIN_STATUS);
		//check if the user login successfully
		if (loginStatus != null && loginStatus.equals(IConstants.OK)) {
	%>
	<%---------------------%>
	<%--Table and Buttons--%>
	<%---------------------%>

	<div id="admin-page-container" class="container-fluid">
		<header>
			<div class="row">
				<a id="logout-link" class="go-to-main-page btn btn-link" href="/CouponsProject">התנתק וחזור לדף הראשי</a>
				<div id="admin-page-header-wrapper" class="well col-md-6 col-md-offset-3">
					<h1>עמדת בקרה - מנהל</h1>
				</div>
			</div>
		</header>
		<section>
			<div id="buttons-admin-page-wrapper" class="row">
				<div id="admin-page-add-button" class="col-md-4">
					<button id="add-coupon-button" class="btn btn-success btn-lg">הוסף קופון חדש</button>
				</div>
				<div id="admin-page-update-button" class="col-md-4">
					<button id="update-coupon-button" class="btn btn-success btn-lg">עדכן קופון קיים</button>
				</div>
				<div id="admin-page-remove-button" class="col-md-4">
					<button id="delete-coupon-button" class="btn btn-success btn-lg">הסר קופון קיים</button>
				</div>
			</div>

			<div class='admin-coupons-table-wrapper col-md-12'>
				<table class="table table-bordered table-striped table-hover" id="admin-coupons-table">
					<thead>
						<tr>
							<th>מס' מזהה</th>
							<th>שם</th>
							<th>תיאור</th>
							<th>קטגוריה</th>
							<th>שווי</th>
							<th>הנחה באחוזים</th>
							<th>תאריך תפוגה(כולל)</th>
							<th>כתובת תמונה</th>
							<th>קו אורך</th>
							<th>קו רוחב</th>
						</tr>
					</thead>
					<tbody>


					</tbody>

				</table>
			</div>
		</section>
	</div>

	<%--------------------------%>
	<%--Add Coupon Dialog Form--%>
	<%--------------------------%>

	<div class="container" id="add-coupon-dialog-form" title="הוסף קופון">
		<form id="add-coupon-form" class="form-horizontal">
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-lg-3" for="add-id">מס' מזהה :</label>
				<div class="controls col-lg-9">
					<input type="text" class="form-control input-text" id="add-id" name="id" pattern="[0-9]{3,5}" required>
				</div>
			</div>

			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-name">שם :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="add-name" name="name" pattern="[0-9a-zA-Z\u0591-\u05F4 ]{2,30}"
						required>
				</div>
			</div>

			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-description">תיאור :</label>
				<div class="controls col-md-9">
					<textarea rows="5" class="form-control input-text invalid-red" id="add-description" name="description" required></textarea>
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-category">קטגוריה :</label>
				<div class="controls col-md-9">
					<select class="form-control" id="add-category" name="category" required>
						<option></option>
						<option value="1">בריאות</option>
						<option value="2">מסעדות</option>
						<option value="3">פנאי</option>
						<option value="4">נופש</option>
						<option value="5">כרטיסים</option>
					</select>
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-value">שווי :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="add-value" name="value" pattern="^\d{1,6}(.[0-9]{1,2})?$" required />
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-percent-discount">הנחה באחוזים :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="add-percent-discount" name="percentDiscount"
						pattern="^[1-9][0-9]?$|^100$" required />
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-expiration-date">תאריך תפוגה :</label>
				<div class="controls col-md-7">
					<input type="text" class="form-control input-text invalid-red" id="add-expiration-date" name="expirationDate" required
						readonly placeholder="לחץ פה כדי לפתוח לוח שנה" />
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-expiration-time">שעת תפוגה :</label>
				<div class="controls col-md-7">
					<input type="time" class="form-control input-text invalid-red" id="add-expiration-time" name="expirationTime" required />
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-pic-url">כתובת תמונה :</label>
				<div class="controls col-md-9">
					<input type="url" class="form-control input-text" id="add-pic-url" name="picUrl" required />
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-longitude">קו אורך :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="add-longitude" name="longitude" pattern="^\d{2,3}(\.[0-9]+)?$"
						required />
				</div>
			</div>
			<div class="control-group add-coupon-dialog-input">
				<label class="control-label col-md-3" for="add-latitude">קו רוחב :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="add-latitude" name="latitude" pattern="^\d{2,3}(\.[0-9]+)?$" required />
				</div>
			</div>
			<input type="submit" class="hidden-add-submit" hidden />

			<div class="control-group">
				<div class="controls col-md-12">
					<span class="col-md-offset-4" id="result-message"></span>
				</div>
			</div>
		</form>
	</div>

	<%-----------------------------%>
	<%--Update Coupon Dialog Form--%>
	<%-----------------------------%>

	<div class="container" id="update-coupon-dialog-form" title="הוסף קופון">
		<form id="update-coupon-form" class="form-horizontal">
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-id">מס' זיהוי לעדכון :</label>
				<div class="controls col-md-9">
					<input type="text" class="formt-control" id="update-id" name="id" required />
				</div>
			</div>

			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-name">שם :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="update-name" name="name" pattern="[0-9a-zA-Z\u0591-\u05F4 ]{2,30}"
						required />
				</div>
			</div>

			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-description">תיאור :</label>
				<div class="controls col-md-9">
					<textarea rows="5" class="form-control input-text invalid-red" id="update-description" name="description" required></textarea>
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-category">קטגוריה :</label>
				<div class="controls col-md-9">
					<select class="form-control" id="update-category" name="category" required>
						<option></option>
						<option value="1">בריאות</option>
						<option value="2">מסעדות</option>
						<option value="3">פנאי</option>
						<option value="4">נופש</option>
						<option value="5">כרטיסים</option>
					</select>
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-value">שווי :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="update-value" name="value" pattern="^\d{1,6}(.[0-9]{1,2})?$" required />
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-percent-discount">הנחה באחוזים :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="update-percent-discount" name="percentDiscount"
						pattern="^[1-9][0-9]?$|^100$" required />
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-expiration-date">תאריך תפוגה :</label>
				<div class="controls col-md-7">
					<input type=text class="form-control input-text invalid-red" id="update-expiration-date" name="expirationDate" required
						readonly placeholder="לחץ פה כדי לפתוח לוח שנה" />
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for=update-expiration-time">שעת תפוגה :</label>
				<div class="controls col-md-7">
					<input type="time" class="form-control input-text invalid-red" id="update-expiration-time" name="expirationTime" required />
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-pic-url">כתובת תמונה :</label>
				<div class="controls col-md-9">
					<input type="url" class="form-control input-text" id="update-pic-url" name="picUrl" required />
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-longitude">קו אורך :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="update-longitude" name="longitude" pattern="^\d{2,3}(\.[0-9]+)?$"
						required />
				</div>
			</div>
			<div class="control-group update-coupon-dialog-input">
				<label class="control-label col-md-3" for="update-latitude">קו רוחב :</label>
				<div class="controls col-md-9">
					<input type="text" class="form-control input-text" id="update-latitude" name="latitude" pattern="^\d{2,3}(\.[0-9]+)?$"
						required />
				</div>
			</div>
			<input type="submit" class="hidden-update-submit" hidden />

			<div class="control-group">
				<div class="controls col-md-12">
					<span class="col-md-offset-4" id="update-result-message"></span>
				</div>
			</div>
		</form>
	</div>

	<%-----------------------------%>
	<%--Delete Coupon Dialog Form--%>
	<%-----------------------------%>

	<div class="container" id="delete-coupon-dialog-form" title="מחק קופון">
		<form id="delete-coupon-form" class="form-horizontal">
			<div class="control-group delete-coupon-dialog-input">
				<label class="control-label col-md-4" for="delete-id">מס' זיהוי למחיקה :</label>
				<div class="controls col-md-8">
					<input type="text" class="formt-control" id="delete-id" name="id" required />
				</div>
			</div>

			<input type="submit" class="hidden-delete-submit" hidden />

			<div class="control-group">
				<div class="controls col-md-12">
					<span class="col-md-offset-4" id="delete-result-message"></span>
				</div>
			</div>
		</form>
	</div>
	<%
		//show an error message if the user didn't login
		} else {
	%>
	<div id="not-connected" class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">&nbsp;</span>
		<span>משתמש יקר, אינך מחובר למערכת</span>
		<br />
		<form action="/CouponsProject/Controller/adminLogin" method="POST">
			<button type="submit" class="btn btn-link" name="<%=IConstants.ACTION%>" value="<%=IConstants.FORWARD_TO_ADMIN_LOGIN_PAGE_ACTION%>">חזור
				אל דף ההתחברות</button>
		</form>
	</div>
	<%
		}
	%>
	<%------------------%>
	<%------Scripts-----%>
	<%------------------%>
	<script src="<%=contextPath%>/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="<%=contextPath%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/jquery/jquery-ui.min.js"></script>
	<script src="<%=contextPath%>/js/select/select2.min.js"></script>
	<script src="<%=contextPath%>/js/select/select2_locale_he.js"></script>
	<script src="<%=contextPath%>/js/adminPage.js"></script>
</body>
</html>