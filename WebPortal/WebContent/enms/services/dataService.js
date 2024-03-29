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
		
		this.getDeviceGroupList = function(){
			return $http({
			    method: 'GET',
			    url: 'getDeviceGroupList',
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.getOverview = function(){
			return $http({
			    method: 'GET',
			    url: 'getOverview',
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.getActionCategoryList = function(){
			return $http({
			    method: 'POST',
			    url: 'getActionCategoryList',
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
		
		this.getMonitorList = function(){
			return $http({
			    method: 'GET',
			    url: 'getMonitorList',
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.getMonitorListbyDeviceId = function(deviceid){
			return $http({
			    method: 'POST',
			    url: 'getMonitorListbyDeviceId',
			    data: "deviceid=" + deviceid,
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
		
		this.deleteMonitor = function(monitor_id){
			var param = $.param({
				monitor_id:monitor_id
            });
			return $http({
			    method: 'POST',
			    url: 'deleteMonitor',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.addAction = function(action_id,action_name,category){
			var param = $.param({
				action_id:action_id,
				action_name:action_name,
				category:category
            });
			return $http({
			    method: 'POST',
			    url: 'addAction',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.deleteAction = function(action_id){
			var param = $.param({
				action_id:action_id
            });
			return $http({
			    method: 'POST',
			    url: 'deleteAction',
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
		
		this.getMonitorStatus = function(){
			return $http({
			    method: 'POST',
			    url: 'getMonitorStatus',
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.analyseMonitor = function(monitorid,actionid){
			return $http({
			    method: 'POST',
			    url: 'analyseMonitor',
			    data: "monitorid="+monitorid+"&actionid="+actionid,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.createDevice = function(device_name,ip,deviceGroup_id){
			var param = $.param({
				device_name:device_name,
				ip:ip,
				deviceGroup_id:deviceGroup_id
            });
			return $http({
			    method: 'POST',
			    url: 'createDevice',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.deleteDevice = function(device_id){
			var param = $.param({
				device_id:device_id
            });
			return $http({
			    method: 'POST',
			    url: 'deleteDevice',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.getDeviceDetails = function(device_id){
			var param = $.param({
				device_id:device_id
            });
			return $http({
			    method: 'POST',
			    url: 'getDeviceDetails',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
		this.updateDevice = function(device_id,device_name,ip,deviceGroup_id){
			var param = $.param({
				device_id:device_id,
				device_name:device_name,
				ip:ip,
				deviceGroup_id:deviceGroup_id
            });
			return $http({
			    method: 'POST',
			    url: 'updateDevice',
			    data: param,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
		};
		
	});
	
})();