package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;
import Mediatheque.*;

public class ServiceReservation extends Service {
	private int num;
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
				break;
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
				break;
		} 
		int numDoc = Integer.valueOf(refDoc);
		System.out.println("chargement du documment " + numDoc);
		
		try {
			Mediatheque.getInstance().reserver(numDoc, numAb);
			this.write("Votre reservation a ete effectue avec succes !");
		} catch (ReservationException e1) {
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

//	public void run() {
//		synchronized (this) {
//			System.out.println("Voici la liste des livres disponibles : ");
//			m.getLivresDisponibles();
//			System.out.println("Veillez saisir le numero du livres pour le reserver :");
//			
//			Scanner sc = new Scanner(System.in);
//			num = sc.nextInt();
//	
//			System.out.println("Veillez saisir votre numero d'abonne :");
//			
//			Scanner scAbonne = new Scanner(System.in);
//			numAbonne = scAbonne.nextInt();
//			
//			try {
//				m.getLivreDispo(num).reserver(m.getAbo(numAbonne));
//			} catch (EmpruntException e) {
//				e.printStackTrace();
//			}
//			
//			Timer t = new Timer();
//			t.schedule(new TaskReservation(m, this), 20000);
//			
//			System.out.println("Vous avez jusqu'a 30 secondes pour venir chercher votre livre !");
//		}
//	}
	
	public int numLivre() {
		return num;
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
