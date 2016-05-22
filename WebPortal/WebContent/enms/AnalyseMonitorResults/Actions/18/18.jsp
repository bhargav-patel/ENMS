<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NetStats</title>
</head>
<body>
<%String result = request.getParameter("data"); %>
<div id="hiddenDiv" hidden><%=result%></div>
<div style="font-size:2em;"><p>Graph for Byte In/Out VS. Time.<br>
<span style="background-color:#ccddff;color:#ccddff">  TxR  </span>Tx<span style="background-color:#99bbff;color:#99bbff">  Rx  </span>Rx</p></div>
<canvas id="analyse_Monitor2" width="550" height="300"></canvas>
</body>
</html>