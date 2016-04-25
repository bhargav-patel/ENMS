	var result = $("#hiddenDiv").html();
	result = JSON.parse(result);
	
	var table = document.getElementById("table1");
	var serialNo = 0;
	for(var k in result){
		var row = table.insertRow(-1);
		
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		
		var data = result[k];

		
		if(k!="OS name"){
		cell1.innerHTML = "Drive "+data.Drive_Name;
		cell2.innerHTML = data.Description;
		}
		else{
			cell1.innerHTML = k;
			cell2.innerHTML = data;
		}
		serialNo++;
	}