(function (){
	'use strict';
	
	angular.module('enms.monitor.update', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/monitor/update/:monitorId', {
	    templateUrl: 'enms/monitor/update/update.html',
	    controller: 'updateMonitorCtrl'
	  });
	}])
	
	.controller('updateMonitorCtrl', ['$scope','$routeParams','Data',function($scope,$routeParams,Data) {
		
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
			
			Data.getMonitorDetails($routeParams.monitorId)
			.success(function(data){
				$scope.monitor_name = data.response.monitor_name;
				$scope.polling_duration = data.response.polling_duration;
				$scope.device = data.response.device_id;
				$scope.action = data.response.action_id;
			})
			.error(function(){
				console.log("ERROR");
			});
			
		}
		
		$scope.init();
				
		$scope.submit = function() {

			$scope.alerts = [];
			
			Data.updateMonitor($routeParams.monitorId,$scope.monitor_name,$scope.polling_duration,$scope.device,$scope.action)
			.success(function(data){
				if(data.response_code==1){
					$scope.alerts.push({
						type: 'success',
						msg: 'Monitor successfully Updated!',
						show: true
					})
				}
				else{
					$scope.alerts.push({
						type: 'warning',
						msg: 'Monitor not Updated. Try Again!',
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