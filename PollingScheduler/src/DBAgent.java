import java.sql.Connection;
import java.util.ArrayList;


public class DBAgent {
	
	private Connection connection;
	
	public DBAgent(){
		//Make JDBC Connection here
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
