package simulator.exceptions;

public class InvalidParametersException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidParametersException() {
		super();
	}

	public InvalidParametersException(String s) {
		super(s);
	}

}
