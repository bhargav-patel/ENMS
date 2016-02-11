import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBAgent {
	
	private Connection connection;
	
	public DBAgent(){
		String URL = "jdbc:mysql://localhost/enms";
		String USER = "root";
		String PASSWORD = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public Action getActionByID(int id){
		Action action = new Action();
		
		//set action parameters from action table from database
		
		return action;
	}
	
	public Device getDeviceByID(int id){
		Device device = new Device();
		
		//set device parameters from device table from database
		
		return device;
	}
	
	public Monitor getMoniterByID(int id){
		Monitor monitor= new Monitor();
		
		//set monitor parameters from monitor table from database
		
		return monitor;
	}
	
	public int updateMonitorResult(MonitorResult mr){
		
		int status = 0;
		
		//update into moniterResult table
			
		return status;
	}
	
	public ArrayList<Monitor> getMonitorList(){
		ArrayList<Monitor> monitorList = new ArrayList<Monitor>();
		
		//load monitorList from database
		
		return monitorList;
	}
	
}
