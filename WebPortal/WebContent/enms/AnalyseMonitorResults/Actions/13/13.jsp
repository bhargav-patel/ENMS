<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String result = request.getParameter("data"); %>
<div id="hiddenDiv" hidden><%=result%></div>

<h2 style="text-decoration:underline">Process running on Remote Host</h2>
<table id="table1" class="table table-striped">
<thead>
<tr><th>#</th> <th>PID</th> <th>ProcessName</th></tr>
</thead>
<tbody>
</tbody>
</table>
</body>
</html>