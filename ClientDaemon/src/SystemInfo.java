import java.util.Map;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.pager.PageControl;
import org.json.simple.JSONObject;


public class SystemInfo {
	
	public class MemoryInfo{
		Mem mem = null;
		public MemoryInfo(){
			DebugHelper dh = new DebugHelper("SystemInfo MemoryInfo", "Constructor");
			dh.debugThisFunction(true);
			dh.header();
			
			Shell shell = new Shell();
			shell.setPageSize(PageControl.SIZE_UNLIMITED);
			try {
				mem = shell.getSigar().getMem();
			} catch (SigarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dh.footer();
		}
		public JSONObject get_RAM(){
			DebugHelper dh = new DebugHelper("SystemInfo MemoryInfo", "get_RAM()");
			dh.debugThisFunction(true);
			dh.header();
			
			JSONObject result = new JSONObject();
			result.put("Total", mem.getRam());
			System.out.println("Ram total is this->>>>>>>>>>>>>>>>>>>"+mem.getRam());
			result.put("Used", mem.getActualUsed());
			result.put("Free", mem.getActualFree());
			result.put("UsedPercentage", mem.getUsedPercent());
		
			dh.footer();
			return result;
		}
	}
	
}
