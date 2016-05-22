<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+password);
		response.setContentType("text/json");
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT password FROM user WHERE username='"+username+"'");
		if(rs.next() && rs.getString(1).equals(password)){
			response.sendRedirect("../");
			session.setAttribute("userid", username);
			session.setAttribute("password", password);
		}
		else{
			response.sendRedirect("login.jsp?OAuth=false");
		}
		rs.close();
		stmt.close();
		rs.close();
		conn.close();
%>
</body>
</html>