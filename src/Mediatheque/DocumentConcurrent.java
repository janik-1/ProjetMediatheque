package Mediatheque;

import Exceptions.EmpruntException;
import Exceptions.ReservationException;

public class DocumentConcurrent implements Document {
	private Document d;
	
	public DocumentConcurrent (Document Doc) {
		this.d = Doc;
	}

	@Override
	public int numero() {
		return 0;
	}

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		synchronized (this) {
			d.reservationPour(ab);
		}
	}

	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		
	}


}
