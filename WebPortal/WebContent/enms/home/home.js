(function (){
	'use strict';
	
	angular.module('enms.home', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/home', {
	    templateUrl: 'enms/home/home.html',
	    controller: 'homeCtrl'
	  });
	}])
	
	.controller('homeCtrl', ['$scope','Data','$interval',function($scope,Data,$interval) {
		$scope.getResentMonitorResults = function(howMany){
			Data.getRecentMonitorResults(howMany)
			.success(function(data){
				$scope.recentMonitorLogs = data;
				console.log(data);
			})
			.error(function(){
				console.log("ERROR : homeCtrl > getRecentMonitorResults");
			});
		};
		$interval(function(){
			$scope.getResentMonitorResults(5);
			$scope.getMonitorStatus();
		},3000);
		
		
		function drawChart(animation_steps){
			Data.getMonitorStatus()
			.success(function(data){
				$scope.count = data.count;
				$scope.total_count = data.total_count;
				
				var ctx = $("#monitor_Status").get(0).getContext("2d");
					data = [
						{
							value: data.total_count-data.count,
							color: "RED",
							highlight: '#ff1a8c',
							label: "Down"
						},
						{
							value: data.count,
							color: "green",
							highlight: "lightgreen",
							label: "Running"
						},
					];

					new Chart(ctx).Doughnut(data,{animateScale: true,animationSteps:animation_steps});
					console.log(data);
			})
			.error(function(){
				console.log('Error in fetching Monitor Status');
			});
		}
		
		$scope.getMonitorStatus = function(){drawChart(1);};
		
		$(document).ready(function(){
			$scope.getResentMonitorResults(5);
			drawChart(100);
		});
		
		$scope.analyseMonitor = function(monitorid,actionid){
			$scope.WidgetHeading= "CPU Usage, MonitorID:3";
			console.log(monitorid,actionid);
			Data.analyseMonitor(monitorid,actionid)
			.success(function(data){
				$scope.monitorResult = data;
				fakeChart(14);
			})
			.error(function(){
				console.log("ERROR");
			});
			
		};
		
		function fakeChart(actionid){
			console.log("Drawing canvas of actionid= "+actionid);
			var dataChart =$scope.monitorResult.resultData; 
			var ctx = $("#fake").get(0).getContext("2d");
			var myChart;
			console.log(actionid);
			if(actionid==14){				
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
				ctx.clearRect(0, 0, $("#fake").get(0).width, $("#fake").get(0).height);
			}
		}
		
	}]);
	
})();