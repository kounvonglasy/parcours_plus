$(document).ready(function() {
	$("input:checkbox").change(function(){
		 var group = ":checkbox[name='"+ $(this).attr("name") + "']";
		   if($(this).is(':checked')){
		     $(group).not($(this)).attr("checked",false);
		   }
		   group = ":checkbox[class='"+ $(this).attr("class") + "']";
		   if($(this).is(':checked')){
		     $(group).not($(this)).attr("checked",false);
		   }
		 });
});