(function (){
	'use strict';
	
	angular.module('enms.dataService', ['ngRoute'])
	
	.service('Data',function($http){
		this.baseUrl = '/';
		
		this.getResentLogs = function(){
			return $http.post('/resentLogs.json');
		}
		
	});
	
})();