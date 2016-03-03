import java.io.File;

import javax.swing.filechooser.FileSystemView;


public class ActionImplementation {
	
	public static String get_PC_Drivers_info(){
		DebugHelper dh = new DebugHelper("ActionImplementation", "get_PC_Drivers_info()");
		dh.debugThisFunction(true);
		dh.header();
		
		String result = new String();
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();
		result = "OS name" + System.getProperty("os.name") + "\n";
	
		// returns pathnames for files and directory
		paths = File.listRoots();
		// for each pathname in pathname array
		for(File path:paths)
		{
		    // prints file and directory paths
		    result = result + "Drive Name: "+path + "Description: "+ fsv.getSystemTypeDescription(path);
		}
		
		dh.footer();
		return result;
	}
	
	public static String get_RAM_info(){
		DebugHelper dh = new DebugHelper("ActionImplementation", "get_RAM_info()");
		dh.debugThisFunction(true);
		dh.header();
		
		SystemInfo si = new SystemInfo();
		SystemInfo.MemoryInfo ramInfo = si.new MemoryInfo();
		
		dh.footer();
		return ramInfo.get_RAM().toJSONString();
	}


}
