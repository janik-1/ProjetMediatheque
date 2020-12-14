package Mediatheque;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.util.List;

public class AboService implements Runnable {
	private static List<AboReference> lesAbos;
	
	public static void setLesAbos(List<AboReference> lesAbos) {
		AboService.lesAbos = lesAbos;
	}
	
	private static AboReference getAbo(int numAbo) {
		for (AboReference ab : lesAbos)
			if (ab.getNumAb() == numAbo)
				return ab;
		return null;
	}
	
	private final Socket client;
	
	public AboService(Socket socket) {
		this.client = socket;
	}

	@Override
	public void run() {
		String reponse = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);

			out.println("Tapez le numero d'abonne ");
			int noabo = Integer.parseInt(in.readLine());
			out.println("Tapez le nombre de places souhaites ");
			int nbrePlaces = Integer.parseInt(in.readLine());

			System.out.println("Requete de " + this.client.getInetAddress() + " Cours nｰ" + noabo + " Nbre de places "
					+ nbrePlaces);
			AboReference abo = getAbo(noabo);
			if (abo != null)
				// la ressource concurrente est le cours
				synchronized (abo) {
					try {
						abo.inscription(nbrePlaces);
						reponse = "Inscription(s) ressie(s)";
					} catch (PasAssezDePlacesException e) {
						reponse = e.toString();
					}
				}
			else
				reponse = "Aucun cours ne porte ce num駻o";
			//System.out.println(reponse);
			out.println(reponse);
		} catch (IOException e) {
			// Fin du service d'inversion
		}

		try {
			client.close();
		} catch (IOException e2) {
		}
		
	}
	
	
	protected void finalize() throws Throwable {
		client.close();
	}

	@Override
	public String toString() {
		return "Inversion de texte";
	}
}
