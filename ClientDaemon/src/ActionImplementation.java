import org.json.simple.JSONObject;


public class ActionImplementation {
	
	public static JSONObject get_RAM_info(){
		DebugHelper dh = new DebugHelper("ActionImplementation", "get_RAM_info()");
		dh.debugThisFunction(true);
		dh.header();
		
		SystemInfo si = new SystemInfo();
		SystemInfo.MemoryInfo ramInfo = si.new MemoryInfo();
		
		dh.footer();
		return ramInfo.get_RAM();
	}


}
