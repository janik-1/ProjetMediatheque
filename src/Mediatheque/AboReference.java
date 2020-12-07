package Mediatheque;

public class AboReference implements Abonne {
	private int NumAb;
	private String Nom;
	private String Date;
	private boolean Mediatheque;
	
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
		this.Mediatheque =true;
	}
	
	public void partMedia() {
		this.Mediatheque=false;
	}
	
	public boolean getMedia() {
		return this.Mediatheque;
	}
}
