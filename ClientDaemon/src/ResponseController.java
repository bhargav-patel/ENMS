import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;


public class ResponseController {
	
	public ResponseController(){
		
		JSONObject config = LocalIO.getConfig();
		
	}
	
	public void listen(){
		//Listen for server actions and create new request Handler for that process.
	}
	
	public static void main(String args[]){
		ResponseController rc = new ResponseController();
		rc.listen();
	}
}
