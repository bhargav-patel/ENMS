import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.json.simple.JSONObject;


public class ActionImplementation {
	
	public static JSONObject get_PC_Drivers_info(){
		DebugHelper dh = new DebugHelper("ActionImplementation", "get_PC_Drivers_info()");
		dh.debugThisFunction(true);
		dh.header();
		
		JSONObject result = new JSONObject();
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();
		result.put("OS name" , System.getProperty("os.name") + "\n");
	
		// returns pathnames for files and directory
		paths = File.listRoots();
		// for each pathname in pathname array
		int i=0;
		for(File path:paths)
		{
			i++;
		    // prints file and directory paths
			result.put("Drive Name"+i, path);
			result.put("Description"+i,fsv.getSystemTypeDescription(path));
		}
		
		dh.footer();
		return result;
	}
	
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
