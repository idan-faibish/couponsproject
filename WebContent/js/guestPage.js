$(document).ready(function() {
	
	initializeCouponsInAccordion();

	//disable the default behavior of concatenating the character '#' when clicking on element with 'thumbnail' class
	$('.panel-body').on('click', '.thumbnail', function(event) {
		event.preventDefault();
	});

	//ajax request for adding a coupon to the favorites of the current session
	$('.panel-body').on('click', '.empty-star', function(event) {
		event.preventDefault();
		var idToAdd = $(this).attr('id');
		$.ajax({
			url : '/CouponsProject/Controller',
			type : 'POST',
			dataType : 'text',
			context : this,
			data : {
				action : 'add-favorite-coupon-action',
				id : idToAdd 
			}, 
			success : function(resultFromServer) {
				if (resultFromServer === 'success') {
					console.log('add success');
					var anchor = $(this).closest('a');
					$(this).remove();
					anchor.append($('<span id="' + idToAdd + '" class="full-star glyphicon glyphicon-star"></span>'));
					var image = $('<img src="'+$('#context-path').val()+'/css/images/valid.png"/>');
					anchor.append(image);
					image.show().fadeOut(1000);
				} else {
					console.log('add failure');
					var anchor = $(this).closest('a');
					var image = $('<img src="'+$('#context-path').val()+'/css/images/error.png"/>');
					anchor.append(image);
					image.show().fadeOut(1000);
				}
			}
		});
	});

	//ajax request for deleting a coupon from the favorites of the current session
	$('.panel-body').on('click', '.full-star', function(event) {
		event.preventDefault();
		var idToRemove = $(this).attr('id');
		$.ajax({
			url : '/CouponsProject/Controller',
			type : 'POST',
			dataType : 'text',
			context : this,
			data : {
				action : 'delete-favorite-coupon-action',
				id : idToRemove
			},
			success : function(resultFromServer) {
				if (resultFromServer === 'success') {
					console.log('delete success');
					var anchor = $(this).closest('a');
					$(this).remove();
					anchor.append($('<span id="' + idToRemove + '" class="empty-star glyphicon glyphicon-star-empty"></span>'));
					var image = $('<img src="'+$('#context-path').val()+'/css/images/valid.png"/>');
					anchor.append(image);
					image.show().fadeOut(1000);
				} else {
					console.log('delete failure');
					var anchor = $(this).closest('a');
					var image = $('<img src="'+$('#context-path').val()+'/css/images/error.png"/>');
					anchor.append(image);
					image.show().fadeOut(1000);
				}
			}
		});
	});

	//get the longitude and latitude of the user and forwards him to a page to show him the the closest coupons
	$('#closest-coupon-button').on('click', function(event) {
		event.preventDefault();
		navigator.geolocation.getCurrentPosition(GetLocation);
	});

});


//initializing the accordion with the non-expired coupons
function initializeCouponsInAccordion() {
	$.ajax({
		url : '/CouponsProject/Controller',
		type : 'POST',
		dataType : 'json',
		data : {
			action : 'retrieve-non-expired-coupons-action'
		},
		success : function(coupons) {
			var favoritesCoupons = JSON.parse(ajaxResponse());
			console.log(favoritesCoupons);
			$.each(coupons, function() {
				var anchor = $('.accordion-wrapper #accordion a:contains("' + this.category + '")');
				var panelBody = anchor.closest('.panel-heading').next('div').find('.panel-body');
				var couponOverview = $('<div class="coupon-overview well col-xs-11"></div>');
				var couponName = $('<div class="col-xs-3"></div>');
				var couponValue = $('<div class="col-xs-2"></div>');
				var couponDiscount = $('<div class="col-xs-1"></div>');
				var couponDate = $('<div class="col-xs-2"></div>');
				var couponPicture = $('<div class="col-xs-4"></div>');
				var star = $('<div class="coupon-star col-xs-1"></div>');

				couponName.append($('<span>שם קופון:</span>'));
				couponName.append($('<p></p>').text(this.name));
				couponValue.append($('<span>שווי:</span>'));
				couponValue.append($('<p></p>').text(this.value + '\u20AA'));
				couponDiscount.append($('<span>הנחה:</span>'));
				couponDiscount.append($('<p></p>').text(this.percentDiscount + '%'));
				couponDate.append($('<span>תפוגה:</span>'));
				couponDate.append($('<p></p>').text(dateOrTimeFormatted(new Date(this.expirationDate), 'dateAndTime')));
				couponPicture.append($('<a href="#" class="thumbnail"></a>').append($('<img/>').attr('src', this.picURL)));
				var isExist = false;
				for ( var favorite in favoritesCoupons) {
					if (this.id === favoritesCoupons[favorite].id) {
						isExist = true;
						break;
					}
				}
				if (isExist === false) {
					star.append($('<a href="#"></a>').append($('<span id="' + this.id + '" class="empty-star glyphicon glyphicon-star-empty"></span>')));
				} else {
					star.append($('<a href="#"></a>').append($('<span id="' + this.id + '" class="full-star glyphicon glyphicon-star"></span>')));
				}

				couponOverview.append(couponName);
				couponOverview.append(couponValue);
				couponOverview.append(couponDiscount);
				couponOverview.append(couponDate);
				couponOverview.append(couponPicture);
				panelBody.append(couponOverview);
				panelBody.append(star);
			});
		}
	});
	
	//refresh the page every 1 minute
	setInterval(function() {
		window.location.reload();
	}, 1000*60);
}

