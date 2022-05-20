function collapse(button) {
	let content = button.nextElementSibling;

	if (content.style.display === "block") {
		content.style.display = "none";
		button.textContent = "[+]";
	} else {
		content.style.display = "block";
		button.textContent = "[-]";
	}
}