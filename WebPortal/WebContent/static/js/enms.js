(function (){
	'use strict';

	//Define an angular module for our app
	var enmsApp = angular.module('enms', ['ngRoute']);
	 
	enmsApp.config(['$routeProvider',
	  function($routeProvider) {
	    $routeProvider.
	      when('/main', {
	        templateUrl: 'static/templates/main.html',
	        controller: 'homeController'
	    }).when('/test', {
	        templateUrl: 'static/templates/test.html',
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