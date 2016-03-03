import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ActionExecution {
	private String fileName;
	private String runCommand;
	
	public ActionExecution(JSONObject action) {
		System.out.println("inintialising actionExecutiton and getting filename and runCmd");//for debug
		// TODO Auto-generated constructor stub
		fileName = (String) action.get("Name");
		runCommand = (String) action.get("runCommand");
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
		System.out.println("ExecuteAction in actionExecution");
		JSONObject result = new JSONObject();
		if(fileName.equalsIgnoreCase("drives_info")){
			String str = ActionImplementation.get_PC_Drivers_info();
				result.put("result", str);
		}
		if(fileName.equalsIgnoreCase("ram_status")){
			System.out.println("ram_status");
			String str = ActionImplementation.get_RAM_info();
			System.out.println(str);
			result.put("result", str);
		}
		if(fileName.equalsIgnoreCase("username")){
			System.out.println("username");
			String str = System.getProperty("user.name");
			System.out.println(str);
			result.put("result", str);
		}
		//ACTION Execution logic considering client OS.
		System.out.println("Executing Action and result is:" + result.toString());//for debug

		return result;
	}
}
