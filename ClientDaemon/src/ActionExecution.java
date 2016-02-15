import org.json.simple.JSONObject;


public class ActionExecution {
	private String fileName;
	private String runCommand;
	
	public ActionExecution(JSONObject action) {
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
		JSONObject result = null;
		
		//ACTION Execution logic considering client OS.
				
		
		return result;
	}
}
