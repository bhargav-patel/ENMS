(function (){
	'use strict';
	
	angular.module('enms.action', ['ngRoute','enms.action.add'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/action', {
	    templateUrl: 'enms/action/action.html',
	    controller: 'actionCtrl'
	  });
	}])
	
	.controller('actionCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getActionList()
			.success(function(data){
				$scope.actions = data;
				console.log("actionList="+data);
			})
			.error(function(){
				console.log("ERROR");
			});	
		};
		
		$scope.init();
		
		$scope.deleteAction = function(action_id){
			Data.deleteAction(action_id)
			.success(function(data){
				$scope.init();
			})
			.error(function(){
				console.log("ERROR");
			});
		};
	}]);
	
})();