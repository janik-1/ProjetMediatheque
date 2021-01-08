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
	private BufferedReader clavier;
	
	
	
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
				this.clavier = new BufferedReader(new InputStreamReader(System.in));
				while(true) {
					if(this.socket.isClosed()) break;
					String line = socketIn.readLine();			
					if(line == null) continue;
					if(!line.isEmpty()) {
						this.action(line);
					}
						
				}
			} catch (IOException e) {
				System.out.println("Erreur lors de la manipulation de la socket");
				e.printStackTrace();
			}
		}
		//this.end();
		
	}
	
	public void send(String msg) {
		this.socketOut.println(msg);
	}
	
	public void end() {
		try {
			if(this.socket.isConnected()) {
				this.socket.close();
				System.out.println("Fermeture de la socket");
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de fermeture de la socket");
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.socket.close();
	}
	
	public void action(String msg) {
		switch (msg) {
			case("end"): {
				this.end();
				break;
			}
			case("ask"): {
				String reponse = "";
				try {
					reponse = clavier.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.send(reponse);
				break;
			}
			default : {
				System.out.println(msg);
			}
	
		}
	}
}
