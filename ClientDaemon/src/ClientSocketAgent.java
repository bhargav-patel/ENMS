import java.net.Socket;


public class ClientSocketAgent {
	Socket socket;
	
	public ClientSocketAgent(Socket socket) {
		super();
		this.socket = socket;
	}
	public void getFile(){
		//receive file from ServerSocketAgent
	}
	public void sendActionResponse(){
		//send action execution result to server via socket
	}
}
