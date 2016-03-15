import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


public class DBAgent {
	
	private Statement stmt;
	private Connection con;
	public DBAgent(){
		DebugHelper dh = new DebugHelper("DBAgent", "Constructor");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("initialising dbagent");//for debug
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/enms","root","root");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dh.footer();
	}
	
	public Action getActionByID(int id){
		DebugHelper dh = new DebugHelper("DBAgent", "getActionByID()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("getting actionbyid");//for debug
		Action action = new Action();
		try {
			ResultSet rs =stmt.executeQuery("SELECT * FROM action WHERE id="+id);
			rs.next();
			action.setId(rs.getInt(1));
			action.setCategory(rs.getString(2));
			action.setActionCategory_id(rs.getInt(3));
		} catch (SQLException e) {e.printStackTrace();}
		
		//set action parameters from action table from database
		
		dh.footer();
		return action;
	}
	
	public Device getDeviceByID(int id){
		DebugHelper dh = new DebugHelper("DBAgent", "getDeviceByID()");
		dh.debugThisFunction(true);
		dh.header();
		
		Device device = new Device();
		try {
			dh.println("getting device id");//for debug
			ResultSet rs = stmt.executeQuery("SELECT * FROM device WHERE id="+id);
			rs.next();
			device.setId(rs.getInt(1));
			device.setName(rs.getString(2));
			try {
				device.setIp(InetAddress.getByName(rs.getString(3)));
			} catch (UnknownHostException e){e.printStackTrace();}
			
			device.setGrp_id(rs.getInt(4));
		} catch (SQLException e) {e.printStackTrace();}
		
		//set device parameters from device table from database
		
		dh.footer();
		return device;
	}
	
	public Monitor getMonitorByID(int id){
		DebugHelper dh = new DebugHelper("DBAgent", "getMonitorByID()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("getting monitorbyid");//for debug
		Monitor monitor= new Monitor();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM monitor WHERE id="+id);
			if(rs.next()){
				monitor.setId(rs.getInt(1));
				monitor.setName(rs.getString(2));
				monitor.setPollingDuration(rs.getInt(3));
				monitor.setLastPoll(rs.getTimestamp(4));
				monitor.setAction_id(rs.getInt(5));
				monitor.setDevice_id(rs.getInt(6));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		//set monitor parameters from monitor table from database
		
		dh.footer();
		return monitor;
	}
	
	
	public int getMonitorResultIDByMonitorID(int id){
		DebugHelper dh = new DebugHelper("DBAgent", "getMonitorResultIDByMonitorID()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("gettingmonitorResultbyid from monitor id");//for debug
		int monRes_id=0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM monitor_result WHERE monitor_id="+id);
			if(rs.next()){
				monRes_id = rs.getInt(1);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		//gets monitor_resultID from monitor ID
		
		dh.footer();
		return monRes_id;
	}
	
	public int insertMonitorResult(MonitorResult mr){
		int status = 0;
		try {
			status = stmt.executeUpdate("INSERT INTO `monitor_result` (`Poll_Time`, `resultData`, `monitor_id`) VALUES ('"+mr.getPollTime()+"', '\""+mr.getResultData()+"\"', '"+mr.getMonitor_id()+"');");
		} catch (SQLException e) {e.printStackTrace();}
		
		return status;
	}
	
	public int updateMonitorResult(MonitorResult mr){
		DebugHelper dh = new DebugHelper("DBAgent", "updateMonitorResult()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("update mr");
//		dh.println("updating monitor result with value :"+mr.getId()+mr.getResultData());//for debug
		int status = 0;
		
		try {
			status = stmt.executeUpdate("UPDATE `monitor_result` SET `Poll_Time`='"+mr.getPollTime()+"', `resultData`='"+mr.getResultData()+"', `monitor_id`='"+mr.getMonitor_id()+"' WHERE `id`='"+mr.getId()+"';");
		} catch (SQLException e) {System.out.println("MonitorResult entry not found so adding new value");}
		
		if(status==0)insertMonitorResult(mr);
		
		dh.footer();	
		return status;
	}
	
	public ArrayList<Monitor> getMonitorList(){
		DebugHelper dh = new DebugHelper("DBAgent", "getMonitorList()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("getting monitor list");//for debug
		ArrayList<Monitor> monitorList = new ArrayList<Monitor>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM monitor");
			Monitor m = null;
			while(rs.next()){
				m = new Monitor();
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setPollingDuration(rs.getInt(3));
				m.setLastPoll(rs.getTimestamp(4));
				m.setAction_id(rs.getInt(5));
				m.setDevice_id(rs.getInt(6));
				monitorList.add(m);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		//load monitorList from database
		
		dh.footer();
		return monitorList;
	}
	
	public int insertMonitorResult(MonitorResult mr){
		int status = 0;
		try {
			status = stmt.executeUpdate("INSERT INTO `monitor_result` (`Poll_Time`, `resultData`, `monitor_id`) VALUES ('"+mr.getPollTime()+"', '\""+mr.getResultData()+"\"', '"+mr.getMonitor_id()+"');");
		} catch (SQLException e) {e.printStackTrace();}
		
		//update into moniterResult table
			System.runFinalization();
		return status;
	}
	
	public void updateLastPollByMonitorID(Monitor mon){
		try {
			stmt.executeUpdate("UPDATE `monitor` SET `lastPoll`='"+new Timestamp(new Date().getTime())+"' WHERE `id`='"+mon.getId()+"';");
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void close(){
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
