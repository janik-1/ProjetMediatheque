package Mediatheque;
import Exceptions.*;

public class DVD extends DocumentAbstrait{
	private boolean adulte;
	
	public DVD(int NumeroDVD, String Titre, int age) {
		super(NumeroDVD, Titre);
		if (age > 16) {
			this.adulte = true;
		}
		
	}
	
	public boolean isAdulte() {
		return this.adulte;
	}
	
	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		this.setIndispo();
	}

	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		this.setEmprunteur(ab.getNumAb());
	}

	@Override
	public void retour() {
		this.setDispo();
		this.setEmprunteur(0);
	}
	

}
