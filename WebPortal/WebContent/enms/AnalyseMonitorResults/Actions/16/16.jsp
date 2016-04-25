<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
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
<%String result = request.getParameter("data"); 
JSONObject jsonobj = new JSONObject();
JSONParser parser = new JSONParser();
jsonobj = (JSONObject)parser.parse(result);
String filepath = (String)jsonobj.get("image");
%>
<div id="hiddenDiv" hidden><%=result%></div>
<h3>Screenshot:</h3><br>
<img src=<%=filepath+"?time="+System.currentTimeMillis()%> width="560"></img>
</body>
</html>