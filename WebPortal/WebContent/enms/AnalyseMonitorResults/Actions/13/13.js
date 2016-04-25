	var result = $("#hiddenDiv").html();
	result = JSON.parse(result);
	
	var table = document.getElementById("table1");
	var serialNo = 0;
	for(var k in result){
		var row = table.insertRow(-1);
		
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		
		var data = result[k];
		cell0.innerHTML = serialNo;
		cell1.innerHTML = data.Pid;
		cell2.innerHTML = data.ProcessName;
		
		serialNo++;
	}