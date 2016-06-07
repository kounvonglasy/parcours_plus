function ajax_loader(x) {
	req = $
			.ajax({
				type : "GET",
				url : "SelectionResponsableParcours?name=" + x,
				datatype : "json",
				success : function(data) {
					$('#scrolly').ScrollTo();
					var result = JSON.parse(JSON.stringify(data));
					$("#libelle").html(result.libelle);
					$("#type_responsable").html(result.type_responsable);
					$("#email").html(result.email);
					$("#description").html(result.description);
					if (result.img_existante == true) {
						$("#image").attr('src', 'DisplayBlob?id=' + result.img);
					} else {
						$("#image").attr('src','Images/olive.PNG');
					}
					$("#envoi_message")
							.attr(
									"href",
									"redac_mess.jsp?email_destinataire="
											+ result.email);
				}
			});
}