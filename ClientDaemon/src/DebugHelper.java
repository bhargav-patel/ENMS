
public class DebugHelper {
	private boolean debugMode;
	private boolean fileDebugMode;
	private String className;
	private String functionName;
	
	public DebugHelper(String className, String functionName) {
		super();
		this.className = className;
		this.functionName = functionName;
		this.debugMode = ((String)LocalIO.getConfig().get("debugMode")).equalsIgnoreCase("true");
		this.fileDebugMode = false;
	}
	public void debugThisFile(boolean debug){
		this.fileDebugMode = debug;
	}
	public void header(){
		if(debugMode && fileDebugMode){
			System.out.println("\n===========");
			System.out.println("\tHeader\t"+className+"\t->\t"+functionName);
			System.out.println("===========");
		}
	}
	public void footer(){
		if(debugMode && fileDebugMode){
			System.out.println("\n===========");
			System.out.println("\tFooter\t"+className+"\t->\t"+functionName);
			System.out.println("===========");
		}
	}
	public void print(String s){
		if(debugMode && fileDebugMode){
			System.out.println("\t>>"+s);
		}
	}
}
