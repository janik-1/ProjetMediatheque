package Serveur;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Mediatheque.*;

public class ServiceRetour extends Service {
	private Mediatheque m;
	private int numLivre;
	private int numAbonne;
	private String nom;
	private ServerSocket socket_ret;
	private int PORT_RETOUR;
	
	public ServiceRetour(Mediatheque m) {
		this.m = m;
		this.PORT_RETOUR = 2700;
		this.nom = "localhost";
	}

	public ServiceRetour(Socket accept) {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		synchronized (this) {
			System.out.println("Voici la liste des livres empruntes : ");
			m.getLivresEmpruntes();
			
			System.out.println("Veillez saisir votre numero d'abonne :");
			
			Scanner sc = new Scanner(System.in);
			numAbonne = sc.nextInt();
			
			System.out.println("Veillez saisir le numero du livre � retourner :");
			
			Scanner scLivre = new Scanner(System.in);
			numLivre = scLivre.nextInt();
			
			if (m.estResa(numLivre, numAbonne) == true)
				m.annulerResa(numLivre, numAbonne);
			else
				try {
					m.getlivreEmprunter(numLivre).retour();
				} catch (RetourException e) {
					e.printStackTrace();
				} // Sinon l'abonne a rendu le livre normalement sans reservation
		}
	}

	@Override
	public String serviceName() {
		return nom;
	}

	@Override
	public int servicePort() {
		return PORT_RETOUR;
	}

	@Override
	public ServerSocket getServ() {
		return socket_ret;
	}
	
}
