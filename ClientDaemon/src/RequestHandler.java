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
		DebugHelper dh = new DebugHelper("RequestHandler", "run()");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println(">>>>>>>>>>>");
		dh.println(socket.toString());
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

		dh.println(actionFile.getName());
		jsonResult.put("ResultasJSONString", executeFile(actionFile.getName()));

		
		try {
			ClientSocketAgent csa = new ClientSocketAgent(socket);
			csa.sendActionResponse(jsonResult);
			csa.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dh.footer();
	}

	private String executeFile(String fileName) {
		DebugHelper dh = new DebugHelper("RequestHandler", "executeFile()");
		dh.debugThisFunction(true);
		dh.header();
		
		JSONObject action = LocalIO.getAction(fileName.substring(0, fileName.indexOf('.')));
		ActionExecution ae = new ActionExecution(action);
		JSONObject result = ae.ExecuteAction(action);

		dh.footer();
		return result.toJSONString();
	}

}
