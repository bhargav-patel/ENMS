(function (){
	'use strict';
	
	angular.module('enms.action.add', ['ngRoute'])
	
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/action/addActionCategory', {
	    templateUrl: 'enms/action/add/addActionCategory.html',
	    controller: 'addActionCategoryCtrl'
	  });
	}])
	
	.controller('addActionCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getActionCategoryList()
			.success(function(data){
				$scope.actionCategories = data;
				console.log(data);
			})
			.error(function(){
				console.log("ERROR");
			});
		};
		
		$scope.init();
				
		$scope.submit = function() {

			$scope.alerts = [];
			
			Data.addActionCategory($scope.actionCategory_id,$scope.actionCategory_name)
			.success(function(data){
				if(data.response_code==1){
					$scope.alerts.push({
						type: 'success',
						msg: 'ActionCategory successfully Added!',
						show: true
					});
				}
				else{
					$scope.alerts.push({
						type: 'warning',
						msg: 'ActionCategory not Created. Try Again!',
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