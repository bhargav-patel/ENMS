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

/**
 * Servlet implementation class getMonitorDetails
 */
@WebServlet("/getMonitorDetails")
public class getMonitorDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getMonitorDetails() {
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
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		response.setContentType("text/json");
		
		try {
			int monitor_id = Integer.parseInt(request.getParameter("monitor_id"));
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM monitor WHERE id="+monitor_id;
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			JSONObject result = new JSONObject();
			result.put("id", rs.getInt(1));
			result.put("monitor_name", rs.getString(2));
			result.put("polling_duration", rs.getInt(3));
			result.put("lastPoll", rs.getTimestamp(4));
			result.put("action_id", rs.getInt(5));
			result.put("device_id", rs.getInt(6));
			
			json.put("response_code", 1);
			json.put("response", result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.put("response_code", -1);
			json.put("error_message", "Invalid Request or Server Error.");
			e.printStackTrace();
		}finally{
			response.getWriter().println(json);
		}
	}

}
