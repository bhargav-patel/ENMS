<%@page import="java.io.FileWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE `userdefinedaction` SET `fired`='1' WHERE `action_id`='15'");
			response.sendRedirect("http://localhost:8080/WebPortal/#/AnalyseMonitorResults");
			String param = request.getParameter("kill_btn");
			
			File file = new File("C:\\Users\\Parth-OZ\\Desktop\\Project\\ENMS\\PollingScheduler\\enmsActions\\json\\15.json");
			FileReader read = new FileReader(file);
			String s = new String("");
			int c=0;
			while( (c=read.read())!=-1){
				s += (char)c;
			}
			out.print(s);
			JSONParser parser = new JSONParser();
			JSONObject jsonobj = (JSONObject)parser.parse(s);
			jsonobj.put("Parameter", param);
			read.close();
			FileWriter writer = new FileWriter(file);
			writer.write(jsonobj.toString());
			writer.close();
			
			stmt.close();
			conn.close();
%>		
</body>
</html>