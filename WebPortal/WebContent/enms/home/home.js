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
		}
		$interval(function(){
			$scope.getResentMonitorResults(5);
		},3000);
	}]);
	
})();