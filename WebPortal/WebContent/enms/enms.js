(function (){
	'use strict';

	//Define an angular module for our app
	var enmsApp = angular.module('enms', ['ngRoute','enms.home']);
	 
	enmsApp.config(['$routeProvider',
	  function($routeProvider) {
	    $routeProvider.
	    	when('/test', {
	        templateUrl: 'enms/test/test.html',
	        controller: 'testController'
	    }).
	      otherwise({
	        redirectTo: '/home'
	      });
	}]);

})();