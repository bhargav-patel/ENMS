import java.sql.Timestamp;


public class MonitorResult {
	
	private int id;
	private Timestamp pollTime;
	private String resultData; //TODO Convert to json object
	private int monitor_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getPollTime() {
		return pollTime;
	}
	public void setPollTime(Timestamp pollTime) {
		this.pollTime = pollTime;
	}
	public String getResultData() {
		return resultData;
	}
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	public int getMonitor_id() {
		return monitor_id;
	}
	public void setMonitor_id(int monitor_id) {
		this.monitor_id = monitor_id;
	}
	
}
