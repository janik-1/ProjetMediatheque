package Serveur;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;

import Mediatheque.*;

public class ServiceReservation extends Service {
	private Mediatheque m;
	private int num;
	private int numAbonne;
	private int PORT_RESERVATION;
	private ServerSocket socket_res;
	private String host = "localhost";
	
	public ServiceReservation(Mediatheque m, Socket accept) {
		super(accept);
		this.m = m;
		this.PORT_RESERVATION = 2600;
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
