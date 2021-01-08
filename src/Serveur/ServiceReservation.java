package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import Exceptions.ReservationException;
import Mediatheque.*;

public class ServiceReservation extends Service {
	private int numDoc;
	private int numAbonne;
	private int PORT_RESERVATION;
	private ServerSocket socket_res;
	private String host = "localhost";
	
	public ServiceReservation(Socket accept) {
		super(accept);
		this.PORT_RESERVATION = 3000;
	}
	
	
	@Override
	public void exec() throws IOException {
		//String line;
		//this.m.addTest();
		this.write("Bienvenue sur le service de reservation de la mediatheque " );
		this.write(Mediatheque.getInstance().getDocDisponibles());
		this.write("Saisisez le numero d'abonne");
		
		String refAbo = "";
		do {
			if (refAbo!="")
				this.write("Veuillez saisir un numero d'abonne valable");
			this.ask();
			refAbo = this.read();
			if (refAbo.equals("end")) {
				this.end();
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return;
			}				
		} while (!this.isNumeric(refAbo) || !Mediatheque.getInstance().aboExistant(Integer.valueOf(refAbo)));		
		int numAb = Integer.valueOf(refAbo);
		System.out.println("Connection de l'abonne " + numAb);
		
		this.write("Tapez le numero du document que vous souhaitez reserver");
		this.ask();
		String refDoc = this.read();
		while (!this.isNumeric(refDoc) || !Mediatheque.getInstance().docExistant(Integer.valueOf(refDoc))) {
			this.write("Veuillez saisir un numero de document valable et existant");
			this.ask();
			refDoc = this.read();
			if (refDoc.equals("end"))
				this.end();
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return;
		} 
		this.numDoc = Integer.valueOf(refDoc);
		System.out.println("chargement du documment " + numDoc);
		
		try {
			Mediatheque.getInstance().reserver(numDoc, numAb);
			this.write("Votre reservation a ete effectue avec succes !");
			this.write("10 sec");
			System.out.println("Vous avez jusqu'a 10 secondes pour venir chercher votre livre !");
			Timer t = new Timer();
			t.schedule(new TaskReservation(this), 10000);
			this.write("annulation ");
			
			
		} catch (ReservationException e1) {
			this.write(e1.toString());
			e1.printStackTrace();
			this.end();
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	
	
		this.end();
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
	}
	
	public int numDoc() {
		return this.numDoc;
	}
	
	public int numAbo() {
		return numAbonne;
	}
	
	public int getNumPort() {
		return PORT_RESERVATION;
	}

	public void finalize() throws Throwable {
		 
	}

	@Override
	public String serviceName() {
		return host;
	}

	@Override
	public int servicePort() {
		return this.PORT_RESERVATION;
	}

	@Override
	public ServerSocket getServ() {
		return socket_res;
	}

	
}
