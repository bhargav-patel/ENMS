(function (){
	'use strict';

	//Define an angular module for our app
	var enmsApp = angular.module('enms', ['ngRoute','enms.home','enms.test','enms.dataService']);
	 
	enmsApp.config(['$routeProvider',
	  function($routeProvider) {
	    $routeProvider.
	    	otherwise({
	        redirectTo: '/home'
	      });
	}]);

})();