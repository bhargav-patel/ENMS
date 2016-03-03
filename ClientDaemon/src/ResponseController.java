import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//import org.json.simple.JSONObject;

public class ResponseController {
	private ServerSocket ss;
	private final int enmsserviceport = 55155;

	public ResponseController(){
		//JSONObject config = LocalIO.getConfig();
	}
	
	public void listen(){
		DebugHelper dh = new DebugHelper("ResponseController", "listen()");
		dh.debugThisFunction(true);
		dh.header();
		
		while (true) {
			//Listen for server actions and create new request Handler for that process.
			dh.println("Response Controller mic testing");
			try {
				ss = new ServerSocket(enmsserviceport);
				Socket s = ss.accept();
				Thread t = new Thread(new RequestHandler(s));
				t.run();
				s.close();
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//dh.footer();
	}
	
	public static void main(String args[]){
		ResponseController rc = new ResponseController();
		rc.listen();
	}
}
