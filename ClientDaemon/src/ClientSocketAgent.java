import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONObject;


public class ClientSocketAgent {
	Socket socket;
	
	public ClientSocketAgent(Socket socket) {
		super();
		this.socket = socket;
		System.runFinalizersOnExit(true);
	}
	public void getFile(){
		//receive file from ServerSocketAgent
		String filename = new String();
		int c;
		FileOutputStream fos;
		
		try {			
			DataInputStream reader = new DataInputStream(socket.getInputStream());
			filename = reader.readUTF();
			
			File localDir = new File(System.getProperty("user.home"),".enmscd");
			File actiondir = new File(localDir,"actions");
			File f = new File(actiondir,filename);
			fos = new FileOutputStream(f);
			if(!f.exists()){
				f.createNewFile();
			}
			else{
				f.setWritable(true);
			}
						
			while((c = reader.read())>0){
				fos.write(c);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sendActionResponse(JSONObject action) throws IOException{
		System.out.println("response sending....");//for debug
		try {
			//send action execution result to server via socket
			ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
			oos.writeObject(action);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void finalize() throws Throwable{
			socket.close();
	}
}
