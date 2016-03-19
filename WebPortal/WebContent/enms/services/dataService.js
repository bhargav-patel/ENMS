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
		};
		
		this.getActionList = function(){
			return $http({
			    method: 'GET',
			    url: 'getActionList',
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.getDeviceList = function(){
			return $http({
			    method: 'GET',
			    url: 'getDeviceList',
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.createMonitor = function(monitor_name,polling_duration,device,action){
			var param = $.param({
				monitor_name:monitor_name,
				polling_duration:polling_duration,
				device:device,
				action:action
            });
			return $http({
			    method: 'POST',
			    url: 'createMonitor',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.getMonitorDetails = function(monitor_id){
			var param = $.param({
				monitor_id:monitor_id
            });
			return $http({
			    method: 'POST',
			    url: 'getMonitorDetails',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.updateMonitor = function(monitor_id,monitor_name,polling_duration,device,action){
			var param = $.param({
				monitor_id:monitor_id,
				monitor_name:monitor_name,
				polling_duration:polling_duration,
				device:device,
				action:action
            });
			return $http({
			    method: 'POST',
			    url: 'updateMonitor',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
	});
	
})();