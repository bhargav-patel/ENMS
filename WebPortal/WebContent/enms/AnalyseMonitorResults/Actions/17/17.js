	var result = $("#hiddenDiv").html();
	result = JSON.parse(result);
	console.log(result);
	var table = document.getElementById("table1");
	
	var row = table.insertRow(-1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	cell1.innerHTML = "IP Address";
	cell2.innerHTML = result.Address;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Description";
	cell2.innerHTML = result.Description;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Driver Name";
	cell2.innerHTML = result.Name;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "HostName";
	cell2.innerHTML = result.HostName;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "DefaultGateway";
	cell2.innerHTML = result.DefaultGateway;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Broadcast Address";
	cell2.innerHTML = result.broadcast;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "MTU";
	cell2.innerHTML = result.mtu;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "domain Name";
	cell2.innerHTML = result.domainName;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Network Hardware Address";
	cell2.innerHTML = result.netHWAddr;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Type";
	cell2.innerHTML = result.type;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Primary DNS";
	cell2.innerHTML = result.primaryDNS;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Secondary DNS";
	cell2.innerHTML = result.secondaryDNS;
	
	row = table.insertRow(-1);
	cell1 = row.insertCell(0);
	cell2 = row.insertCell(1);
	cell1.innerHTML = "Network masking Address";
	cell2.innerHTML = result.netMask;