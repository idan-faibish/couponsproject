<%@tag import="il.ac.hit.couponsproject.model.dto.Coupon"%>
<%@tag import="java.util.Iterator"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag"%>
<%@attribute name="listOfCoupons" required="true" type="java.util.Collection"%>
<%
	Iterator<Coupon> iterator = listOfCoupons.iterator();
	while (iterator.hasNext()) {
		Coupon current = iterator.next();
%>
<customtag:showCoupon coupon="<%=current%>" />
<%
	}
%>