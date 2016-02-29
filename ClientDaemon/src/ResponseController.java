import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;

public class ResponseController {
	private ServerSocket ss;
	private final int enmsserviceport = 4344;

	public ResponseController(){
		JSONObject config = LocalIO.getConfig();
	}
	
	public void listen(){
		//Listen for server actions and create new request Handler for that process.
		try {
			ss = new ServerSocket(enmsserviceport);
			Socket s = ss.accept();
			Thread t = new Thread(new RequestHandler(s));
			t.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		ResponseController rc = new ResponseController();
		rc.listen();
	}
}
