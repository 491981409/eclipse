$(function(){
	$.ajaxSetup({
		global: true,
		type: "POST",
		beforeSend:function(xhr){
			return true;
		}
	});
});