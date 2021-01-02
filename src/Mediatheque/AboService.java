package Mediatheque;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.util.List;

public class AboService{
	private static List<AboReference> lesAbos;
	private final Socket client;
	
	public static void setLesAbos(List<AboReference> lesAbos) {
		AboService.lesAbos = lesAbos;
	}
	
	private static AboReference getAbo(int numAbo) {
		for (AboReference ab : lesAbos)
			if (ab.getNumAb() == numAbo)
				return ab;
		return null;
	}
	
	
	public AboService(Socket socket) {
		this.client = socket;
	}
	
	protected void finalize() throws Throwable {
		client.close();
	}

	@Override
	public String toString() {
		return "Inversion de texte";
	}
}
