package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Service implements Runnable {
	private Socket socket;
	
	public abstract String serviceName();
	public abstract int servicePort();
	public abstract ServerSocket getServ();
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	

	public void finalize() throws Throwable {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.err.println("La connexion du socket " + socket.getRemoteSocketAddress().toString() + " s'est terminee.");
	}

}

