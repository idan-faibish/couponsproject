$(document).ready(function(){
	$('.thumbnail').on('click',function(event){
		event.preventDefault();
	});
	setInterval(function() {
		window.location.reload();
	}, 1000*60);
});
