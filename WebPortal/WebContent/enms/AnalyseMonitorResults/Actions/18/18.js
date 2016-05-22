	var result = $("#hiddenDiv").html();
	console.log("DrawChart:::"+result);
	var resultData = JSON.parse(result);
	var dataChart = resultData; 
	var ctx = $("#analyse_Monitor2").get(0).getContext("2d");
	var myChart;
		
		var values = {
			    labels: ["0","1", "2","3","4","5","6","7","8","9"],
			    datasets: [
			        {
			        	label: "Tx",
			            fillColor: "rgba(151,187,205,0.2)",
			            strokeColor: "rgba(151,187,205,1)",
			            pointColor: "rgba(151,187,205,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(151,187,205,1)",
			            data: [dataChart.Data0.tx,dataChart.Data1.tx,dataChart.Data2.tx,
			                   dataChart.Data3.tx,dataChart.Data4.tx,dataChart.Data5.tx,
			                   dataChart.Data6.tx,dataChart.Data7.tx,dataChart.Data8.tx,
			                   dataChart.Data9.tx]
			        },
			        {
			        	label: "Rx",
			            fillColor: "rgba(151,187,205,0.2)",
			            strokeColor: "rgba(151,187,205,1)",
			            pointColor: "rgba(151,187,205,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(151,187,205,1)",
			            data: [dataChart.Data0.rx,dataChart.Data1.rx,dataChart.Data2.rx,
			                   dataChart.Data3.rx,dataChart.Data4.rx,dataChart.Data5.rx,
			                   dataChart.Data6.rx,dataChart.Data7.rx,dataChart.Data8.rx,
			                   dataChart.Data9.rx]
			        }
			    ]
			};
		myChart = new Chart(ctx).Line(values,{bezierCurve : true});
		myChart.update();