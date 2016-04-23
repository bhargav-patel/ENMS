(function (){
	'use strict';
	
	angular.module('enms.AnalyseMonitorResults', [])
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/AnalyseMonitorResults', {
	    templateUrl: 'enms/AnalyseMonitorResults/AnalyseMonitorResult.html',
	    controller: 'MonitorResultCtrl'
	  });
	}])
	
	.controller('MonitorResultCtrl', ['$scope','Data',function($scope,Data) {
		
		$scope.init = function(){
			Data.getDeviceList()
			.success(function(data){
				$scope.devices = data;
			})
			.error(function(){
				console.log("ERROR");
			});	
		};
		
		$scope.getMonitorbyDeviceId = function(deviceid){
			Data.getMonitorListbyDeviceId(deviceid)
			.success(function(data){
				$scope.monitors = data;
			})
			.error(function(){
				console.log("ERROR");
			});	
		};
		
		$scope.analyseMonitor = function(monitorid,actionid){
			//switch Modal
			$('#actionModal').modal('hide').on('hidden.bs.modal', function () {
                $('#monitorModal').modal('show');
                $(this).off('hidden.bs.modal'); 
            });
			
			function loadJS(file) {
			    var jsElm = document.createElement("script");
			    jsElm.type = "application/javascript";
			    jsElm.src = file;
			    document.body.appendChild(jsElm);
			}
			function loadCSS(file) {
			    var jsElm = document.createElement("style");
			    jsElm.type = "text/css";
			    jsElm.ref = "stylesheet";
			    jsElm.href = file;
			    document.body.appendChild(jsElm);
			}
			
			function callJSP(data,actionid){
				var actionfile ="enms/AnalyseMonitorResults/Actions/"+actionid+"/"+actionid;
				
				var xhttp = new XMLHttpRequest();
				  xhttp.onreadystatechange = function() {
				    if (xhttp.readyState == 4 && xhttp.status == 200) {
				      document.getElementById("demo").innerHTML = xhttp.responseText;
				      loadJS(actionfile+".js");
				      loadCSS(actionfile+".css");
				    }
				  };
				  xhttp.open("GET", actionfile+".jsp?data="+data, true);
				  xhttp.send();
			}
			
			
			
			Data.analyseMonitor(monitorid,actionid)
			.success(function(data){
				$scope.monitorResult = data;
				callJSP(JSON.stringify($scope.monitorResult.resultData),actionid);
			})
			.error(function(){
				console.log("ERROR");
			});
			
		};
		
		$scope.init();
	}]);	
})();