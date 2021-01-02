package Serveur;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Mediatheque.*;

public class ServiceEmprunt extends Service {
	private Mediatheque m;
	private int numLivre;
	private int PORT_EMPRUNT;
	private String nom;
	private ServerSocket socket_emp;
	private int numAbonne;
	
	public ServiceEmprunt(Mediatheque m) {
		this.PORT_EMPRUNT = 2500;
		this.m = m;
		this.nom = "localhost";
	}
	
	public ServiceEmprunt(Socket accept) {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		synchronized (this) {
			System.out.println("Voici la liste des livres disponibles : ");
			m.getLivresDisponibles();
			System.out.println("Veillez saisir votre numero d'abonne :");
			
			Scanner sc = new Scanner(System.in);
			numAbonne = sc.nextInt();
			
			System.out.println("Veillez saisir le livre que vous voulez emprunter");
			
			Scanner scLivre = new Scanner(System.in);
			numLivre = scLivre.nextInt();
			try {
				m.getLivreDispo(numLivre).emprunter(m.getAbo(numAbonne));
			} catch (EmpruntException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String serviceName() {
		return nom;
	}

	@Override
	public int servicePort() {
		return PORT_EMPRUNT ;
	}
	
	public ServerSocket getServ() {
		return socket_emp;
	}

}
