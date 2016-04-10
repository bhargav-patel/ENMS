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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class getMonitorListbyDeviceId
 */
@WebServlet("/getMonitorListbyDeviceId")
public class getMonitorListbyDeviceId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getMonitorListbyDeviceId() {
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
		JSONArray result = new JSONArray();
		response.setContentType("text/json");
		int deviceid = Integer.parseInt(request.getParameter("deviceid"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery("SELECT * FROM monitor WHERE device_id= '"+deviceid+"'");
			while (rs.next()) {
							
				JSONObject json = new JSONObject();
				json.put("monitor_id", rs.getInt(1));
				json.put("monitor_name", rs.getString(2));
				json.put("polling_duration", rs.getString(3));
				json.put("lastPoll", rs.getString(4));
				json.put("action_id", rs.getString(5));
				json.put("device_id", rs.getString(6));
				
				result.add(json);
			}
			
			response.getWriter().println(result);
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
