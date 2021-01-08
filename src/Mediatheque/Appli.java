package Mediatheque;

import java.util.Scanner;
import Serveur.*;

public class Appli {
	private final static int PORT_RESERVATION = 3000, PORT_EMPRUNT = 4000, PORT_RETOUR = 5000;
	public static void main(String[] args) throws Exception {
//		Mediatheque m = new Mediatheque();
//		m.addTest();
//		m.getLivresDisponibles();
		int Port;
		//Scanner s = new Scanner();
		//Port=s.nextLine();
		Port=4000;
		
		Mediatheque m = new Mediatheque();
		Mediatheque.getInstance();
		Mediatheque.getInstance().addTest();
		
		new Thread(new AppliServeur(PORT_RESERVATION)).start();
		new Thread(new AppliServeur(PORT_EMPRUNT)).start();
		new Thread(new AppliServeur(PORT_RETOUR)).start();
		System.out.println("Serveur lance sur le port " + PORT_RETOUR + " " + PORT_EMPRUNT + " " + PORT_RESERVATION );
	}

}
