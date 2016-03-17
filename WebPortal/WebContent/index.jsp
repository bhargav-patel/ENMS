<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>ENMS</title>
	<link rel="stylesheet" href="enms/lib/bower_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="enms/lib/bower_components/bootstrap-material-design/dist/css/ripples.min.css">
	<link rel="stylesheet" href="enms/lib/bower_components/bootstrap-material-design/dist/css/material-fullpalette.min.css">
	<link rel="stylesheet" href="enms/css/custom.css">
</head>
<body>

<%@ include file="nav.jsp" %>

<div class="container-fluid">
	<%@ include file="enms/enms.html" %>
</div>

<script src="enms/lib/bower_components/jquery/dist/jquery.min.js"></script>
<script src="enms/lib/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="enms/lib/bower_components/bootstrap-material-design/dist/js/ripples.min.js"></script>
<script src="enms/lib/bower_components/bootstrap-material-design/dist/js/material.min.js"></script>
<script src="enms/lib/bower_components/angular/angular.min.js"></script>
<script src="enms/lib/bower_components/angular-route/angular-route.min.js"></script>
<script src="enms/enms.js"></script>
<script src="enms/home/home.js"></script>
<script src="enms/test/test.js"></script>
<script src="enms/services/dataService.js"></script>
<script>
  $.material.init();
</script>
</body>
</html>