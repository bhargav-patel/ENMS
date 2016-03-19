(function (){
	'use strict';
	
	angular.module('enms.device', ['ngRoute','enms.device.create','enms.device.update'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/device', {
	    templateUrl: 'enms/device/device.html',
	    controller: 'deviceCtrl'
	  });
	}])
	
	.controller('deviceCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getDeviceList()
			.success(function(data){
				$scope.devices = data;
			})
			.error(function(){
				console.log("ERROR");
			});	
		}
		
		$scope.init();
		
		$scope.deleteDevice = function(device_id){
			Data.deleteDevice(device_id)
			.success(function(data){
				$scope.init();
			})
			.error(function(){
				console.log("ERROR");
			});
		}
	}]);
	
})();