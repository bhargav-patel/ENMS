<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String result = request.getParameter("data"); %>
<div id="hiddenDiv" hidden><%=result%></div>
<h2 style="text-decoration:underline">Disk Details</h2>
<table id="table1" class="table table-striped">
<thead>
<tr><th>#</th> <th>DiskDetails</th></tr>
</thead>
<tbody>
</tbody>
</table>

</body>
</html>