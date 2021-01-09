package Mediatheque;

import java.time.LocalDate;

public interface Abonne {
	public int getNumAb();
	public String getNom();
	public LocalDate getDate();
	public boolean estMajeur();
	public boolean getbannis();
	public void bannir();
	public boolean deban();
}