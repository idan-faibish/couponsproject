$(document).ready(function() {
	
	$('#guest-page-anchor').on('click',function(event) {
		event.preventDefault();
		$('#guest-page-button').click();
	});
	
	$('#admin-login-page-anchor').on('click',function(event){
		event.preventDefault();
		$('#admin-login-page-button').click();
	});
	

});