
public class DebugHelper {
	private boolean debugMode;
	private boolean fileDebugMode;
	private String className;
	private String functionName;
	
	public DebugHelper(String className, String functionName) {
		super();
		this.className = className;
		this.functionName = functionName;
		String mode = (String)LocalIO.getConfig().get("debugMode");
		this.debugMode = mode.equalsIgnoreCase("true");
		this.fileDebugMode = false;
	}
	public void debugThisFunction(boolean debug){
		this.fileDebugMode = debug;
	}
	public void header(){
		if(debugMode && fileDebugMode){
			System.out.println("\n============================================");
			System.out.println("Started | "+className+" >> "+functionName);
			System.out.println("============================================");
		}
	}
	public void footer(){
		if(debugMode && fileDebugMode){
			System.out.println("\n============================================");
			System.out.println("Terminated >> "+className+" -> "+functionName);
			System.out.println("============================================");
		}
	}
	public void println(String s){
		if(debugMode && fileDebugMode){
			System.out.println(">> "+s);
		}
	}
}
