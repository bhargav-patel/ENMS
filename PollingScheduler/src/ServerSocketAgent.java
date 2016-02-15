import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sun.org.mozilla.javascript.internal.json.JsonParser;

public class ServerSocketAgent {
	private Socket socket;
	private final int enmsserviceport  = 4344;
	
	private int uploadFile(){
		
		return 0;		
	}
	
	public int pushActionToClient(){
		//Implement after implementing handler in client daemon
		return 0;		
	}
	
	public MonitorResult sendExecuteRequest(Monitor mon){
		
		MonitorResult monRes = null;
		
		//get Monitor Result object with interaction with ClientSocketAgent
		try {
			socket = new Socket(new DBAgent().getDeviceByID(mon.getDevice_id()).getIp(),enmsserviceport);//gets ip address of respective monitor from it's device id
			ObjectInputStream ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
			JSONObject action = (JSONObject)ois.readObject();
			monRes.setResultData(action.toJSONString());//TODO convert to JSON when convert MonitorResult.resultdata data type
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return monRes;
	}
}
