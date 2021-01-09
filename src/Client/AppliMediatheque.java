package Client;

import java.util.Scanner;

public class AppliMediatheque {

	private static final Integer PORT_RESERVER = 3000;
	private static final Integer PORT_EMPRUNT = 4000;
	private static final Integer PORT_RETOUR = 5000;

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Taper 1 pour reserver");
		System.out.println("Taper 2 pour emprunter");
		System.out.println("Taper 3 pour retourner");
		
		Integer i = sc.nextInt();

		if (i == 1) {
			new Thread(new Client("localhost", PORT_RESERVER)).start();
		} else if (i == 2) {
			new Thread(new Client("localhost", PORT_EMPRUNT)).start();
		} else if (i == 3) {
			new Thread(new Client("localhost", PORT_RETOUR)).start();
		}else {
			System.out.println("Ce numéro ne correspond a aucune action");
		}
	}

}
