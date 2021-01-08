package Exceptions;

public class EmpruntException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmpruntException(String msg) {
		super(msg);
	}
    

	@Override
	public String toString() {
		return getMessage();
	}
}
