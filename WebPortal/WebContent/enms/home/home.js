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
							highlight: "red",
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
	}]);
	
})();