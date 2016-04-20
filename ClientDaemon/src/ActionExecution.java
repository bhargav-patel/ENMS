import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ActionExecution {
	private String fileName;
	private String runFunction;
	private URLClassLoader loader;
	
	public ActionExecution(JSONObject action) {
		DebugHelper dh = new DebugHelper("ActionExecution", "Constructor");
		dh.debugThisFunction(true);
		dh.header();
		
		dh.println("inintialising actionExecutiton and getting filename and runCmd");//for debug
		// TODO Auto-generated constructor stub
		fileName = (String) action.get("Name");
		runFunction = (String) action.get("runFunction");
		
		dh.footer();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getrunFunction() {
		return runFunction;
	}
	public void setrunFunction(String runFunction) {
		this.runFunction = runFunction;
	}
	
	public JSONObject ExecuteAction(JSONObject action){//rename to ExecuteAction and delete existing functin
		JSONObject result = new JSONObject();
		String actionDir = LocalIO.getConfig().get("actionDir").toString();
		try {
			loader = new URLClassLoader(new URL[]{new URL("file://"+ actionDir+"/")});
			Class<?> c = loader.loadClass(fileName);
			Object obj = c.newInstance();
			Method m = c.getDeclaredMethod(runFunction,new Class[]{Object.class});
			m.setAccessible(true);
			Object resultobj = m.invoke(obj,new Object[]{null});
			result = (JSONObject)resultobj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
