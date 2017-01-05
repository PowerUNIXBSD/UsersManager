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
	
	$("#selectAllBtn").click(function() {
		$(".userDeleteCheckBox").each(function() {
			$(this).prop('checked', !$(this).prop('checked'));
		});
		
		if ($(this).text() == 'Select all') {
			$(this).text("Deselect all");
		} else {
			$(this).text("Select all");
		}

	});
	
});