<%@tag import="il.ac.hit.couponsproject.configuration.constants.IConstants"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="coupon" required="true" type="il.ac.hit.couponsproject.model.dto.Coupon" rtexprvalue="true"%>
<%
	double newPriceAfterDiscount = coupon.getValue() * (100 - coupon.getPercentDiscount()) / 100;
%>
<div class="coupon">
	<div class="coupon-right-content col-md-5">
		<div class="coupon-category col-md-12">
			<span>קטגוריה:</span>
			<span><%=coupon.getCategory()%></span>
		</div>
		<div class="coupon-name col-md-12">
			<span>שם הקופון:</span>
			<span><%=coupon.getName()%></span>
		</div>
		<div class="coupon-description col-md-12">
			<p><%=coupon.getDescription()%></p>
		</div>
	</div>
	<div class="coupon-middle-content col-md-4">
		<div class="coupon-picture col-md-12">
			<a href="#" class="thumbnail">
				<img src="<%=coupon.getPicURL()%>" />
			</a>
		</div>
	</div>
	<div class="coupon-left-content col-md-3">
		<div class="coupon-expiration-date">
			<span>תאריך ושעת תפוגה:</span>
			<br/>
			<span class="expiration-date"><%=coupon.getExpirationDate().toString().substring(0, 16)%></span>
		</div>
		<div class="coupon-price">
			<span>המחיר </span>
			<span class="original-price">
				:<%=coupon.getValue()%>
				ש"ח
			</span>
			<span class="new-price">
				<%=newPriceAfterDiscount%>
				ש"ח
			</span>
			<p>
				(לאחר
				<%=coupon.getPercentDiscount()%>% הנחה)
			</p>
		</div>
	</div>
</div>

