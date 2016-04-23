package dataServiceControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class analyseMonitor
 */
@WebServlet("/analyseMonitor")
public class analyseMonitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public analyseMonitor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		response.setContentType("text/json");
		
		try {
			int monitorid = Integer.parseInt(request.getParameter("monitorid"));
			int actionid = Integer.parseInt(request.getParameter("actionid"));
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM monitor_result WHERE monitor_id='"+monitorid+"'");
			
			while (rs.next()) {
				json.put("id", rs.getInt(1));
				json.put("Poll_Time", rs.getTimestamp(2).toString());
				JSONParser parser = new JSONParser();
				json.put("resultData", parser.parse(rs.getString(3)));
				json.put("monitor_id", rs.getInt(4));
			}
			
			response.getWriter().println(json);
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println("Invalid Request or Server Error.");
			e.printStackTrace();
		}	
		
	}

}
