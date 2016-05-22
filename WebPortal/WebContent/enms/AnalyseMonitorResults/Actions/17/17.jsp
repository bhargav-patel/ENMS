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
<table id="table1" class="table table-striped table-hover" style="background-color:#122929;color:#A9D8F7;">
<thead>
<tr><th style="text-decoration:underline;font-size:2em;">#</th> <th style="text-decoration:underline;font-size:2em;">Details</th></tr>
</thead>
<tbody >
</tbody>
</table>
</body>
</html>