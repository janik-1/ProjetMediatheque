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
		super(accept,m);
		this.PORT_EMPRUNT = 4000;
		this.nom = "localhost";
	}
	
	@Override
	public void exec() throws IOException {
		String line;
		
		this.write("Bienvenue sur le service d'emprunt de la bibliothèque " );
		
		this.write("Saisisez le numero d'abonne");
		this.ask();
		line = this.read();
		while (!this.aboCheck(line) ) {
			this.write("Veuillez saisir un numero d'abonne valable et existant");
			this.ask();
			line = this.read();
		}
		int numAb = Integer.valueOf(line);
		System.out.println("Connection de l'abonne " + numAb);
		this.write("Tapez le numero du document que vous souhaitez enpruntez");
		this.ask();
		line = this.read();
		while(!this.docNumEtExistant(line)) {
			this.write("Veuillez saisir un numero de document valable et existant");
			this.ask();
			line = this.read();
		}
		
		int numDoc = Integer.valueOf(line);
		System.out.println("chargement du documment " + numDoc);

		try {
			this.m.emprunter(numDoc, numAb);
		} catch (EmpruntException e1) {
			e1.printStackTrace();
		}
	
	
		this.end();
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	

//	@Override
//	public void run() {
//		System.out.println("plzzzz");
//		String reponse = null;
//		try {
//			//System.out.println("azza");
//			BufferedReader sin = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
//			PrintWriter sout = new PrintWriter(this.getSocket().getOutputStream(), true);
//			System.out.println(sin.readLine());
//			
//			sout.println("Tapez le numéro d'abonne ");
//			System.out.println("Tapez le numéro d'abonne 2");
//			String line = sin.readLine();
//			int numAb= Integer.valueOf(line);
//			//sout.println("Votre numero d'abonne est " + numAb);
//			System.out.println("Votre numero d'abonne est " + numAb);
//			sout.println("Tapez le numero du document que vous souhaitez enpruntez");
//			line = sin.readLine();
//			try {
//				m.emprunter(Integer.valueOf(line), numAb);
//				System.out.println("succes de votre enprunt");
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (EmpruntException e) {
//				e.printStackTrace();
//			}
//			sout.println("end");
//			
//			
//			
//		} catch (IOException e) {
//			System.out.println("Une erreur est survenueeee");
//		}
//
//		try {
//			this.getSocket().close();
//		} catch (IOException e2) {
//		}
//		System.out.println("Voici la liste des livres disponibles : ");
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		
////		m.getLivresDisponibles();
////		System.out.println("Veillez saisir votre numero d'abonne :");			
////		Scanner sc = new Scanner(System.in);
////		numAbonne = sc.nextInt();			
////		System.out.println("Veillez saisir le livre que vous voulez emprunter");			
////		Scanner scLivre = new Scanner(System.in);
////		numLivre = scLivre.nextInt();	
////		
////		try {
////			m.emprunter(numLivre, numAbonne);
////		} catch (EmpruntException e) {}
//	}

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
