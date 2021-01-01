package Mediatheque;

import java.util.ArrayList;
import java.util.List;

public class Mediatheque {
	private List<Document> catalogue;
	private List<Abonne> ListeAbo;

	public Mediatheque() {
		this.catalogue = new ArrayList<Document>();
		this.ListeAbo = new ArrayList<Abonne>();
		
	}
	
	public void ajouter(Document doc) {
		synchronized(catalogue){
			this.catalogue.add(doc);
		}
	}
	
	public void ajoutabo(Abonne abo) {
		this.ListeAbo.add(abo);
	}
	
	public Document LeDocdeNum(int numero) {
		synchronized(catalogue) {
			for (Document d:catalogue)
				if (d.numero()==numero)
					return d;
		}
		return null;
	}
}
