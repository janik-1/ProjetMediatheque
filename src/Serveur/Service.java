package Serveur;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Service implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public abstract String serviceName();
	public abstract int servicePort();
	public abstract ServerSocket getServ();
	
	public Service(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {		
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void write(String message) {
		this.out.println(message);
	}
	
	public String read() throws IOException {
		return this.in.readLine();
	}
	
	@Override
	public void finalize() throws Throwable {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.err.println("La connexion du socket " + socket.getRemoteSocketAddress().toString() + " s'est terminee.");
	}
		
}

