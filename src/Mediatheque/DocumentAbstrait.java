package Mediatheque;

import java.time.LocalDate;
import java.time.LocalTime;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;

public abstract class DocumentAbstrait implements Document {
	private static final long tpsResEnHeure = 2;
	private static final long RetardMax = 14;
	//private static int cptDoc;
	private int numDocument;
	private String titre;
	private int emprunteur;
	private boolean estDisponible;
	private boolean estReserve;
	private boolean estEmprunte;
	private int reservePar;
	private boolean estDegrade;
	private LocalTime finReservation;
	private LocalDate DateEmprunt;
	
	public DocumentAbstrait(String titre) {
		this.titre = titre;
		this.estDisponible = true;
		this.estReserve = false;
		this.estEmprunte=false;
		this.emprunteur = -1;
		this.reservePar = -1;
		this.estDegrade = false;
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
		if (!this.estDisponible) {
			throw new ReservationException("Le document n'est pas disponible");
		}
		if (this.estReserve) {
			throw new ReservationException("Ce document est reserve jusqu'a " + this.finReservation.withNano(0).toString());
		}
		if(this.estDisponible && !estReserve) {
			this.estReserve = true;
			this.reservePar = ab.getNumAb();
			this.finReservation= LocalTime.now().plusHours(tpsResEnHeure);  
		}
	}
	
	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
			if (!this.estDisponible)
				throw new EmpruntException("Ce Document n'est pas disponible");		
			if(ab.getbannis() && !ab.deban()) {
				throw new EmpruntException("Désolé, vous êtes bannis");
			}
			this.emprunteur = ab.getNumAb();
			this.estDisponible = false;
			this.estReserve = false;
			this.estEmprunte = true;
			this.reservePar = -1;
			this.DateEmprunt = LocalDate.now();
	}
	
	@Override
	public void retour(){
		if(!this.estDisponible && this.emprunteur > 0) {
			this.estDisponible = true;
			this.emprunteur = -1;
			this.estEmprunte = false;
			this.estReserve = false;
			this.reservePar = -1;
		}
	}
	
	public boolean estEmprunte() {
		return this.estEmprunte;
	}
	
	public boolean estReserve() {
		return this.estReserve;
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
	
	public int getReservation() {
		return this.reservePar;
	}
	
	public boolean verifReservation(int numa) {
		return (this.reservePar == numa);
	}
	
	public void annulerResa() {
		this.estReserve=false;
		this.reservePar=-1;
	}
	
	public boolean estDegrade() {
		return this.estDegrade;
	}
	
	public void setdegrade(boolean etat) {
		this.estDegrade=etat;
	}
	
	public boolean estUnGrosRetard() {
		return ((this.DateEmprunt.plusDays(RetardMax).isAfter(LocalDate.now())));
	}
	
	public boolean checkBannir() {
		return(this.estUnGrosRetard() || this.estDegrade);
	}
	
}
