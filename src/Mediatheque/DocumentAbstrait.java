package Mediatheque;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;

public abstract class DocumentAbstrait implements Document {
	//private static int cptDoc;
	private int numDocument;
	private String titre;
	private int emprunteur;
	private boolean estDisponible;
	private boolean estReserve;
	private Abonne reservePar;
	
	public DocumentAbstrait(String titre) {
		//this.numDocument= cptDoc + 1;
		this.titre = titre;
		this.estDisponible = true;
		this.estReserve = false;
		this.emprunteur = -1;
		this.reservePar = null;
	}
	
	public DocumentAbstrait(int numDocument) {
		this.numDocument = numDocument;
		this.estDisponible = true;
	}
	
	@Override
	public int numero() {
		return this.numDocument;
	}
	
	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		if(this.estDisponible && !estReserve) {
			this.estReserve = true;
			this.reservePar = ab;
		}
	}
	
	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		this.setEmprunteur(ab.getNumAb());
		this.estReserve = false;		
	}
	
	@Override
	public void retour() {
		synchronized(this) {
			if(!this.estDisponible && this.emprunteur > 0) {
				this.estDisponible = true;
				this.emprunteur = -1;
			}		
		}
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public void setNum(int num) {
		this.numDocument = num;
	}
	
	public int getNum() {
		return this.numDocument;
	}
	
	public void setEmprunteur(int numAb) {
		this.emprunteur = numAb;
	}
	
	public int getNumEmprunteur() {
		return this.emprunteur;
	}
	
	public boolean estDispo() {
		return this.estDisponible;
	}
	
	public void setDispo() {
		this.estDisponible=true;
	}
	
	public void setIndispo() {
		this.estDisponible=false;
	}
	
	public Abonne getReservation() {
		return this.reservePar;
	}
	
}
