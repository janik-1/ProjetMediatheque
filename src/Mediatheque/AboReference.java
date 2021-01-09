package Mediatheque;

import java.time.LocalDate;

public class AboReference implements Abonne {
	private static int cptNum = 1;
	private static final int nbMoisBan = 1;
	private int NumAb;
	private String Nom;
	private LocalDate Date;
	private boolean Mediatheque;
	private boolean bannis;
	private LocalDate DateBanFin;
	
	public AboReference (String Nom, LocalDate Date) {
		this.NumAb = cptNum;
		this.Nom = Nom;
		this.Date = Date;
		this.bannis =false;
	}
	
	@Override
	public int getNumAb() {
		return this.NumAb;
	}
	@Override
	public String getNom() {
		return this.Nom;
	}
	@Override
	public LocalDate getDate() {
		return this.Date;
	}
	
	public void ArriveMedia() {
		this.Mediatheque = true;
	}
	
	public void partMedia() {
		this.Mediatheque = false;
	}
	
	public boolean getMedia() {
		return this.Mediatheque;
	}
	
	public boolean estMajeur() {
		return (this.Date.plusYears(16).isBefore(LocalDate.now()) );	
	}

	public boolean getbannis() {
		return this.bannis;
	}
	
	public void bannir() {
		this.bannis=true;
		this.DateBanFin = LocalDate.now().plusMonths(AboReference.nbMoisBan);
	}
	
	public boolean deban() {
		if(this.DateBanFin.isBefore(LocalDate.now())) {
			this.bannis = false;
			return true;
		}
		return false;
	}

	
	

}
