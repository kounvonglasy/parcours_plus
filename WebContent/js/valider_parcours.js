function radio(clicked) {
	var form = clicked.form;
	var checkboxes = form.elements[clicked.name];

	if (!clicked.checked || !checkboxes.length) {
		clicked.parentNode.parentNode.className = "";
		return false;
	}

	for (i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] != clicked) {
			checkboxes[i].checked = false;
			checkboxes[i].parentNode.parentNode.className = "";
		}
	}

	// highlight the row    
	clicked.parentNode.parentNode.className = "over";
}