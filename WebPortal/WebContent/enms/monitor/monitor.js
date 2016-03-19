(function (){
	'use strict';
	
	angular.module('enms.monitor', ['ngRoute','enms.monitor.create','enms.monitor.update'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/monitor', {
	    templateUrl: 'enms/monitor/monitor.html',
	    controller: 'monitorCtrl'
	  });
	}])
	
	.controller('monitorCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getMonitorList()
			.success(function(data){
				$scope.monitors = data;
			})
			.error(function(){
				console.log("ERROR");
			});	
		}
		
		$scope.init();
		
		$scope.deleteMonitor = function(monitor_id){
			Data.deleteMonitor(monitor_id)
			.success(function(data){
				$scope.init();
			})
			.error(function(){
				console.log("ERROR");
			});
		}
	}]);
	
})();