function loadXMLDoc(url){
	xmlhttp = null;
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject){
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(xmlhttp != null){
		xmlhttp.onreadystatechange = state_Change;
		xmlhttp.open("GET", url, true);
		xmlhttp.send(null);
	} else {
		alert("Your browser does not support XMLHTTP");
	}
}
function state_Change(){
	if(xmlhttp.readyState == 4){
		if (xmlhttp.status == 200){
			document.getElementById('T1').innerHTML = xmlhttp.responseText;
			var r = $('<input type="button" value="Hello"/>')
			$("body").append(r);
		} else {
			alert("Problem retrieving data:" + xmlhttp.statusText);
		}
	}
}

function sendRequest(){
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	} else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var content = documents.getElementById("text_container");
	var selected = documents.getElementById("nbest");
	
	alert(content + selected);
}