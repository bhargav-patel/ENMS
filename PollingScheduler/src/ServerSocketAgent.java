import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

import org.json.simple.JSONObject;

public class ServerSocketAgent {
	private Socket socket;
	private final int enmsserviceport  = 55155;
	
	private int uploadFile(){
		return 0;		
	}
	
	public int pushActionToClient(){
		//Implement after implementing handler in client daemon
		return 0;		
	}
	
	public MonitorResult sendExecuteRequest(Monitor mon){
		System.out.println("inside serversocketagent->sendexerequest");
		MonitorResult monRes = null;
		
		//get Monitor Result object with interaction with ClientSocketAgent
		try {
			socket = new Socket(new DBAgent().getDeviceByID(mon.getDevice_id()).getIp(),enmsserviceport);//gets ip address of respective monitor from it's device id
			ObjectInputStream ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
			JSONObject action = (JSONObject)ois.readObject();
			monRes = new MonitorResult();
			monRes.setResultData(action.toJSONString());//TODO convert to JSON when convert MonitorResult.resultdata data type
			System.out.println("before monitor id = "+monRes.getId());
			monRes.setId(new DBAgent().getMonitorResultIDByMonitorID(mon.getId()));
			System.out.println("after monitor id = "+monRes.getId());
			monRes.setPollTime(new Timestamp(new Date().getTime()));
			monRes.setMonitor_id(mon.getId());
			ois.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return monRes;
	}
}
