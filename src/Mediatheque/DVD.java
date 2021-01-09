package Mediatheque;
import Exceptions.*;

public class DVD extends DocumentAbstrait{
	private static int cptDVD = 1;
	private boolean adulte;
	
	public DVD(String Titre, int age) {
		super(Titre);
		this.setNum(cptDVD);
		if (age > 16) {
			this.adulte = true;
		}
		cptDVD++;
	}
	
	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		if (this.adulte && !ab.estMajeur())
			throw new EmpruntException("vous n’avez pas l’âge pour emprunter ce DVD");	
		super.empruntPar(ab);		
	}
	
	public boolean isAdulte() {
		return this.adulte;
	}

}
