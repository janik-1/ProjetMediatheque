package Serveur;

import java.util.TimerTask;

import Mediatheque.*;

public class TaskReservation extends TimerTask {
	private ServiceReservation a;
	
	public TaskReservation(ServiceReservation a) {
		this.a = a;
	}
	
	@Override
	public void run() {	
		if (Mediatheque.getInstance().estEmprunt(a.numDoc()) == false) {
			Mediatheque.getInstance().annulerResa(a.numDoc());
			System.out.println("reservation annulee");}
		else
			System.out.println("ok");
	}
}
