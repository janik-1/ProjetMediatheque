package Serveur;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Mediatheque.Mediatheque;

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
			this.exec();	
		} catch (IOException e) {
			System.err.println("Une erreur est survenue lors de la manipulation de la socket");
			e.printStackTrace();
			
		}
//		finally {
//			try {
//				this.socket.close();
//			} catch (IOException e) {
//				System.err.println("Impossible de fermer le socket");
//				e.printStackTrace();
//			}
	//}
	}	
	
	public void write(String message) {
		this.out.println(message);
	}
	
	public String read() throws IOException {
		return this.in.readLine();
	}
	
	public void ask() {
		this.write("ask");
	}
	
	public void end() {
		try {
			this.out.println("end");
			this.socket.close();
		} catch (IOException e) {}
	}
	
	public boolean isNumeric(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean aboExistant(int num) {
		if (Mediatheque.getInstance().getAbonneByNum(num) == null)
			return false;
		else
			return true;
	}
	
	public boolean docExistant(int num) {
		if (Mediatheque.getInstance().getDocByNum(num) == null)
			return false;
		else
			return true;
	}
	
	public boolean aboCheck(String str) {
		if (this.isNumeric(str)) {
			if(Mediatheque.getInstance().aboExistant(Integer.valueOf(str)));
				return true;
		}
		else
			return false;
	}
	
	public boolean docNumEtExistant(String str) {
		if (this.isNumeric(str)) {
			if (this.docExistant(Integer.valueOf(str)));
				return true;
		}
		else
			return false;
	}
	
	public abstract void exec() throws IOException;
	
	@Override
	public void finalize() throws Throwable {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("La connexion du socket " + socket.getRemoteSocketAddress().toString() + " s'est terminee.");
	}
	
	public Socket getSocket() {
		return this.socket;
	}
		
}

