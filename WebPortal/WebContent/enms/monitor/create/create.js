(function (){
	'use strict';
	
	angular.module('enms.monitor.create', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/monitor/create', {
	    templateUrl: 'enms/monitor/create/create.html',
	    controller: 'createMonitorCtrl'
	  });
	}])
	
	.controller('createMonitorCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getActionList()
			.success(function(data){
				$scope.actions = data;
			})
			.error(function(){
				console.log("ERROR");
			});
			
			Data.getDeviceList()
			.success(function(data){
				$scope.devices = data;
			})
			.error(function(){
				console.log("ERROR");
			});
			
		}
		
		$scope.init();
				
		$scope.submit = function() {

			$scope.alerts = [];
			
			Data.createMonitor($scope.monitor_name,$scope.polling_duration,$scope.device,$scope.action)
			.success(function(data){
				if(data.response_code==1){
					$scope.alerts.push({
						type: 'success',
						msg: 'Monitor successfully Created!',
						show: true
					})
				}
				else{
					$scope.alerts.push({
						type: 'warning',
						msg: 'Monitor not Created. Try Again!',
						show: true
					})
				}
			})
			.error(function(){
				console.log("ERROR");
			});
	      };
	}]);
	
})();