package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Mediatheque.*;

public class ServiceRetour extends Service {
	private Mediatheque m;
	private String numLivre;
	private int numAbonne;
	private String nom;
	private ServerSocket socket_ret;
	private int PORT_RETOUR;
	
	public ServiceRetour(Mediatheque m, Socket s) {
		super(s);
		this.m = m;
		this.PORT_RETOUR = 2700;
		this.nom = "localhost";
	}

	@Override
	public void run() {		
		write("Veillez saisir le numero du livre a retourner :");
		
		//Scanner scLivre = new Scanner(System.in);
		numLivre = read();
		try {
			m.retourner(Integer.parseInt(numLivre));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		/*if (m.estResa(numLivre, numAbonne) == true)
			m.annulerResa(numLivre, numAbonne);
		else
			try {
				m.getlivreEmprunter(numLivre).retour();
			} catch (RetourException e) {
				e.printStackTrace();
			}*/ // Sinon l'abonne a rendu le livre normalement sans reservation
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
