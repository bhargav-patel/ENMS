(function (){
	'use strict';
	
	angular.module('enms.overview', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/overview', {
	    templateUrl: 'enms/overview/overview.html',
	    controller: 'overviewCtrl'
	  });
	}])
	
	.controller('overviewCtrl', ['$scope','Data',function($scope,Data) {
		$scope.getoverview = function(){
			Data.getOverview()
			.success(function(data){
				$scope.monitor_array = data;
				console.log(data);
			})
			.error(function(){
				console.log("ERROR : overviewCtrl-> getOverview");
			});
		};
		$(document).ready(function(){
			$scope.getoverview();
			repeat();
		});
		function repeat(){
			setInterval(function(){$scope.getoverview();},3000);
		}
	}]);
	
})();