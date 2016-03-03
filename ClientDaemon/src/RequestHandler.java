import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.net.Socket;

import org.json.simple.JSONObject;


public class RequestHandler implements Runnable {
	Socket socket;
	int action_id;
	
	public RequestHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println(">>>>>>>>>>>");
		System.out.println(socket);
		try {
			DataInputStream datainputreader = new DataInputStream(socket.getInputStream());
			action_id = datainputreader.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject jsonResult = new JSONObject();
		File actionDir = new File(LocalIO.getConfig().get("actionDir").toString());
		File actionFile = new File(actionDir, action_id+".json");

		System.out.println(actionFile.getName());
		jsonResult.put("ResultasJSONString", executeFile(actionFile.getName()));

		
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
