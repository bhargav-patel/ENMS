(function (){
	'use strict';

	//Define an angular module for our app
	var enmsApp = angular.module('enms', ['ngRoute']);
	 
	enmsApp.config(['$routeProvider',
	  function($routeProvider) {
	    $routeProvider.
	      when('/main', {
	        templateUrl: 'enms/main/main.html',
	        controller: 'homeController'
	    }).when('/test', {
	        templateUrl: 'enms/test/test.html',
	        controller: 'testController'
	    }).
	      otherwise({
	        redirectTo: '/main'
	      });
	}]);

	enmsApp.controller('homeController', ['$scope',function($scope) {
		
	}]);
	
	enmsApp.controller('testController', ['$scope',function($scope) {
			
	}]);

})();