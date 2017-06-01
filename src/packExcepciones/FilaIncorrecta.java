package packExcepciones;

public class FilaIncorrecta extends Exception {
	
	public FilaIncorrecta() {

	}
	
	public FilaIncorrecta(String arg0) {
		super(arg0);
	}

	public FilaIncorrecta(Throwable arg0) {
		super(arg0);
	}

	public FilaIncorrecta(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FilaIncorrecta(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
