<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileWriter"%>
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
<div class="jumbotron" style="background-color:#630;color:#ffb366;">
	<form action="http://localhost:8080/WebPortal/enms/AnalyseMonitorResults/Actions/15/15_kill.jsp">
	<span style="font-family:'Comic Sans MS', cursive, sans-serif;">Enter Name of Process : <input type="text" name="kill_btn" value="" class="form-control" style="color:WHITE;"></span>
	<input type="submit" class="btn btn-default" style="background-color:#ffb366;">
	</form>
</div>
<%String Pid = request.getParameter("kill_btn"); %>
<%
File file = new File("C:\\Users\\Parth-OZ\\Desktop\\Project\\ENMS\\PollingScheduler\\enmsActions\\json\\15.json");
FileReader read = new FileReader(file);
String s = new String("");
int c=0;
while( (c=read.read())!=-1){
	s += (char)c;
}

%>	


</body>
</html>