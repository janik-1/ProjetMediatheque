package Mediatheque;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;

public class DocumentConcurrent extends DocumentAbstrait{
	private Document d;
	
	public DocumentConcurrent (Document doc) {
		super(doc.numero());
		this.d = doc;
	}

	@Override
	public int numero() {
		return 0;
	}

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		synchronized (this) {
			while(!estDispo()) {
				try {
					this.wait();					
				}
				catch (InterruptedException e){
				}
				d.reservationPour(ab);
			}
		}
	}

	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		synchronized(this) {
			d.empruntPar(ab);
		}
	}

	@Override
	public void retour() {
		synchronized(this) {
			d.retour();
			this.notifyAll();
		}
	}


}
