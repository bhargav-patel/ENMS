import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LocalIO {
	public static JSONObject getConfig(){
		
		String homePath = System.getProperty("user.home");
		File localDir = new File(homePath,".enmscd");
		File confFile = new File(localDir,"conf.json");
		
		if(!localDir.exists()){
			File actionsDir = new File(localDir,"actions");
			actionsDir.mkdirs();
		}
		
		if(confFile.exists()){
			//System.out.println("Config file exists");
			JSONParser parser = new JSONParser();
			JSONObject conf = null;
			try {
				conf = (JSONObject) parser.parse(new FileReader(confFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return conf;			
		}
		else{			
			//System.out.println("Config file doesn't exists");
			JSONObject conf = new JSONObject();
			conf.put("localDir", localDir.getAbsolutePath());
			conf.put("actionDir", new File(localDir,"actions").getAbsolutePath());
			conf.put("debugMode", "false");
			
			try (FileWriter confWriter = new FileWriter(confFile)) {
				confFile.createNewFile();
				confWriter.write(conf.toJSONString());
			} catch (IOException e) {
				System.out.println("Error in writing configuration file : "+e.getMessage());
				e.printStackTrace();
			}
		
			return conf;
		}
		
	}
	
	public static JSONObject getAction(String id){
		DebugHelper dh = new DebugHelper("LocalIO", "getAction()");
		dh.debugThisFunction(true);
		dh.header();
		
		File localDir = new File(LocalIO.getConfig().get("actionDir").toString());
		File actionFile = new File(localDir,id+".json");
		JSONParser parser = new JSONParser();
		JSONObject action=null;
		try {
			action = (JSONObject) parser.parse(new FileReader(actionFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dh.footer();
		return action;
	}
}
