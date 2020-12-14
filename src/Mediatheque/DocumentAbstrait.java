package Mediatheque;


public abstract class DocumentAbstrait implements Document {
	private int NumeroDoc;
	private String Titre;
	private boolean Dispo;
	private int Emprunteur;
	
	public DocumentAbstrait(int NumeroDoc, String Titre) {
		this.NumeroDoc= NumeroDoc;
		this.Titre = Titre;
		this.Dispo=true;
	}
	
	@Override
	public int numero() {
		return this.NumeroDoc;
	}
	
	public String getTitre() {
		return this.Titre;
	}
	
	public void setEmprunteur(int numAb) {
		this.Emprunteur= numAb;
	}
	
	public int getEmprunteur() {
		return this.Emprunteur;
	}
	
	public boolean estDispo() {
		return this.Dispo;
	}
	
	public void setDispo() {
		this.Dispo=true;
	}
	
	public void setIndispo() {
		this.Dispo=false;
	}
}
