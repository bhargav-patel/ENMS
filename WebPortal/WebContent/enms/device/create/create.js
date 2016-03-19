(function (){
	'use strict';
	
	angular.module('enms.device.create', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/device/create', {
	    templateUrl: 'enms/device/create/create.html',
	    controller: 'createDeviceCtrl'
	  });
	}])
	
	.controller('createDeviceCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getDeviceGroupList()
			.success(function(data){
				$scope.deviceGroups = data;
			})
			.error(function(){
				console.log("ERROR");
			});			
		}
		
		$scope.init();
				
		$scope.submit = function() {

			$scope.alerts = [];
			
			Data.createDevice($scope.device_name,$scope.ip,$scope.deviceGroup)
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