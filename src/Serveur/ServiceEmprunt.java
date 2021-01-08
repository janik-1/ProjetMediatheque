package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Exceptions.EmpruntException;
import Mediatheque.*;

public class ServiceEmprunt extends Service {
	private int PORT_EMPRUNT;
	private String nom;
	private ServerSocket socket_emp;
	
	public ServiceEmprunt(Socket accept) {
		super(accept);
		this.PORT_EMPRUNT = 4000;
		this.nom = "localhost";
	}
	
	@Override
	public void exec() throws IOException {
			this.write("Bienvenue sur le service d'emprunt de la mediatheque " );
			this.write(Mediatheque.getInstance().getDocDisponibles());
			this.write("Saisisez le numero d'abonne");
			String refAbo = "";
	
			do {
				this.write("Entrez un numero d'abonne valide");
				this.ask();
				refAbo = this.read();
				if (refAbo.equals("end")) {
					this.end();
					try {
						this.finalize();
					} catch (Throwable e) {
						e.printStackTrace();
					}
					return;
				}				
			} while (!this.isNumeric(refAbo) || !Mediatheque.getInstance().aboExistant(Integer.valueOf(refAbo)));

			int numAb = Integer.valueOf(refAbo);
			System.out.println("Connection de l'abonne " + numAb);
			this.write("Tapez le numero du document que vous souhaitez enpruntez");
			this.ask();
			String refDoc = this.read();
			while ( (!this.isNumeric(refDoc) || !Mediatheque.getInstance().docExistant(Integer.valueOf(refDoc)))
					|| !Mediatheque.getInstance().getDocByNum(Integer.valueOf(refDoc)).verifReservation(numAb))  
				  	 {
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
				Mediatheque.getInstance().emprunter(numDoc, numAb);
				this.write("Votre Emprunt a ete effectue avec succes !");
				this.end();
			} catch (EmpruntException e1) {
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
		return PORT_EMPRUNT ;
	}
	
	public ServerSocket getServ() {
		return socket_emp;
	}

}
