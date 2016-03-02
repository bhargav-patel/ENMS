import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

import org.json.simple.JSONObject;

public class ServerSocketAgent {
	private Socket socket;
	private final int enmsserviceport  = 55155;
	
	public int uploadFile(Monitor mon, String fileName, File path){
		try {
			File file = new File(path,fileName);
			FileInputStream fis = new FileInputStream(file);
			socket = new Socket(new DBAgent().getDeviceByID(mon.getDevice_id()).getIp(),enmsserviceport);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(fileName);
			int c;
			
			while((c = fis.read())> -1){
				dos.write(c);
			}

			fis.close();
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;		
	}
	
	public int pushActionToClient(){
		//Implement after implementing handler in client daemon
		return 0;		
	}
	
	public MonitorResult sendExecuteRequest(Monitor mon){
		System.out.println("inside serversocketagent->sendexerequest");//debug
		MonitorResult monRes = null;
		DBAgent dbagent = new DBAgent();
		//get Monitor Result object with interaction with ClientSocketAgent
		try {
			socket = new Socket(dbagent.getDeviceByID(mon.getDevice_id()).getIp(),enmsserviceport);//gets ip address of respective monitor from it's device id
			DataInputStream datainputreader = new DataInputStream(socket.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(datainputreader);
			System.out.println("socket created and got input stream");
			JSONObject action = (JSONObject)ois.readObject();
			monRes = new MonitorResult();
			monRes.setResultData(action.toJSONString());//TODO convert to JSON when convert MonitorResult.resultdata data type
			System.out.println("before monitor id = "+monRes.getId());
			monRes.setId(dbagent.getMonitorResultIDByMonitorID(mon.getId()));
			System.out.println("after monitor id = "+monRes.getId());
			monRes.setPollTime(new Timestamp(new Date().getTime()));
			monRes.setMonitor_id(mon.getId());
			ois.close();
			datainputreader.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		dbagent.close();
		return monRes;
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
