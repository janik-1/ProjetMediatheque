package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Exceptions.EmpruntException;
import Mediatheque.*;

public class ServiceEmprunt extends Service {
	private Mediatheque m;
	private int numLivre;
	private int PORT_EMPRUNT;
	private String nom;
	private ServerSocket socket_emp;
	private int numAbonne;
	
	public ServiceEmprunt(Mediatheque m, Socket accept) {
		super(accept);
		this.PORT_EMPRUNT = 2500;
		this.m = m;
		this.nom = "localhost";
	}
	

	@Override
	public void run() {
		System.out.println("plzzzz");
		String reponse = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(this.getSocket().getOutputStream(), true);
			out.println("Tapez le numéro de cours ");
		} catch (IOException e) {
			// Fin du service d'inversion
		}

		try {
			this.getSocket().close();
		} catch (IOException e2) {
		}
		System.out.println("Voici la liste des livres disponibles : ");
//		m.getLivresDisponibles();
//		System.out.println("Veillez saisir votre numero d'abonne :");			
//		Scanner sc = new Scanner(System.in);
//		numAbonne = sc.nextInt();			
//		System.out.println("Veillez saisir le livre que vous voulez emprunter");			
//		Scanner scLivre = new Scanner(System.in);
//		numLivre = scLivre.nextInt();	
//		
//		try {
//			m.emprunter(numLivre, numAbonne);
//		} catch (EmpruntException e) {}
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
