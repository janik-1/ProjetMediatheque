package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	private Socket socket;
	private Integer port;
	private String hostname;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	
	
	public Client(String host, Integer port) {
		this.hostname = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			this.socket = new Socket(this.hostname, this.port);
			System.out.println("Vous etes connecté");
		} catch (IOException e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
		if(this.socket.isConnected()) {
			try {
				this.socketIn = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
				this.socketOut = new PrintWriter (socket.getOutputStream ( ), true);
				BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));	
				
				while(true) {
					if(this.socket.isClosed()) break;
					String line = clavier.readLine();
					this.socketOut.println(line);
					line = this.socketIn.readLine();
					System.out.println(line);
					if(line == null) continue;
					if(!line.isEmpty() && line.equals("end"))
						this.end();
				}
			} catch (IOException e) {
				System.out.println("Erreur lors de la manipulation de la socket");
				e.printStackTrace();
			}
		}
		this.end();
		
	}
	
	public void end() {
		try {
			if(this.socket.isConnected()) {
				this.socket.close();
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de fermeture de la socket");
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.socket.close();
	}
}
