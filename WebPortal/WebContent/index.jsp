<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>ENMS</title>
	<link rel="stylesheet" href="static/js/bower_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/js/bower_components/bootstrap-material-design/dist/css/ripples.min.css">
	<link rel="stylesheet" href="static/js/bower_components/bootstrap-material-design/dist/css/material-fullpalette.min.css">
	<link rel="stylesheet" href="static/css/custom.css">
</head>
<body>

<%@ include file="nav.jsp" %>

<div class="container-fluid">
	<%@ include file="static/templates/index.html" %>
</div>

<script src="static/js/bower_components/jquery/dist/jquery.min.js"></script>
<script src="static/js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/js/bower_components/bootstrap-material-design/dist/js/ripples.min.js"></script>
<script src="static/js/bower_components/bootstrap-material-design/dist/js/material.min.js"></script>
<script src="static/js/bower_components/angular/angular.min.js"></script>
<script src="static/js/bower_components/angular-route/angular-route.min.js"></script>
<script src="static/js/enms.js"></script>
<script>
  $.material.init();
</script>
</body>
</html>