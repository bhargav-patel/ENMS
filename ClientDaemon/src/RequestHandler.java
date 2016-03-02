import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
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
		JSONObject jsonResult = new JSONObject();
		File actionDir = new File(LocalIO.getConfig().get("actionDir").toString());
		File[] listofFiles = actionDir.listFiles();
		
		for (File file : listofFiles) {
			jsonResult.put("actionID", file.getName().substring(0,file.getName().indexOf('.')));
			jsonResult.put("ResultasJSONString", executeFile(file.getName()));
		}
		
		try {
			new ClientSocketAgent(socket).sendActionResponse(jsonResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String executeFile(String fileName) {
		JSONObject action = LocalIO.getAction(fileName.substring(0, fileName.indexOf('.')));
		ActionExecution ae = new ActionExecution(action);
		JSONObject result = ae.ExecuteAction(action);

		return result.toJSONString();
	}

}
