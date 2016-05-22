(function (){
	'use strict';
	
	angular.module('enms.action.add', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/action/add', {
	    templateUrl: 'enms/action/add/add.html',
	    controller: 'addActionCtrl'
	  });
	}])
	
	.controller('addActionCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getActionCategoryList()
			.success(function(data){
				$scope.categories = data;
				console.log(data);
			})
			.error(function(){
				console.log("ERROR");
			});
		};
		
		$scope.init();
				
		$scope.submit = function() {

			$scope.alerts = [];
			
			Data.addAction($scope.action_id,$scope.action_name,$scope.category)
			.success(function(data){
				if(data.response_code==1){
					$scope.alerts.push({
						type: 'success',
						msg: 'Action successfully Added!',
						show: true
					});
				}
				else{
					$scope.alerts.push({
						type: 'warning',
						msg: 'Action not Created. Try Again!',
						show: true
					});
				}
			})
			.error(function(){
				console.log("ERROR");
			});
	      };
	}]);
	
})();