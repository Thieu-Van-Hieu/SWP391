package exception.common;

public class MissingDataException extends RuntimeException {

	public MissingDataException() {
		super("Missing data");
	}
	
	public MissingDataException(String msg) {
		super(msg);
	}
	
}
