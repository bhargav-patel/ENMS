import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class PollingController {

	private ArrayList<Monitor> monitorList;

	public PollingController() {
		//TODO Load monitorList from database;
		DBAgent dbagent = new DBAgent();
		monitorList = dbagent.getMonitorList();
		dbagent.close();
	}
	
	public int startPolling(){
		final DebugHelper dh = new DebugHelper("PollingController", "startPolling()");
		dh.debugThisFunction(true);
		dh.header();
		
		//TODO schedule and start monitors logic
		for (final Monitor monitor : monitorList) {//Runs for each monitors
			int count =0 ;count++;//for debug
			Thread t = new Thread(new ActionRequest() {//created thread for each monitor
				@Override
				public void run() {
					// TODO Schedule timer for time equal to PollingDuration of monitor in seconds
					Thread.currentThread().setName(monitor.getId()+"|"+monitor.getName());
					
					dh.println("THREAD STARTED "+Thread.currentThread().getId()+":"+monitor.getId()+"|"+monitor.getName());
					dh.println(monitorList.size()+"");
					
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							dh.println("thread->run;-sending request");
							sendRequest();//on completion of timer sends execute request
						}
					}, 0,monitor.getPollingDuration()*1000);
				}
				
				@Override
				public int sendRequest() {
					ServerSocketAgent serversocketagent = new ServerSocketAgent(new DBAgent().getDeviceByID(monitor.getDevice_id()));
					dh.println("PollingController->sendRequest to :"+monitor.getId()+monitor.getName());
					
					MonitorResult mr = serversocketagent.sendExecuteRequest(monitor);
					serversocketagent.close();
					dh.println("Recieved monitor result and now updating monitorResult to DB");
					DBAgent dba = new DBAgent();
					dba.updateMonitorResult(mr);
					dba.updateLastPollByMonitorID(monitor);
					dba.close();
					dh.println("request completed");
					return 0;
				}
			});
			t.start();
			dh.println("thread="+ count);
		}//foreach ends here
		
		dh.footer();
		return 0;
	}

}
