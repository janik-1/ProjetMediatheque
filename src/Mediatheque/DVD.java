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
	
	public boolean isAdulte() {
		return this.adulte;
	}

}
