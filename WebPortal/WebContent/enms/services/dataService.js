(function (){
	'use strict';
	
	angular.module('enms.dataService', ['ngRoute'])
	
	.service('Data',function($http){
		this.data =  'Hi';
	});
	
})();