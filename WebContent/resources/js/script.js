"DateBirth"
$(function() {
	$(".button-collapse").sideNav();
	$('.datepicker').pickadate({
		selectMonths : true,
		selectYears : 15,
		format : 'yyyy-mm-dd',
		formatSubmit : 'yyyy-mm-dd',
		max : new Date()
	});
	
});