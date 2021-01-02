package Serveur;

import java.util.TimerTask;

import Mediatheque.*;

public class TaskReservation extends TimerTask {
	private Mediatheque m;
	private ServiceReservation a;
	
	public TaskReservation(Mediatheque m,ServiceReservation a) {
		this.m = m;
		this.a = a;
	}
	
	@Override
	public void run() {	
		if (m.estEmprunt(a.numLivre()) == false) {
			m.annulerResa(a.numLivre(), a.numAbo());
			System.out.println("reservation annulee");}
		else
			System.out.println("ok");
	}
}
