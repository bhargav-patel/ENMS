import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONObject;


public class ClientSocketAgent {
	Socket socket;
	
	public ClientSocketAgent(Socket socket) {
		super();
		this.socket = socket;
	}
	public void getFile(){
		//receive file from ServerSocketAgent
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
}
