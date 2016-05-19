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
		
		//schedule and start monitors logic
		for (final Monitor monitor : monitorList) {//Runs for each monitors
			int count =0 ;count++;//for debug
			Thread t = new Thread(new ActionRequest() {//created thread for each monitor
				@Override
				public void run() {//code for each thread execution
					
					//Schedule timer for time equal to PollingDuration of monitor in seconds
					Thread.currentThread().setName(monitor.getId()+"|"+monitor.getName());
					
					dh.println("THREAD STARTED "+Thread.currentThread().getId()+":"+monitor.getId()+"|"+monitor.getName());
					dh.println(monitorList.size()+"");
					
					DBAgent dba = new DBAgent();
					if (dba.getActionCategory(monitor.getId())==0 || dba.getActionCategory(monitor.getId())==2) {
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								dh.println("thread->run;-sending request");
								sendRequest();//on completion of timer sends execute request
							}
						}, 0, monitor.getPollingDuration() * 1000);
					}
					else{
						sendRequest();
					}
					dba.close();
					
				}//code over
				
				@Override
				public int sendRequest() {
					//check for fired action(s) 
					DBAgent dbagent = new DBAgent();
					if (dbagent.getActionCategory(monitor.getId())==2) {
							if(dbagent.is_fired(monitor.getAction_id())){
								dbagent.updateLastPollByMonitorID(monitor);
								return 0;
							}
					}
					dbagent.close();
					
					//regular polling application
					ServerSocketAgent serversocketagent = new ServerSocketAgent(new DBAgent().getDeviceByID(monitor.getDevice_id()));
					dh.println("PollingController->sendRequest to :"+monitor.getId()+monitor.getName());
					MonitorResult mr = serversocketagent.sendExecuteRequest(monitor);
					serversocketagent.close();
					dh.println("Recieved monitor result and now updating monitorResult to DB");
					DBAgent dba = new DBAgent();
					dba.updateMonitorResult(mr);
					dba.updateLastPollByMonitorID(monitor);

					//reset fired actions to stop second time execution
					if (dba.getActionCategory(monitor.getId())==2) {
						dba.resetFired(monitor.getAction_id());
					}
					
					//always close agent and sockets after use to prevent overflow in connection
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
