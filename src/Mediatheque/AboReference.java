package Mediatheque;

public class AboReference implements Abonne, Runnable {
	private static int cptNum = 1;
	private int NumAb;
	private String Nom;
	private String Date;
	private boolean Mediatheque;
	
	public AboReference (String Nom, String Date) {
		this.NumAb = cptNum;
		this.Nom = Nom;
		this.Date = Date;
		new Thread(this).start();
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
		this.Mediatheque =true;
	}
	
	public void partMedia() {
		this.Mediatheque=false;
	}
	
	public boolean getMedia() {
		return this.Mediatheque;
	}

	@Override
	public void run() {
		try {
			
		}catch (exception e) {
			
		}
	}
}
