function updatePrice(price){
	var noPerson = document.getElementById("noPerson").value;
	var totalPrice = noPerson * price;
	var total = document.getElementById("subtotal");
		total.textContent = totalPrice.toFixed(1);
}

function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}

function checkout(path){
	var fields = document.getElementsByTagName("input");
	var params = {};
	for(var i=0; i < fields.length; i++){
		params[fields[i].id] = fields[i].value;
	}
	post(path, params);
}

