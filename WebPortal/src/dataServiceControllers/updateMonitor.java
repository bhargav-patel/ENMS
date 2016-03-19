package dataServiceControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class updateMonitor
 */
@WebServlet("/updateMonitor")
public class updateMonitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMonitor() {
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
			String monitor_name = request.getParameter("monitor_name");
			int polling_duration = Integer.parseInt(request.getParameter("polling_duration"));
			int device_id = Integer.parseInt(request.getParameter("device"));
			int action_id = Integer.parseInt(request.getParameter("action"));
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			
			String query = "UPDATE `monitor` SET `name`='"+monitor_name+"', `polling-duration`='"+polling_duration+"', `device_id`='"+device_id+"', `action_id`='"+action_id+"' WHERE `id`='"+monitor_id+"';";
			int status = stmt.executeUpdate(query);

			json.put("response_code", status);
			
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
