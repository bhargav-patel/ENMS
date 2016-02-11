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
			localDir.mkdirs();
		}
		
		if(confFile.exists()){
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
			System.out.println(conf.toJSONString());
			return conf;
			
		}
		else{			
			JSONObject conf = new JSONObject();
			conf.put("homeDir", localDir.getAbsolutePath());
			
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
}
