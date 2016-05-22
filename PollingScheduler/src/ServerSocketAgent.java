import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
			final int BUFFER_lENGTH = 1;
			File file = new File(path,fileName);
			FileInputStream fis = new FileInputStream(file);
			System.out.println(file.getCanonicalPath());
			
			dos.writeUTF(fileName);//send filename
			
			byte[] buffer = new byte[BUFFER_lENGTH];
			dos.writeLong(file.length());//send fileSize
			
			dos.writeInt(BUFFER_lENGTH);//send bufferLength
			BufferedInputStream bis = new BufferedInputStream(fis);
			for(int i=0; i<file.length();i+=BUFFER_lENGTH){
				bis.read(buffer,0,buffer.length);
				dos.write(buffer);
			}
			
			bis.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;		
	}
	
	public void getFile(){
		//receive file from ServerSocketAgent
		String filename = new String();
		
		try {
			filename = dis.readUTF();
			System.out.println(filename);
			File localDir = new File(System.getProperty("user.home"),".enmscd");
			File actiondir = new File(localDir,"actions");
			File f = new File(actiondir,filename);
			if(!f.exists()){
				f.createNewFile();
			}
			else{
				f.setWritable(true);
			}
			
			FileOutputStream output = new FileOutputStream(f);
			byte[] buffer  = new byte[(int) dis.readLong()];
			int c=0;
			System.out.println(buffer.length+"isbufferlength");
			if((c = dis.read(buffer))>0){
				System.out.println("here4"+buffer.length);
				output.write(buffer, 0, c);
			}
			
			output.close();
		} catch (IOException e) {e.printStackTrace();}
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
				pushActionToClient(mon,"both");
			}
			
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
			System.out.println(monRes.getResultData());
			if(dis.readUTF().equals("receiveFile")){
				System.out.println("UTF RECEIVE FILES");
				getFile();
			}
			ois.close();
			//datainputreader.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		dbagent.close();
		
		dh.footer();
		return monRes;
	}

	private void pushActionToClient(Monitor mon, String files_to_send) {
			uploadFile(mon, String.valueOf(mon.getAction_id()).concat(".json/"), new File(new File("enmsActions"),"json"));
			File file = new File(new File(new File("enmsActions"),"json"),String.valueOf(mon.getAction_id()).concat(".json"));
			JSONParser parser = new JSONParser();
			JSONObject actionFileName = null;
			try {
				actionFileName = (JSONObject)parser.parse(new FileReader(file));
			} catch (IOException | ParseException e) {e.printStackTrace();}
			uploadFile(mon, actionFileName.get("Name").toString().concat(".class/"), new File(new File("enmsActions"),"class"));
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
