(function (){
	'use strict';
	
	angular.module('enms.home', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/home', {
	    templateUrl: 'enms/home/home.html',
	    controller: 'homeCtrl'
	  });
	}])
	
	.controller('homeCtrl', ['$scope','Data',function($scope,Data) {
		$scope.data = Data.data;
	}]);
	
})();