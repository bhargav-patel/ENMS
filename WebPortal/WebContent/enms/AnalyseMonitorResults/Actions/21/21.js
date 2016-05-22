var result = $("#hiddenDiv").html();
	result = JSON.parse(result);
	console.log(result);
	document.getElementById("div21").innerHTML=result.Hardware;