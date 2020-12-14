package Exceptions;

public class EmpruntException extends Exception {
	private static final long serialVersionUID = 1L;
	
    public EmpruntException(String message, Throwable cause) {
        super(message, cause);
    }
    
	@Override
	public String toString() {
		return "Desolé le document que vous essayez d'emprunter est indisponible";
	}
}
