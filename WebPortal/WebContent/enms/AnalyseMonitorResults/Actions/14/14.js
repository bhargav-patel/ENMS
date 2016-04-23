	var result = $("#hiddenDiv").html();
	console.log("DrawChart:::"+result);
	var resultData = JSON.parse(result);
	var dataChart = resultData; 
	var ctx = $("#analyse_Monitor").get(0).getContext("2d");
	var myChart;
		
		var values = {
			    labels: ["0","1", "2","3","4","5","6","7","8","9"],
			    datasets: [
			        {
			        	label: "Usage",
			            fillColor: "rgba(151,187,205,0.2)",
			            strokeColor: "rgba(151,187,205,1)",
			            pointColor: "rgba(151,187,205,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(151,187,205,1)",
			            data: [dataChart.Usage0.Used/1048576,dataChart.Usage1.Used/1048576,dataChart.Usage2.Used/1048576,
			                   dataChart.Usage3.Used/1048576,dataChart.Usage4.Used/1048576,dataChart.Usage5.Used/1048576,
			                   dataChart.Usage6.Used/1048576,dataChart.Usage7.Used/1048576,dataChart.Usage8.Used/1048576,
			                   dataChart.Usage9.Used/1048576]
			        }
			    ]
			};
		myChart = new Chart(ctx).Line(values,{bezierCurve : true});
		myChart.update();