$(document).ready(function() {
	$('#submit-button').on('click', function(event) {
		event.preventDefault();
		$.ajax({
			url : '/CouponsProject/Controller',
			type : 'POST',
			dataType : 'text',
			data : {
				action : 'admin-login-action',
				userName : $('#username-input').val(),
				password : $('#password-input').val()
			},
			beforeSend : function() {
				$('#submit-button').attr('disabled', true);
				$('.loading-gif').show();

			},
			success : function(resultText) {
				if (resultText === 'true') {
					$('.loading-gif').hide();
					$('#result-from-server').removeClass('fail-highlight')
										.addClass('success-highlight')
										.text('ההתחברות הסתיימה בהצלחה, מעביר אותך...')
										.show()
										.fadeOut(3000);
					window.setTimeout(function() {
						$('#hidden-submit').click();
					}, 2000);


				} else {
					$('.loading-gif').hide();
					$('#submit-button').attr('disabled', false);
					$('#result-from-server').removeClass('success-highlight')
										.addClass('fail-highlight')
										.text("שם משתמש או סיסמא שגויים...")
										.show()
										.fadeOut(2000);
				}
			}
		});
	});
});
