import java.sql.Timestamp;

public class Monitor {
	
	private int id;
	private String name;
	private int pollingDuration;
	private Timestamp lastPoll;
	private int action_id;
	private int device_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPollingDuration() {
		return pollingDuration;
	}
	public void setPollingDuration(int pollingDuration) {
		this.pollingDuration = pollingDuration;
	}
	public Timestamp getLastPoll() {
		return lastPoll;
	}
	public void setLastPoll(Timestamp lastPoll) {
		this.lastPoll = lastPoll;
	}
	public int getAction_id() {
		return action_id;
	}
	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}
	public int getDevice_id() {
		return device_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	
}
