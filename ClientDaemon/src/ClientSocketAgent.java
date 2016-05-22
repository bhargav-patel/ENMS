import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	
	public int uploadFile(String fileName, File path){
		try {

			File file = new File(path,fileName);
			FileInputStream fis = new FileInputStream(file);
			System.out.println(file.getCanonicalPath());
			
			dos.writeUTF(fileName);//send filename
			
			byte[] buffer = new byte[(int) file.length()];
			dos.writeLong(buffer.length);//send fileSize
			
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(buffer,0,buffer.length);
			
			dos.write(buffer);
			
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
			filename = dis.readUTF();//read FileName
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
			byte[] buffer  = new byte[1];
			int fileLength = (int) dis.readLong();//read fileLength
			final int BUFFER_lENGTH = dis.readInt();//read bufferLength
			int c=0;
			
			System.out.println(buffer.length+"isbufferlength");
			for(int i=0;i<fileLength;i+=BUFFER_lENGTH){
				if((c = dis.read(buffer))>0){
					output.write(buffer, 0, c);
				}
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
			System.out.println(action.toJSONString());
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
