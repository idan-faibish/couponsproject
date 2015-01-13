$(document).ready(function(){
	//disable the default behavior of concatenating the character '#' when clicking on element with 'thumbnail' class
	$('.thumbnail').on('click',function(event){
		event.preventDefault();
	});
	
	//refresh the page every 1 minute
	setInterval(function() {
		window.location.reload();
	}, 1000*60);
});
