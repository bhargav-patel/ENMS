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
<div style="text-decoration:underline;font-size:24px;color:#23d">Hardware chip Details</div>
<div class="jumbotron" style="background-color:BLUE;color:WHITE" id="div21"></div>
</body>
</html>