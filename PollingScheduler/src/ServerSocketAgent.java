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
import org.json.simple.parser.JSONParser;


public class ServerSocketAgent {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private final int enmsserviceport  = 55155;
	
	public ServerSocketAgent(Device dev) {
		super();
		
		DebugHelper dh = new DebugHelper("ServerSocketAgent", "Constructor");
		dh.debugThisFunction(true);
		dh.header();
		
		try {
			dh.println(dev.getIp().toString());
			this.socket = new Socket(dev.getIp(),enmsserviceport);
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dh.footer();
	}

	public int uploadFile(Monitor mon, String fileName, File path){
		try {
			File file = new File(path,fileName);
			FileInputStream fis = new FileInputStream(file);
			
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
		DebugHelper dh = new DebugHelper("ServerSocketAgent", "sendExecuteRequest()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("inside serversocketagent->sendexerequest");//debug
		MonitorResult monRes = null;
		DBAgent dbagent = new DBAgent();
		//get Monitor Result object with interaction with ClientSocketAgent
		try {
			dos.writeInt(mon.getAction_id());
			//dataoutputwriter.close();
			dh.println(">>>>>>>>"+socket.isClosed()+socket.isConnected());
			ObjectInputStream ois = new ObjectInputStream(dis);
			dh.println("socket created and got input stream");
			JSONObject action = (JSONObject)ois.readObject();
			monRes = new MonitorResult();
			monRes.setResultData(action.toJSONString());//TODO convert to JSON when convert MonitorResult.resultdata data type
			dh.println("before monitor id = "+monRes.getId());
			monRes.setId(dbagent.getMonitorResultIDByMonitorID(mon.getId()));
			dh.println("after monitor id = "+monRes.getId());
			monRes.setPollTime(new Timestamp(new Date().getTime()));
			monRes.setMonitor_id(mon.getId());
			ois.close();
			//datainputreader.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		dbagent.close();
		
		dh.footer();
		return monRes;
	}
	
	public void close(){
		try {
			dis.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
