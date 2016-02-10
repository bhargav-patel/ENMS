import java.net.Socket;


public class RequestHandler implements Runnable {
	Socket socket;
	
	public RequestHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
