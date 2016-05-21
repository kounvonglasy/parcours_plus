function ajax_loader(x) {
	req = $.ajax({
		type : "GET",
		url : "SelectionResponsableParcours?name=" + x,
		datatype : "json",
		success : function(data) {
			var result = JSON.parse(JSON.stringify(data));
			$("#libelle").html(result.libelle);
			$("#type_responsable").html(result.type_responsable);
			$("#email").html(result.email);
			$("#image").attr('src','DisplayBlob?id='+result.img);
			
		}
	});
}