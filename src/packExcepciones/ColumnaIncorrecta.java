package packExcepciones;

public class ColumnaIncorrecta extends Exception {

	public ColumnaIncorrecta() {

	}

	public ColumnaIncorrecta(String arg0) {
		super(arg0);
	}

	public ColumnaIncorrecta(Throwable arg0) {
		super(arg0);
	}

	public ColumnaIncorrecta(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ColumnaIncorrecta(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}