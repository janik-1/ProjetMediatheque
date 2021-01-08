package Mediatheque;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;

public class Mediatheque {
	private static Mediatheque instance;
	private List<DocumentAbstrait> catalogue;
	private List<Abonne> ListeAbo;
	private Timer timer;

	public Mediatheque() {
		this.catalogue = new ArrayList<DocumentAbstrait>();
		this.ListeAbo = new ArrayList<Abonne>();	
	}
	
	public void ajoutDoc(DocumentAbstrait doc) {
			this.catalogue.add(doc);	
	}
	
	public void ajoutAbonne(Abonne abo) {
		this.ListeAbo.add(abo);
	}
	
	public DocumentAbstrait getDocByNum(int numDocument) {	
		synchronized(catalogue) {
			for (DocumentAbstrait d:catalogue) {
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
	
	public boolean aboExistant(int num) {
		if (this.getAbonneByNum(num) == null)
			return false;
		else
			return true;
	}
	
	public boolean docExistant(int num) {
		if (this.getDocByNum(num) == null)
			return false;
		else
			return true;
	}
	
	public String getDocDisponibles() {
		String s = "";
		for(DocumentAbstrait d:catalogue) {
			s+=d.getTitre() + System.lineSeparator();
		}
		return s;
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
	
	public static Mediatheque getInstance() {
		if (instance == null)
			instance = new Mediatheque();
		return instance;
	}
	
	public List<DocumentAbstrait> getCatalogue(){
		return this.catalogue;
	}
	
	public void addTest() {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		String date = "2016-08-16";
		LocalDate localDate = LocalDate.parse(date);
		//System.out.println("France: " + df.format(date));
//		formatter = formatter.withLocale(Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
//		LocalDate date = LocalDate.parse("2010-NOV-12", formatter);
		DVD n1 = new DVD("n1",17);
		DVD n2 = new DVD("n2",12);
		DVD n3 = new DVD("n3",19);
		DVD n4 = new DVD("n4",20);
		DVD n5 = new DVD("n5",10);
		DVD n6 = new DVD("n6",5);
		AboReference abo1 = new AboReference("Aoba", localDate);
		this.ajoutDoc(n1);
		this.ajoutDoc(n2);
		this.ajoutDoc(n3);
		this.ajoutDoc(n4);
		this.ajoutDoc(n5);
		this.ajoutDoc(n6);
		this.ajoutAbonne(abo1);
	}

	public boolean estEmprunt(int numLivre) {
		return (this.getDocByNum(numLivre).estEmprunte());
	}

	public void annulerResa(int numLivre) {
			this.getDocByNum(numLivre).annulerResa();
	}
	
	public void schedule(TimerTask task, long delay) {
		System.out.println("Schedule task");
		this.timer.schedule(task, delay);
	}
	
	
}
