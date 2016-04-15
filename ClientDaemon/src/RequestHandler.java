import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.net.Socket;

import org.json.simple.JSONObject;


public class RequestHandler implements Runnable {
	ClientSocketAgent csa;
	int action_id;
	
	public RequestHandler(Socket socket) {
		super();
		this.csa = new ClientSocketAgent(socket);
	}

	@Override
	public void run() {
		DebugHelper dh = new DebugHelper("RequestHandler", "run()");
		dh.debugThisFunction(true);
		dh.header();
				
		action_id = csa.readActionId();
		if(action_id==1){
			csa.getFile();
			return;
		}
		JSONObject jsonResult = new JSONObject();
		File actionDir = new File(LocalIO.getConfig().get("actionDir").toString());
		File actionFile = new File(actionDir, action_id+".json");
		System.out.println("actionID= "+action_id);
		dh.println(actionFile.getName());
		jsonResult = executeFile(actionFile.getName());

		csa.sendActionResponse(jsonResult);
		csa.close();
		
		dh.footer();
	}

	private JSONObject executeFile(String fileName) {
//		DebugHelper dh = new DebugHelper("RequestHandler", "executeFile()");
//		dh.debugThisFunction(true);
//		dh.header();
//		
		System.out.println(fileName);
		JSONObject action = LocalIO.getAction(fileName.substring(0, fileName.indexOf('.')));
		ActionExecution ae = new ActionExecution(action);
		JSONObject result = ae.ExecuteAction(action);

//		dh.footer();
		return result;
	}

}
