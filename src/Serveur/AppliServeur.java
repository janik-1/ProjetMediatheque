package Serveur;

import java.io.IOException;
import java.net.ServerSocket;

import Mediatheque.*;

public class AppliServeur implements Runnable {
	private ServerSocket listen_socket;
	private final static int PORT_RESERVATION = 3000, PORT_EMPRUNT = 4000, PORT_RETOUR = 5000;
	private Mediatheque m;
	
	public AppliServeur(int port, Mediatheque m) throws IOException {
		listen_socket = new ServerSocket(port);
		this.m=m;
	}
	
//	public void connection(String[] args) {
//		Mediatheque m = new Mediatheque();
//		try {
//			ServerSocket socket_res = new ServerSocket(PORT_RESERVATION);
//			ServerSocket socket_emp = new ServerSocket(PORT_EMPRUNT);
//			ServerSocket socket_ret = new ServerSocket(PORT_RETOUR);
//			new Thread(new ServiceLauncher(socket_emp, new ServiceEmprunt(m))).start();
//			new Thread(new ServiceLauncher(socket_res, new ServiceReservation(m))).start();
//			new Thread(new ServiceLauncher(socket_ret, new ServiceRetour(m))).start();
//			
//			System.out.println("ok");
//			
//		} catch (IOException e) {
//			System.err.println("Pb lors de la creation du service : " +  e);			
//		}
//	}
	
	// Le serveur ecoute et accepte les connexions.
	// pour chaque connexion, il cree un ServiceInversion,
	// qui va la traiter, et le lance
	public void run() {
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			while (true) {
				if (listen_socket.getLocalPort()==PORT_RESERVATION) {
					new Thread(new ServiceReservation(m, listen_socket.accept())).start();
				}
				else if (listen_socket.getLocalPort()==PORT_EMPRUNT) {
					//System.out.println("zaazza");
					new Thread(new ServiceEmprunt(m, listen_socket.accept())).start();
				}
				else if (listen_socket.getLocalPort()==PORT_RETOUR) {
					new Thread(new ServiceRetour(m, listen_socket.accept())).start();
				}
				break;
			}
		} catch (IOException e) {
			try {
				this.listen_socket.close();
				System.out.println("erfhudcjnkz,erfuvfhedjz");
			} catch (IOException e1) {
			}
			System.err.println("Arrêt du serveur au port " + this.listen_socket.getLocalPort());
		}
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {
			this.listen_socket.close();
		} catch (IOException e1) {
		}
	}
}
