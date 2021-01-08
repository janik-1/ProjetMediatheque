package Mediatheque;
import Exceptions.*;

public class DVD extends DocumentAbstrait{
	private boolean adulte;
	
	public DVD(String Titre, int age) {
		super(Titre);
		if (age > 16) {
			this.adulte = true;
		}
		
	}
	
	public boolean isAdulte() {
		return this.adulte;
	}

}
