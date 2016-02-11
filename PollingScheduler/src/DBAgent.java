import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBAgent {
	
	private Statement stmt;
	public DBAgent(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","root");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	
	}
	
	public Action getActionByID(int id){
		Action action = new Action();
		try {
			ResultSet rs =stmt.executeQuery("SELECT * FROM action WHERE id="+id);
			action.setId(rs.getInt(1));
			action.setCategory(rs.getString(2));
			action.setActionCategory_id(rs.getInt(3));
		} catch (SQLException e) {e.printStackTrace();}
		
		//set action parameters from action table from database
		return action;
	}
	
	public Device getDeviceByID(int id){
		Device device = new Device();
		try {
			System.out.println("sfbc");
			ResultSet rs = stmt.executeQuery("SELECT * FROM device WHERE id="+id);
			device.setId(rs.getInt(1));
			device.setName(rs.getString(2));
			try {
				device.setIp(InetAddress.getByName(rs.getString(3)));
			} catch (UnknownHostException e){e.printStackTrace();}
			
			device.setGrp_id(rs.getInt(4));
		} catch (SQLException e) {e.printStackTrace();}
		
		//set device parameters from device table from database
		return device;
	}
	
	public Monitor getMonitorByID(int id){
		Monitor monitor= new Monitor();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM monitor WHERE id="+id);
			if(rs.next()){
				monitor.setId(rs.getInt(1));
				monitor.setName(rs.getString(2));
				monitor.setPollingDuration(rs.getInt(3));
				monitor.setLastPoll(rs.getDate(4));
				monitor.setAction_id(rs.getInt(5));
				monitor.setDevice_id(rs.getInt(6));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		//set monitor parameters from monitor table from database
		return monitor;
	}
	
	public int updateMonitorResult(MonitorResult mr){
		
		int status = 0;
		try {
			stmt.executeUpdate("UPDATE monitor_result SET id="+mr.getId()+",Poll_Time="+mr.getPollTime().toString()+",resultData="+mr.getResultData()+",monitor_id="+mr.getMonitor_id()+";");
		} catch (SQLException e) {e.printStackTrace();}
		
		//update into moniterResult table
			
		return status;
	}
	
	public ArrayList<Monitor> getMonitorList(){
		ArrayList<Monitor> monitorList = new ArrayList<Monitor>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM monitor");
			Monitor m = new Monitor();
			while(rs.next()){
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setPollingDuration(rs.getInt(3));
				m.setLastPoll(rs.getDate(4));
				m.setAction_id(rs.getInt(5));
				m.setDevice_id(rs.getInt(6));
				monitorList.add(m);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		//load monitorList from database
		return monitorList;
	}
	
}
