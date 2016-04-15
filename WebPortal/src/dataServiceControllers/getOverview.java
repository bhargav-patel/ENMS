package dataServiceControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class overview
 */
@WebServlet("/getOverview")
public class getOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOverview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		response.setContentType("text/json");
		JSONArray jsonArray = new JSONArray();
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			
			String query = "SELECT monitor.id,monitor.name,monitor.polling_duration,monitor.lastPoll,monitor.action_id,action.name,device.name,device.ip,monitor_result.resultData FROM monitor,monitor_result,action,device where monitor.id=monitor_id AND action.id=action_id AND monitor.device_id=device.id group by device.id;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				JSONObject result = new JSONObject();
				result.put("monitor_id", rs.getInt(1));
				result.put("monitor_name", rs.getString(2));
				result.put("polling_duration", rs.getInt(3));
				result.put("lastPoll", rs.getTimestamp(4).toString());
				result.put("action_id", rs.getInt(5));
				result.put("actionName", rs.getString(6));
				result.put("deviceName", rs.getString(7));
				result.put("deviceIP", rs.getString(8));
				result.put("monitorResultData", rs.getString(9));
				Timestamp stamp = new Timestamp(new Date().getTime());
				if(( ( stamp.getTime()-rs.getTimestamp(4).getTime() )/1000 )<=(rs.getInt(3))){
				result.put("live", "ok");
				}else{
					result.put("live", "remove");
				}
				jsonArray.add(result);
				System.out.println(stamp.getTime() + "::::::DB timestamp=     "+ rs.getTimestamp(4).getTime() + "::::::rs.getint=  " + rs.getInt(1)+" ::::result=  "+( ( stamp.getTime()-rs.getTimestamp(4).getTime() )/1000 ));
			}
			response.getWriter().println(jsonArray);
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.put("error_message", "Invalid Request or Server Error.");
			jsonArray.add(json);
			e.printStackTrace();
		}finally{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
