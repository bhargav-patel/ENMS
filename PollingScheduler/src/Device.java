import java.net.InetAddress;


public class Device {
	
	private int id;
	private int grp_id;
	private String name;
	private InetAddress ip;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrp_id() {
		return grp_id;
	}
	public void setGrp_id(int grp_id) {
		this.grp_id = grp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InetAddress getIp() {
		return ip;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
}
