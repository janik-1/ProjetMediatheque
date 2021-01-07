package Mediatheque;

import java.util.Scanner;
import Serveur.*;

public class Appli {
	private final static int PORT_RESERVATION = 3000, PORT_EMPRUNT = 4000, PORT_RETOUR = 5000;
	public static void main(String[] args) throws Exception {
		int Port;
		//Scanner s = new Scanner();
		//Port=s.nextLine();
		Port=4000;
		
		new Thread(new AppliServeur(Port)).start();
		System.out.println("Serveur lance sur le port " + Port);
	}

}
