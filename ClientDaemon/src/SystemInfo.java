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
			Shell shell = new Shell();
			shell.setPageSize(PageControl.SIZE_UNLIMITED);
			try {
				mem = shell.getSigar().getMem();
			} catch (SigarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public JSONObject get_RAM(){
			JSONObject result = new JSONObject();
			result.put("Total", mem.getRam());
			System.out.println("Ram total is this->>>>>>>>>>>>>>>>>>>"+mem.getRam());
			result.put("Used", mem.getActualUsed());
			result.put("Free", mem.getActualFree());
			result.put("UsedPercentage", mem.getUsedPercent());
			return result;
		}
	}
	
}
