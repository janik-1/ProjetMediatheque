//package Serveur;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ServiceLauncher implements Runnable {
//	// Demarrage d'un service
//	private ServerSocket serverSocket;
//	private Service service;
//	
//	public ServiceLauncher(ServerSocket socket, Service service) {
//		this.serverSocket = socket;
//		this.service = service;
//	}
//
//	@Override
//	public void run() {
//		while (true) {
//			try {
//				Socket client = serverSocket.accept();
//				this.service.setSocket(client);
//				new Thread(this.service).start();
//				System.out.println("fait");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	 // restituer les ressources --> finalize
////	protected void finalize() throws Throwable {
////		try {
////			this.listen_socket.close();
////		} catch (IOException e1) {
////			
////		}
////	}
//}
//
