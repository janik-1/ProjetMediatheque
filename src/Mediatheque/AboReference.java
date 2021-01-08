package Mediatheque;

import java.time.LocalDate;

public class AboReference implements Abonne {
	private static int cptNum = 1;
	private int NumAb;
	private String Nom;
	private LocalDate Date;
	private boolean Mediatheque;
	private boolean bannis;
	
	public AboReference (String Nom, LocalDate Date) {
		this.NumAb = cptNum;
		this.Nom = Nom;
		this.Date = Date;
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

	@Override
	public void reserver(Document d) {
		//d.reservationPour(this);
	}
	

}
