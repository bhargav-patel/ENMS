(function (){
	'use strict';
	
	angular.module('enms.device.update', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/device/update/:deviceId', {
	    templateUrl: 'enms/device/update/update.html',
	    controller: 'updateDeviceCtrl'
	  });
	}])
	
	.controller('updateDeviceCtrl', ['$scope','$routeParams','Data',function($scope,$routeParams,Data) {
		
		$scope.init = function(){			
			Data.getDeviceGroupList()
			.success(function(data){
				$scope.deviceGroups = data;
			})
			.error(function(){
				console.log("ERROR");
			});
			
			Data.getDeviceDetails($routeParams.deviceId)
			.success(function(data){
				$scope.device_name = data.response.device_name;
				$scope.ip = data.response.ip;
				$scope.deviceGroup = data.response.deviceGroup_id;
			})
			.error(function(){
				console.log("ERROR");
			});
			
		}
		
		$scope.init();
				
		$scope.submit = function() {

			$scope.alerts = [];
			
			Data.updateDevice($routeParams.deviceId,$scope.device_name,$scope.ip,$scope.deviceGroup)
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