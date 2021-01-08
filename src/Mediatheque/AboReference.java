package Mediatheque;

public class AboReference implements Abonne {
	private static int cptNum = 1;
	private int NumAb;
	private String Nom;
	private String Date;
	private boolean Mediatheque;
	private boolean bannis;
	
	public AboReference (String Nom, String Date) {
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
	public String getDate() {
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

	@Override
	public void reserver(Document d) {
		//d.reservationPour(this);
	}
	

}
