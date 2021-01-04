package Mediatheque;

import java.util.ArrayList;
import java.util.List;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;

public class Mediatheque {
	private List<Document> catalogue;
	private List<Abonne> ListeAbo;

	public Mediatheque() {
		this.catalogue = new ArrayList<Document>();
		this.ListeAbo = new ArrayList<Abonne>();	
	}
	
	public void ajoutDoc(Document doc) {
			this.catalogue.add(doc);	
	}
	
	public void ajoutAbonne(Abonne abo) {
		this.ListeAbo.add(abo);
	}
	
	public Document getDocByNum(int numDocument) {	
		synchronized(catalogue) {
			for (Document d:catalogue) {
				if (d.numero()==numDocument)
					return d;
			}
			return null;
		}
	}
	
	public Abonne getAbonneByNum(int numAbonne) {	
		for(Abonne a: ListeAbo) {
			if(a.getNumAb() == numAbonne)
				return a;
		}
		return null;
	}
	
	public void getLivresDisponibles() {
		for(Document d:catalogue) {
			System.out.println(d);
		}
	}
	
	public void retourner(int numDocument) {
		synchronized(this) {
			this.getDocByNum(numDocument).retour();
		}
	}
	
	public void reserver(int numDocument, int numAbonne) throws ReservationException {
		synchronized(this) {
			this.getDocByNum(numDocument).reservationPour(getAbonneByNum(numAbonne));
		} 
	}
	
	public void emprunter(int numDocument, int numAbonne) throws EmpruntException {
		synchronized(this) {
			this.getDocByNum(numDocument).empruntPar(getAbonneByNum(numAbonne));
		}
	}
	
	
}
