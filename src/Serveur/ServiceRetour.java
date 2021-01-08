package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Mediatheque.*;

public class ServiceRetour extends Service {
	private String numLivre;
	private int numAbonne;
	private String nom;
	private ServerSocket socket_ret;
	private int PORT_RETOUR;
	
	public ServiceRetour(Socket s) {
		super(s);
		this.PORT_RETOUR = 5000;
		this.nom = "localhost";
	}
	
	public void exec() throws IOException {
		this.write("Bienvenue sur le service de retour de la mediatheque " );
		//this.write(Mediatheque.getInstance().getDocDisponibles());		
		this.write("Tapez le numero du document que vous souhaitez retourner");
		this.ask();
		String refDoc = this.read();
		while (!this.isNumeric(refDoc) || !Mediatheque.getInstance().docExistant(Integer.valueOf(refDoc))) {
			this.write("Veuillez saisir un numero de document valable et existant");
			this.ask();
			refDoc = this.read();
			if (refDoc.equals("end")) {
				this.end();
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return;
			}
		} 
		int numDoc = Integer.valueOf(refDoc);
		System.out.println("chargement du documment " + numDoc);
		
		try {
			Mediatheque.getInstance().retourner(numDoc);;
			this.write("Votre retour a ete effectuer avec succes !");
		} catch (Exception e1) {
			e1.printStackTrace();
			this.end();
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	
	
		this.end();
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
