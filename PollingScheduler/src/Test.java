
public class Test {

	public static void main(String[] args) {
		DBAgent db = new DBAgent();
		Monitor m =db.getMonitorList().get(0);
		System.out.println(m.getId());
	}

}
