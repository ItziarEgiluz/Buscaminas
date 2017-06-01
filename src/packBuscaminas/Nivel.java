package packBuscaminas;

public enum Nivel {
	//filas,columnas,minas,ancho y alto
	//NIVEL1(7,10,10,193,243), NIVEL2(10,15,30,273,290), NIVEL3(12,25,75,432,323);
	NIVEL1(7,10,10,193,253), NIVEL2(10,15,30,273,300), NIVEL3(12,25,75,432,332);
	
	private int filas;
	private int columnas;
	private int minas;
	private int ancho;
	private int alto;
		
	private Nivel(int pFilas , int pColumnas , int pMinas , int pAncho , int pAlto ){
		filas = pFilas;
		columnas = pColumnas;
		minas = pMinas;
		ancho = pAncho;
		alto = pAlto;
	}
	
	public int getFilas(){
		return filas;
	}
	
	public int getColumnas(){
		return columnas;
	}
	
	public int getMinas(){
		return minas;
	}	
	
	public int getAlto(){
		return alto;
	}
	
	public int getAncho(){
		return ancho;
	}	
}