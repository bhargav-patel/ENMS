(function (){
	'use strict';
	
	angular.module('enms.monitor', ['ngRoute','enms.monitor.create'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/monitor', {
	    templateUrl: 'enms/monitor/monitor.html',
	    controller: 'monitorCtrl'
	  });
	}])
	
	.controller('monitorCtrl', ['$scope','Data',function($scope,Data) {
		
	}]);
	
})();