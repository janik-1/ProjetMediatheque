package Client;

import java.util.Scanner;

public class AppliBibliotheque {

	private static final Integer PORT_RESERVER = 3000;
	private static final Integer PORT_EMPRUNT = 4000;
	private static final Integer PORT_RETOUR = 5000;

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Que voulez vous faire ?");
		System.out.println("- Taper 1 pour acceder au service de reservation");
		System.out.println("- Taper 2 pour acceder au service d'emprunt");
		System.out.println("- Taper 3 pour acceder au service de retour");
		System.out.print("> ");
		
		Integer i = sc.nextInt();

		if (i == 1) {
			new Thread(new Client("localhost", PORT_RESERVER)).start();
		} else if (i == 2) {
			new Thread(new Client("localhost", PORT_EMPRUNT)).start();
		} else if (i == 3) {
			new Thread(new Client("localhost", PORT_RETOUR)).start();
		}else {
			System.err.println("Ce num�ro ne correspond a aucune action");
		}
	}

}
