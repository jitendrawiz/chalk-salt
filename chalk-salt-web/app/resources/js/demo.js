jQuery(function($){

	$('iframe').css('height', $(window).height() - 45);
	$('iframe').css('width', $(window).width());

	$(window).on('resize', function() {
		$('iframe').css('height', $(window).height() - 45);
	});

	//Resize
	$('#device-laptop').on('click',function(event){
		event.preventDefault();
		$('iframe').css({
			'width':$(window).width()
		});	
	});
	$('#device-tablet').on('click',function(event){
		event.preventDefault();
		$('iframe').css({
			'width': 768
		});	
	});

	$('#device-mobile').on('click',function(event){
		event.preventDefault();
		$('iframe').css({
			'width': 480
		});	
	});
});