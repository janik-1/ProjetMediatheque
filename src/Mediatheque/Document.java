package Mediatheque;
import Exceptions.*;

public interface Document {
	int numero();
	void reservationPour(Abonne ab) throws ReservationException ;
	void empruntPar(Abonne ab) throws EmpruntException;
	// retour document ou annulation r�servation
	void retour();
}