//make sure the non-expired list is show with the right 'full start' or 'empty start' according to a previous choose of the user
function ajaxResponse() {
	var favoritesCoupons = $.ajax({
		url : '/CouponsProject/Controller',
		type : 'POST',
		dataType : 'json',
		async : false,
		data : {
			action : 'update-and-retrieve-favorites-coupons-action'
		}
	}).responseText;
	return favoritesCoupons;
}


var GetLocation = function(location) {
	var userLongitude = location.coords.longitude;
	var userLatitude = location.coords.latitude;
	$('#hidden-user-longitude').val(userLongitude);
	$('#hidden-user-latitude').val(userLatitude);
	console.log($('#hidden-user-longitude').val());
	console.log($('#hidden-user-latitude').val());
	$('#hidden-input-closest-coupon').click();
};

/*this function is used to parse date object to string according to the given indicator string:
dateAndTime ---> yyyy-MM-dd HH:mm
dateOnly    ---> yyyy-MM-dd
timeOnly    ---> HH:mm*/
function dateOrTimeFormatted(dateObject, indicator) {
	var dateString = '';
	if (indicator === 'dateAndTime') {
		dateString = dateObject.getFullYear();
		dateString = dateString + '-';
		dateString = dateString + ((dateObject.getMonth() < 10) ? ('0' + (dateObject.getMonth() + 1)) : (dateObject.getMonth() + 1));
		dateString = dateString + '-';
		dateString = dateString + ((dateObject.getDate() < 10) ? ('0' + dateObject.getDate()) : (dateObject.getDate()));
		dateString = dateString + ' ';
		dateString = dateString + ((dateObject.getHours() < 10) ? ('0' + dateObject.getHours()) : (dateObject.getHours()));
		dateString = dateString + ':';
		dateString = dateString + ((dateObject.getMinutes() < 10) ? ('0' + dateObject.getMinutes()) : (dateObject.getMinutes()));
		return dateString;
	} else if (indicator === 'dateOnly') {
		dateString = dateObject.getFullYear();
		dateString = dateString + '-';
		dateString = dateString + ((dateObject.getMonth() < 10) ? ('0' + (dateObject.getMonth() + 1)) : (dateObject.getMonth() + 1));
		dateString = dateString + '-';
		dateString = dateString + ((dateObject.getDate() < 10) ? ('0' + dateObject.getDate()) : (dateObject.getDate()));
		return dateString;
	} else if (indicator === 'timeOnly') {
		dateString = dateString + ((dateObject.getHours() < 10) ? ('0' + dateObject.getHours()) : (dateObject.getHours()));
		dateString = dateString + ':';
		dateString = dateString + ((dateObject.getMinutes() < 10) ? ('0' + dateObject.getMinutes()) : (dateObject.getMinutes()));
		return dateString;
	} else {
		return undefined;
	}
}
