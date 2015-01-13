$(document).ready(function() {
	
	//forwards the user to the guest page
	$('#guest-page-anchor').on('click',function(event) {
		event.preventDefault();
		$('#guest-page-button').click();
	});
	
	//forwards the user to the admin login page
	$('#admin-login-page-anchor').on('click',function(event){
		event.preventDefault();
		$('#admin-login-page-button').click();
	});
	

});