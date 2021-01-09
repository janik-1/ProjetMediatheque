package Exceptions;

public class RetourException extends Exception {
	private static final long serialVersionUID = 1L;

	public RetourException(String msg) {
		super(msg);
    }
	
	public String toString() {
		return getMessage();
	}
}
