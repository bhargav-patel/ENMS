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
 * Servlet implementation class addAction
 */
@WebServlet("/addAction")
public class addAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addAction() {
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
			String action_name = request.getParameter("action_name");
			int action_id = Integer.parseInt(request.getParameter("action_id"));
			int category = Integer.parseInt(request.getParameter("category"));
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","temppass");
			Statement stmt = conn.createStatement();
			
			String query = "INSERT INTO `enms`.`action` (`id`,`name`, `actionCategory_id`) VALUES ('"+action_id+"', '"+action_name+"', '"+category+"');";
			int status = stmt.executeUpdate(query);

			json.put("response_code", status);
			stmt.close();
			conn.close();
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
