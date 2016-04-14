(function (){
	'use strict';
	
	angular.module('enms.AnalyseMonitorResults', [])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/AnalyseMonitorResults', {
	    templateUrl: 'enms/AnalyseMonitorResults/AnalyseMonitorResult.html',
	    controller: 'MonitorResultCtrl'
	  });
	}])
	
	.controller('MonitorResultCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getDeviceList()
			.success(function(data){
				$scope.devices = data;
			})
			.error(function(){
				console.log("ERROR");
			});	
		};
		
		$scope.getMonitorbyDeviceId = function(deviceid){
			Data.getMonitorListbyDeviceId(deviceid)
			.success(function(data){
				$scope.monitors = data;
			})
			.error(function(){
				console.log("ERROR");
			});	
		};
		
		$scope.analyseMonitor = function(monitorid,actionid){
			//switch Modal
			$('#actionModal').modal('hide').on('hidden.bs.modal', function () {
                $('#monitorModal').modal('show');
                $(this).off('hidden.bs.modal'); 
            });
			
			Data.analyseMonitor(monitorid,actionid)
			.success(function(data){
				$scope.monitorResult = data;
					drawChart(actionid);//calls drawChart function which drawsChart
				if(actionid==12){
					while(true){
						var str = data.resultData;
						$scope.monitorResult.resultData = str.Description1 + " : "+str.Drive_Name1;
						break;
					}
				}
				console.log($scope.monitorResult.resultData);
			})
			.error(function(){
				console.log("ERROR");
			});
			
		};
		
		function drawChart(actionid){
			console.log("Drawing canvas of actionid= "+actionid);
			var dataChart =$scope.monitorResult.resultData; 
			var ctx = $("#analyse_Monitor").get(0).getContext("2d");
			var myChart;
			console.log(actionid);
			if(actionid==14){
				$scope.Analysis_Heading = "Ram Usage Statistics";
				$scope.monitorResult.resultData ="Graph of RAM Usage(in MBs) with respect time";
				
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
			}
			else{
				ctx.clearRect(0, 0, $("#analyse_Monitor").get(0).width, $("#analyse_Monitor").get(0).height);
			}
			
			
		}
		$scope.init();
	}]);
	
})();