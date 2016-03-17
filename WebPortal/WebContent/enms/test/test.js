(function (){
	'use strict';
	
	angular.module('enms.test', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/test', {
	    templateUrl: 'enms/test/test.html',
	    controller: 'testCtrl'
	  });
	}])
	
	.controller('testCtrl', ['$scope','Data',function($scope,Data) {
		
	}]);
	
})();