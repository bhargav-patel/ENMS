<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ENMS</title>
	<link rel="stylesheet" href="../enms/lib/bower_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="../enms/lib/bower_components/bootstrap-material-design/dist/css/ripples.min.css">
	<link rel="stylesheet" href="../enms/lib/bower_components/bootstrap-material-design/dist/css/material-fullpalette.min.css">
	<link rel="stylesheet" href="../enms/css/custom.css">
</head>


<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
	<div class="navbar-header">
	  <a class="navbar-brand" id="showsidenavbar">Enterprise Network Monitoring System</a>
	</div>
  </div>
</nav>

<div class="jumbotron">
<form class="form-horizontal" action="authentication">
  <fieldset>
    <legend>Sign in</legend>
    <div class="form-group">
      <label for="inputPassword" class="col-md-2 control-label">Username</label>
      <div class="col-md-10">
        <input type="text" class="form-control" id="inputEmail" name="username" placeholder="Username">
      </div>
    </div>
    
    <div class="form-group">
      <label for="inputPassword" class="col-md-2 control-label">Password</label>

      <div class="col-md-10">
        <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
      </div>
    </div>
    
   

    <div class="form-group">
      <div class="col-md-10 col-md-offset-2">
        <button type="button" class="btn btn-default">Cancel</button>
        <button type="submit" class="btn btn-primary">Submit</button>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-10 col-md-offset-2">
         <span class="glyphicon glyphicon-lock"></span><a href="#">  Forgot password</a>
      </div>
    </div>
    
    <div class="form-group">
      <div class="col-md-10 col-md-offset-2">
         <span class="glyphicon glyphicon-user"></span><a href="#">  Create User Account</a>
      </div>
    </div>
  </fieldset>
</form>
</div>
<script>
  $.material.init();
</script>
</body>
</html>