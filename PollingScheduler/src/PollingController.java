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
		//TODO schedule and start monitors logic
		for (final Monitor monitor : monitorList) {//Runs for each monitors
			int count =0 ;//for debug
			Thread t = new Thread(new ActionRequest() {//created thread for each monitor
				@Override
				public void run() {
					// TODO Schedule timer for time equal to PollingDuration of monitor in seconds
					System.out.println("thread->run for startpolling");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							System.out.println("thread->run;-sending request");
							sendRequest();//on completion of timer sends execute request
						}
					}, 0,monitor.getPollingDuration()*1000);
				}
				
				@Override
				public int sendRequest() {
					ServerSocketAgent serversocketagent = new ServerSocketAgent();
					System.out.println("PollingController->sendRequest to :"+monitor.getId()+monitor.getName());
					MonitorResult mr = serversocketagent.sendExecuteRequest(monitor);
					serversocketagent.close();
					System.out.println("Recieved monitor result and now updating monitorResult to DB");
					DBAgent dba = new DBAgent();
					dba.updateMonitorResult(mr);
					dba.close();
					System.out.println("request sent");
					return 0;
				}
			});
			t.start();
			System.out.println("thread="+ count);
		}//foreach ends here
		return 0;
	}

}
