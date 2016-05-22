(function (){
	'use strict';

	//Define an angular module for our app
	var enmsApp = angular.module('enms', ['ngRoute','enms.home','enms.test','enms.dataService','enms.monitor','enms.device','enms.AnalyseMonitorResults','enms.overview','enms.action']);
	 
	enmsApp.config(['$routeProvider',
	  function($routeProvider) {
	    $routeProvider.
	    	otherwise({
	        redirectTo: '/home'
	      });
	}]);

})();

$('#sidenavbar').hide();
$( "#showsidenavbar" ).click(function() {
	  $('#sidenavbar').toggle("slow");
	});