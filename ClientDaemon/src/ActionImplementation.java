import java.io.File;

import javax.swing.filechooser.FileSystemView;


public class ActionImplementation {
	
	public static String get_PC_Drivers_info(){
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
		
		return result;
	}


}
