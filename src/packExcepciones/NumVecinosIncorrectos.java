package packExcepciones;

public class NumVecinosIncorrectos extends Exception {

	public NumVecinosIncorrectos() {

	}

	public NumVecinosIncorrectos(String arg0) {
		super(arg0);
	}

	public NumVecinosIncorrectos(Throwable cause) {
		super(cause);
	}

	public NumVecinosIncorrectos(String message, Throwable cause) {
		super(message, cause);
	}

	public NumVecinosIncorrectos(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}