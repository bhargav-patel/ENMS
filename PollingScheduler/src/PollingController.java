import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class PollingController {

	private ArrayList<Monitor> monitorList;

	public PollingController() {
		//TODO Load monitorList from database;
		DBAgent dbagent = new DBAgent();
		monitorList = dbagent.getMonitorList();
	}
	
	public int startPolling(){
		//TODO schedule and start monitors logic
		for (final Monitor monitor : monitorList) {//Runs for each monitors
			
			Thread t = new Thread(new ActionRequest() {//created thread for each monitor
				@Override
				public void run() {
					// TODO Schedule timer for time equal to PollingDuration of monitor in seconds
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							sendRequest();//on completion of timer sends execute request
						}
					}, monitor.getPollingDuration()*1000);
				}
				
				@Override
				public int sendRequest() {
					ServerSocketAgent serversocketagent = new ServerSocketAgent();
					MonitorResult mr = serversocketagent.sendExecuteRequest(monitor);
					DBAgent dba = new DBAgent();
					dba.updateMonitorResult(mr);
					return 0;
				}
			});
			t.start();
		}//foreach ends here
		return 0;
	}

}
