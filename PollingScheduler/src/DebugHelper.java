
public class DebugHelper {
	private boolean debugMode;
	private boolean fileDebugMode;
	private String className;
	private String functionName;
	static int level;
	private String spacingUnit;
	private String threadInfo;
	
	public DebugHelper(String className, String functionName) {
		super();
		spacingUnit = "   ";
		this.className = className;
		this.functionName = functionName;
		String mode = (String)LocalIO.getConfig().get("debugMode");
		this.debugMode = mode.equalsIgnoreCase("true");
		this.fileDebugMode = false;
		threadInfo = Thread.currentThread().getId()+":"+Thread.currentThread().getName();
	}
	public void debugThisFunction(boolean debug){
		this.fileDebugMode = debug;
	}
	public void header(){
		String spacing = threadInfo;
		for(int i=0;i<level;i++)
			spacing+=spacingUnit;
		if(debugMode && fileDebugMode){
			System.out.println("\n"+spacing+"============================================");
			System.out.println(spacing+"Started | "+className+" >> "+functionName);
			System.out.println(spacing+"============================================");
		}
		level++;
	}
	public void footer(){
		String spacing = threadInfo;
		for(int i=0;i<level;i++)
			spacing+=spacingUnit;
		if(debugMode && fileDebugMode){
			System.out.println("\n"+spacing+"============================================");
			System.out.println(spacing+"Terminated | "+className+" >> "+functionName);
			System.out.println(spacing+"============================================");
		}
		level--;
	}
	public void println(String s){
		String spacing = threadInfo;
		for(int i=0;i<level;i++)
			spacing+=spacingUnit;
		if(debugMode && fileDebugMode){
			System.out.println(spacing+">> "+s);
		}
	}
}
