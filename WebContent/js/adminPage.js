$(document).ready(function() {
	initializeCouponsTable();
	// opening the dialog of adding coupon form
	$('#add-coupon-button').on('click', function() {
		addCouponDialog.dialog('open');
	});

	// opening the dialog of updating coupon form
	$('#update-coupon-button').on('click', function() {
		var arrayOfIds = [];
		$('#admin-coupons-table > tbody > tr').each(function() {
			var idValue = $(this).find('td').eq(0).text();
			arrayOfIds.push({
				id : idValue
			});
		});
		$("#update-id").select2({
			width : '100%',
			placeholder : "בחר מס' מזהה לעדכון",
			data : {
				results : arrayOfIds,
				id : 'id',
				text : 'id'
			},
			formatSelection : function(object) {
				return object.id;
			},
			formatResult : function(object) {
				return object.id;
			}
		});
		updateCouponDialog.dialog('open');
	});

	// opening the dialog of deleting coupon form
	$('#delete-coupon-button').on('click', function() {
		var arrayOfIds = [];
		$('#admin-coupons-table > tbody > tr').each(function() {
			var idValue = $(this).find('td').eq(0).text();
			arrayOfIds.push({
				id : idValue
			});
		});
		$("#delete-id").select2({
			width : '100%',
			placeholder : "בחר מס' מזהה למחיקה",
			data : {
				results : arrayOfIds,
				id : 'id',
				text : 'id'
			},
			formatSelection : function(object) {
				return object.id;
			},
			formatResult : function(object) {
				return object.id;
			}
		});
		deleteCouponDialog.dialog('open');
	});

	// datePickers
	$('#add-expiration-date, #update-expiration-date').datepicker({
		dateFormat : "yy-mm-dd",
		isRTL : true
	});

	// select2 for categories selects (both in add-dialog and update-dialog)
	$('#add-category, #update-category').select2({
		placeholder : "בחר קטגוריה"
	});

	// event for submit button of add coupon dialog(triggered by addCoupon function)
	$('#add-coupon-dialog-form .hidden-add-submit').on('submit', function(event) {
		var valid = true;
		valid = valid && $('#add-id').is(':valid');
		valid = valid && $('#add-name').is(':valid');
		valid = valid && $('#add-description').is(':valid');
		valid = valid && ($('#add-category').val() !== '');
		valid = valid && $('#add-value').is(':valid');
		valid = valid && $('#add-percent-discount').is(':valid');
		var dateRegex = /^[0-9]{4}\-[0-1][0-9]\-[0-3][0-9]$/;
		valid = valid && dateRegex.test($('#add-expiration-date').val());
		valid = valid && $('#add-expiration-time').is(':valid');
		valid = valid && $('#add-pic-url').is(':valid');
		valid = valid && $('#add-longitude').is(':valid');
		valid = valid && $('#add-latitude').is(':valid');
		if (valid) {
			$.ajax({
				url : '/CouponsProject/Controller',
				type : 'POST',
				dataType : 'text',
				data : {
					action : 'add-coupon-action',
					id : $('#add-id').val(),
					name : $('#add-name').val(),
					description : $('#add-description').val(),
					category : categoriesObject[$('#add-category').val()],
					value : $('#add-value').val(),
					percentDiscount : $('#add-percent-discount').val(),
					expirationDate : $('#add-expiration-date').val() + ' ' + $('#add-expiration-time').val(),
					picURL : $('#add-pic-url').val(),
					longitude : $('#add-longitude').val(),
					latitude : $('#add-latitude').val()
				},
				success : function(messageFromServer) {
					if (messageFromServer === 'success') {
						console.log('SUCCESS');
						$('#result-message').text('הקופון התווסף בהצלחה').css('color', 'green').show().fadeOut(3000);
					} else {
						console.log('FAILURE');
						$('#result-message').text('כישלון בהוספת הקופון, אנא נסה שנית').css('color', 'red').show().fadeOut(3000);
					}
					addCouponDialog.animate({
						scrollTop : addCouponDialog.scrollTop() + addCouponDialog.height()
					}, 'fast');
					initializeCouponsTable();
				}
			});
		} else {
			$('#result-message').text('שדה אחד או יותר שגויים').css('color', 'orange').show().fadeOut(3000);
			addCouponDialog.animate({
				scrollTop : addCouponDialog.scrollTop() + addCouponDialog.height()
			}, 'fast');
		}
	});

	// change classes when changing description text areas(both in add-dialog and update-dialog)
	$('#add-description, #update-description').on('change', function() {
		var $this = $(this);
		if ($this.val() !== '') {
			$this.removeClass('invalid-red').addClass('valid-green');
		} else {
			$this.removeClass('valid-green').addClass('invalid-red');
		}
	});

	// change classes when changing expiration dates (both in add-dialog and update-dialog)
	$('#add-expiration-date, #update-expiration-date').on('change', function() {
		var $this = $(this);
		if ($this.val() !== '') {
			$this.removeClass('invalid-red').addClass('valid-green');
		} else {
			$this.removeClass('valid-green').addClass('invalid-red');
		}
	});

	// add coupon dialog definition
	var addCouponDialog = $('#add-coupon-dialog-form').dialog({
		autoOpen : false,
		height : 600,
		width : 590,
		resizable : false,
		modal : true,
		title : "הוספת קופון חדש",
		buttons : [ {
			text : 'הוסף',
			click : addCoupon
		} ],
		close : function() {
			$('#add-description').removeClass('valid-green').addClass('invalid-red');
			$('#add-expiration-date').removeClass('valid-green').addClass('invalid-red');
			$('#add-category').select2('data', null);
			$('#add-coupon-form')[0].reset();
		}
	});

	// when clicking on a specific id from the select2 of updating dialog,
	// loading the current coupon data of this id from the server
	$("#update-id").on('click', function() {
		$.ajax({
			//using of the RESTful webservice
			url : '/CouponsProject/services/CouponsService/coupons/'+$(this).select2('val'),
			type : 'GET',
			dataType : 'json',
			success : function(resultCoupon) {
				$('#update-name').val(resultCoupon.name);
				$('#update-description').val(resultCoupon.description);
				for ( var category in categoriesObject) {
					if (resultCoupon.category === categoriesObject[category]) {
						$('#update-category').select2('val', category);
						break;
					}
				}
				$('#update-value').val(resultCoupon.value);
				$('#update-percent-discount').val(resultCoupon.percentDiscount);
				$('#update-expiration-date').val(dateOrTimeFormatted(new Date(resultCoupon.expirationDate), 'dateOnly')).change();
				$('#update-expiration-time').val(dateOrTimeFormatted(new Date(resultCoupon.expirationDate), 'timeOnly'));
				$('#update-pic-url').val(resultCoupon.picURL);
				$('#update-longitude').val(resultCoupon.longitude);
				$('#update-latitude').val(resultCoupon.latitude);
				$('#update-dialog-button').prop('disabled', false).removeClass('ui-state-disabled');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	});

	// event for submit button of update coupon dialog
	$('#update-coupon-dialog-form .hidden-update-submit').on('submit', function(event) {
		var valid = true;
		valid = valid && $('#update-name').is(':valid');
		valid = valid && $('#update-description').is(':valid');
		valid = valid && ($('#update-category').val() !== '');
		valid = valid && $('#update-value').is(':valid');
		valid = valid && $('#update-percent-discount').is(':valid');
		var dateRegex = /^[0-9]{4}\-[0-1][0-9]\-[0-3][0-9]$/;
		valid = valid && dateRegex.test($('#update-expiration-date').val());
		valid = valid && $('#update-expiration-time').is(':valid');
		valid = valid && $('#update-pic-url').is(':valid');
		valid = valid && $('#update-longitude').is(':valid');
		valid = valid && $('#update-latitude').is(':valid');
		if (valid) {
			$.ajax({
				url : '/CouponsProject/Controller',
				type : 'POST',
				dataType : 'text',
				data : {
					action : 'update-coupon-action',
					id : $('#update-id').select2('val'),
					name : $('#update-name').val(),
					description : $('#update-description').val(),
					category : categoriesObject[$('#update-category').val()],
					value : $('#update-value').val(),
					percentDiscount : $('#update-percent-discount').val(),
					expirationDate : $('#update-expiration-date').val() + ' ' + $('#update-expiration-time').val(),
					picURL : $('#update-pic-url').val(),
					longitude : $('#update-longitude').val(),
					latitude : $('#update-latitude').val()
				},
				success : function(messageFromServer) {
					if (messageFromServer === 'success') {
						console.log('SUCCESS');
						$('#update-result-message').text('הקופון התעדכן בהצלחה').css('color', 'green').show().fadeOut(3500);
					} else {
						console.log('FAILURE');
						$('#update-result-message').text('כישלון בעדכון הקופון, אנא נסה שנית').css('color', 'red').show().fadeOut(3500);
					}
					updateCouponDialog.animate({
						scrollTop : updateCouponDialog.scrollTop() + updateCouponDialog.height()
					}, 'fast');
					initializeCouponsTable();
				}
			});
		} else {
			$('#update-result-message').text('שדה אחד או יותר שגויים').css('color', 'orange').show().fadeOut(3000);
			updateCouponDialog.animate({
				scrollTop : updateCouponDialog.scrollTop() + updateCouponDialog.height()
			}, 'fast');
		}
	});

	// update coupon dialog definition
	var updateCouponDialog = $('#update-coupon-dialog-form').dialog({
		autoOpen : false,
		height : 600,
		width : 640,
		resizable : false,
		modal : true,
		title : "עדכון קופון קיים",
		buttons : [ {
			text : 'עדכן',
			id : 'update-dialog-button',
			click : updateCoupon,
			disabled : true
		} ],
		close : function() {
			$('#update-description').removeClass('valid-green').addClass('invalid-red');
			$('#update-expiration-date').removeClass('valid-green').addClass('invalid-red');
			$('#update-id').select2('data', null);
			$('#update-category').select2('data', null);
			$('#update-dialog-button').prop('disabled', true).addClass('ui-state-disabled');
			$('#update-coupon-form')[0].reset();
		}
	});

	// when clicking on a specific id from the select2 of deleting dialog,
	// loading the
	// current coupon data of this id from the server
	$("#delete-id").on('click', function() {
		$('#delete-dialog-button').prop('disabled', false).removeClass('ui-state-disabled');
	});

	// event for submit button of delete coupon dialog
	$('#delete-coupon-dialog-form .hidden-delete-submit').on('submit', function(event) {
		$.ajax({
			url : '/CouponsProject/Controller',
			type : 'POST',
			dataType : 'text',
			data : {
				action : 'delete-coupon-action',
				id : $('#delete-id').select2('val')
			},
			success : function(messageFromServer) {
				if (messageFromServer === 'success') {
					console.log('SUCCESS');
					$('#delete-result-message').text('הקופון נמחק בהצלחה').css('color', 'green').show().fadeOut(3500);
				} else {
					console.log('FAILURE');
					$('#delete-result-message').text('כישלון במחיקת הקופון, קופון זה לא קיים').css('color', 'red').show().fadeOut(3500);
				}
				deleteCouponDialog.animate({
					scrollTop : deleteCouponDialog.scrollTop() + deleteCouponDialog.height()
				}, 'fast');
				initializeCouponsTable();
			}
		});
	});

	// delete coupon dialog definition
	var deleteCouponDialog = $('#delete-coupon-dialog-form').dialog({
		autoOpen : false,
		height : 230,
		width : 600,
		resizable : false,
		modal : true,
		title : "מחיקת קופון קיים",
		buttons : [ {
			text : 'מחיקה',
			id : 'delete-dialog-button',
			click : deleteCoupon,
			disabled : true
		} ],
		close : function() {
			$('#delete-id').select2('data', null);
			$('#delete-dialog-button').prop('disabled', true).addClass('ui-state-disabled');
			$('#delete-coupon-form')[0].reset();
		}
	});

	// mapping of select values and the corresponding categories
	var categoriesObject = {
		'1' : 'בריאות',
		'2' : 'מסעדות',
		'3' : 'פנאי',
		'4' : 'נופש',
		'5' : 'כרטיסים'
	};
});

// loading the coupons table from the server
function initializeCouponsTable() {
	$('#admin-coupons-table > tbody').empty();
	$.ajax({
		url : '/CouponsProject/services/CouponsService/coupons',
		type : 'GET',
		dataType : 'json',
		success : function(couponsFromServer) {
			var tableBody = $('#admin-coupons-table > tbody');
			$.each(couponsFromServer, function() {
				var tr = $('<tr></tr>');
				tr.append($('<td></td>').text(this.id));
				tr.append($('<td></td>').text(this.name));
				tr.append($('<td class="description-td"></td>').append($('<div class="description-div"></div>').text(this.description)));
				tr.append($('<td></td>').text(this.category));
				tr.append($('<td></td>').text(this.value));
				tr.append($('<td></td>').text(this.percentDiscount + '%'));
				tr.append($('<td></td>').text(dateOrTimeFormatted(new Date(this.expirationDate), 'dateAndTime')));
				tr.append($('<td></td>').text(this.picURL));
				tr.append($('<td></td>').text(this.longitude));
				tr.append($('<td></td>').text(this.latitude));
				tableBody.append(tr);
			});
			if (couponsFromServer.length > 0) {
				tableBody.show();
			} else {
				$('<p></p>').hide().insertAfter($('#admin-coupons-table')).text('לא קיימים נתונים בטבלה').css({
					'color' : 'red',
					'text-align' : 'center',
					'font-size' : '20px'
				}).slideDown(1500);
			}
		}
	});

	$('#logout-link').on('click', function() {
		$.ajax({
			url : '/CouponsProject/Controller',
			type : 'POST',
			data : {
				action : 'admin-logout-action'
			},
			success : function(resultFromServer) {

			}
		});
	});
}

function addCoupon() {
	$('#add-coupon-dialog-form .hidden-add-submit').trigger('submit');
}

function updateCoupon() {
	$('#update-coupon-dialog-form .hidden-update-submit').trigger('submit');
}

function deleteCoupon() {
	$('#delete-coupon-dialog-form .hidden-delete-submit').trigger('submit');
}

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
