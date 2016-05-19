package dataServiceControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class getResentMonitorResults
 */
@WebServlet("/getRecentMonitorResults")
public class getResentMonitorResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getResentMonitorResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray result = new JSONArray();
		response.setContentType("text/json");
		
		try {
			int howMany = Integer.parseInt(request.getParameter("howMany"));
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT monitor_result.id,Poll_Time,monitor_id,monitor.name,monitor.polling_duration,monitor.lastPoll,action.name,action.actionCategory_id,device.name,device.ip FROM monitor_result,monitor,action,device WHERE monitor_id=monitor.id AND monitor.action_id=action.id AND monitor.device_id=device.id ORDER BY Poll_Time DESC LIMIT "+howMany+"");
			
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("id", rs.getInt(1));
				json.put("Poll_Time", rs.getTimestamp(2).toString());
				json.put("monitor_id", rs.getInt(3));
				json.put("monitorName", rs.getString(4));
				json.put("pollingDuration", rs.getInt(5));
				json.put("lastPoll", rs.getTimestamp(6).toString());
				json.put("actionName", rs.getString(7));
				json.put("actionCategoryID", rs.getInt(8));
				json.put("deviceName", rs.getString(9));
				json.put("ip", rs.getString(10));
				
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
