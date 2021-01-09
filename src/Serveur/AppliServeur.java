package Serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class AppliServeur implements Runnable {
	private ServerSocket listen_socket;
	private final static int PORT_RESERVATION = 3000, PORT_EMPRUNT = 4000, PORT_RETOUR = 5000;

	public AppliServeur(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	// Le serveur ecoute et accepte les connexions.
	// pour chaque connexion, il cree un ServiceInversion,
	// qui va la traiter, et le lance
	public void run() {
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			while (true) {
				if (listen_socket.getLocalPort() == PORT_RESERVATION) {
					new Thread(new ServiceReservation(listen_socket.accept())).start();
				} else if (listen_socket.getLocalPort() == PORT_EMPRUNT) {
					new Thread(new ServiceEmprunt(listen_socket.accept())).start();
				} else if (listen_socket.getLocalPort() == PORT_RETOUR) {
					new Thread(new ServiceRetour(listen_socket.accept())).start();
				}
			}
		} catch (IOException e) {
			try {
				this.listen_socket.close();
			} catch (IOException e1) {
			}
			System.err.println("Arrêt du serveur au port " + this.listen_socket.getLocalPort());
		}
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
	}

	// restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {
			this.listen_socket.close();
		} catch (IOException e1) {
		}
	}
}
