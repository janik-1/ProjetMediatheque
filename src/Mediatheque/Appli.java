package Mediatheque;

public class Appli {
	private final static int PORT = 3000;
	public static void main(String[] args) throws Exception {
			List<Cours> lesCours = initCours();
			ServiceCours.setLesCours(lesCours);
			
			new Thread(new ServeurCours(PORT)).start();
			System.out.println("Serveur lance sur le port " + PORT);
	}

}
