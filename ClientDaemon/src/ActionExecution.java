import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ActionExecution {
	private String fileName;
	private String runCommand;
	
	public ActionExecution(JSONObject action) {
		DebugHelper dh = new DebugHelper("ActionExecution", "Constructor");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("inintialising actionExecutiton and getting filename and runCmd");//for debug
		// TODO Auto-generated constructor stub
		fileName = (String) action.get("Name");
		runCommand = (String) action.get("runCommand");
		
		dh.footer();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRunCommand() {
		return runCommand;
	}
	public void setRunCommand(String runCommand) {
		this.runCommand = runCommand;
	}
	
	public JSONObject ExecuteAction(JSONObject action){
		DebugHelper dh = new DebugHelper("ActionExecution", "Constructor");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("ExecuteAction in actionExecution");
		JSONObject result = new JSONObject();
		if(fileName.equalsIgnoreCase("drives_info")){
			result = ActionImplementation.get_PC_Drivers_info();
		}
		else if(fileName.equalsIgnoreCase("ram_status")){
			dh.println("ram_status");
			result = ActionImplementation.get_RAM_info();
			dh.println(result.toJSONString());
		}
		else if(fileName.equalsIgnoreCase("username")){
			dh.println("username");
			String str = System.getProperty("user.name");
			dh.println(str);
			result.put("result", str);
		}
		else if(fileName.equalsIgnoreCase("ram_usage")){
			for(int i=0;i<10;i++){
				result.put("Usage"+i, ActionImplementation.get_RAM_info());
			}
		}
		//ACTION Execution logic considering client OS.
		dh.println("Executing Action and result is:" + result.toString());//for debug

		
		dh.footer();
		
		return result;
	}
}
