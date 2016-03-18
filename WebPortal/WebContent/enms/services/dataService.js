(function (){
	'use strict';
	
	angular.module('enms.dataService', ['ngRoute'])
	
	.service('Data',function($http){
		this.baseUrl = '/';
		
		this.getRecentMonitorResults = function(howMany){
			return $http({
			    method: 'POST',
			    url: 'getRecentMonitorResults',
			    data: "howMany=" + howMany,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		}
		
	});
	
})();