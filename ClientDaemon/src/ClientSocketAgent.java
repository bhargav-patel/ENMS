import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.simple.JSONObject;


public class ClientSocketAgent {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	public ClientSocketAgent(Socket socket) {
		super();
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String Message){
		try {
			dos.writeUTF(Message);
		} catch (IOException e) {e.printStackTrace();}
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
	
	public int readActionId(){
		int action_id=-1;
		try {
			action_id = dis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return action_id;
	}
	
	public void sendActionResponse(JSONObject action){
		DebugHelper dh = new DebugHelper("ClientSocketAgent", "sendActionResponse()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("response sending....");//for debug
		try {
			//send action execution result to server via socket
			ObjectOutputStream oos = new ObjectOutputStream(dos);
			oos.writeObject(action);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
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
