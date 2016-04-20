import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
			dos.writeInt(mon.getAction_id());
			File file = new File(path,fileName);
			FileInputStream fis = new FileInputStream(file);
			
			dos.writeUTF(fileName);
			int c;
			byte[] bytes = new byte[8];
			while(fis.read(bytes)>-1){
				dos.write(bytes);
			}

			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			if(receiveMessage().equalsIgnoreCase("json_NotAvailable")){
				pushActionToClient(mon);
			}
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

	private void pushActionToClient(Monitor mon) {
		uploadFile(mon, String.valueOf(mon.getAction_id()).concat(".json"), null);
		File file = new File(String.valueOf(mon.getAction_id()).concat(".json"));
		JSONParser parser = new JSONParser();
		JSONObject actionFileName = null;
		try {
			actionFileName = (JSONObject)parser.parse(new FileReader(file));
		} catch (IOException | ParseException e) {e.printStackTrace();}
		uploadFile(mon, actionFileName.get("Name").toString().concat(".class"), null);
	}
	
	public String receiveMessage(){
		try {
			return dis.readUTF();
		} catch (IOException e) {e.printStackTrace();}
		return "Error in receiving message";
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
