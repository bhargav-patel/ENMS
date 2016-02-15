import java.io.IOException;
import java.net.Socket;

import org.json.simple.JSONObject;


public class RequestHandler implements Runnable {
	Socket socket;
	
	public RequestHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JSONObject action = LocalIO.getAction("12");
		ActionExecution ae = new ActionExecution(action);
		JSONObject result = ae.ExecuteAction(action);
		try {
			new ClientSocketAgent(socket).sendActionResponse(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
