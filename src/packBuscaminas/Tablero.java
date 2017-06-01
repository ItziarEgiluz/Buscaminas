package packBuscaminas;

import java.util.*;

import packExcepciones.ColumnaIncorrecta;
import packExcepciones.FilaIncorrecta;
import packExcepciones.NivelIncorrecto;
import packInterfaz.VentanaRanking;

public class Tablero extends Observable{
	
	private int tiempo;
	private boolean partidaGanada;
	private boolean finalDeLaPartida;
	private MatrizCasillas matrizCasillas;
	private static Tablero mTablero = new Tablero();
	private Nivel nivel = Nivel.NIVEL1;
	
	//Timer y timertask para que se actulicen los segundos
	private Timer timer;
	private TimerTask timerTask = new TimerTask(){
		public void run(){
			actualizarSegundos();
		}
	};
	// Patron SINGLETON
	private Tablero(){
		tiempo = 0;
		partidaGanada = false;
		finalDeLaPartida = false;
	}
	
	public static Tablero getInstance(){	
		
		if ( mTablero == null ){
			mTablero = new Tablero();
		}
		return mTablero;
	}
	
	public Nivel getNivel(){
		return nivel;
	}
	
	public void setNivel(Nivel pNivel) {
		this.nivel = pNivel;
	}

	public void inicializarJuego(Nivel pNivel) throws NivelIncorrecto {
		if( pNivel == null ){
			throw new NivelIncorrecto();
		}
		else{
			nivel = pNivel;
		}
		int filas = nivel.getFilas();
		int columnas = nivel.getColumnas();
		int minas = nivel.getMinas();
		
		tiempo = 0;
		matrizCasillas = new MatrizCasillas( filas, columnas , minas );
		
		partidaGanada = false;
		finalDeLaPartida = false;
		
		if ( timer == null ){
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 1000, 1000);
		}
		setChanged();		
		notifyObservers("INICIO");
	}
	
	public boolean finalDeLaPartida(){
		
		return finalDeLaPartida;
	}
	
	public boolean partidaGanada(){
		
		return partidaGanada;
	}

	public int casillasAbiertas(){
		
		int casillasAbiertas = 0;
		int filas = nivel.getFilas();
		int cols = nivel.getColumnas();
		
		for(int f = 0 ; f < filas ; f++ ){
			for(int c = 0 ; c < cols ; c++ ){
				if(matrizCasillas.consultarAbierta(f, c)){
					casillasAbiertas++;
				}
			}
		}
		return casillasAbiertas;

	}
	
	public boolean casillaConMina(int pFila, int pCol) throws FilaIncorrecta, ColumnaIncorrecta {
		if( pFila < 0 || pFila > nivel.getFilas() ){
			throw new FilaIncorrecta();
		}
		if( pCol < 0 || pCol > nivel.getColumnas() ){
			throw new ColumnaIncorrecta();
		}
		return matrizCasillas.casillaConMina(pFila, pCol);
	}
	
	public int minasAlrededor(int pFila, int pColumna){
		
		int minasAlrededor = -1;
		
		minasAlrededor = matrizCasillas.minasAlrededor(pFila, pColumna);
		
		return minasAlrededor;
	}	

	public void clickDerecho(int pFila, int pColumna ){
		
		if ( !matrizCasillas.consultarAbierta(pFila, pColumna)
				&& !matrizCasillas.consultarMarcada(pFila, pColumna)
				&&  casillasMarcadas() < nivel.getMinas() ){
			try{
				matrizCasillas.cambiarMarcada(pFila, pColumna);
			}
			catch( FilaIncorrecta e ){
				System.err.println("Las coordenas de la casilla son incorrectas");
				e.printStackTrace();
			}
			catch( ColumnaIncorrecta e ){
				System.err.println("Las coordenas de la casilla son incorrectas");
				e.printStackTrace();
			}
		}
		
		else if ( !matrizCasillas.consultarAbierta(pFila, pColumna)
				&& matrizCasillas.consultarMarcada(pFila, pColumna) ){
			
			try{
				matrizCasillas.cambiarMarcada(pFila, pColumna);
			}
			catch( FilaIncorrecta e ){
				System.err.println("Las coordenas de la casilla son incorrectas");
				e.printStackTrace();
			}
			catch( ColumnaIncorrecta e ){
				System.err.println("Las coordenas de la casilla son incorrectas");
				e.printStackTrace();
			}
		}
		
		setChanged();
		notifyObservers("CLICK");
	}
	
	public void clickIzquierdo(int pFila, int pColumna){
	
		if ( !matrizCasillas.consultarAbierta(pFila, pColumna)  && 
				!matrizCasillas.consultarMarcada(pFila, pColumna) ){
			if ( matrizCasillas.casillaConMina(pFila, pColumna)){
				
				try{
					matrizCasillas.cambiarAbierta(pFila, pColumna);
					tiempo = getTiempo();
					finalDeLaPartida = true;
					partidaGanada = false;
					
				}
				catch( FilaIncorrecta e ){
					System.err.println("Las coordenas de la casilla son incorrectas");
					e.printStackTrace();
				}
				catch( ColumnaIncorrecta e ){
					System.err.println("Las coordenas de la casilla son incorrectas");
					e.printStackTrace();
				}
				setChanged();
				notifyObservers();	
			}
			
			else {
				try{
					matrizCasillas.cambiarAbierta(pFila, pColumna);
					
				}
				catch( FilaIncorrecta e ){
					System.err.println("Las coordenas de la casilla son incorrectas");
					e.printStackTrace();
				}
				catch( ColumnaIncorrecta e ){
					System.err.println("Las coordenas de la casilla son incorrectas");
					e.printStackTrace();
				}
				
				if( matrizCasillas.minasAlrededor(pFila, pColumna) == 0 ){
					descubrirCasillas( pFila, pColumna );				
				}
				
				
				if ( casillasAbiertas() == ( nivel.getFilas() * nivel.getColumnas() - nivel.getMinas() ) ){
					tiempo = getTiempo();
					finalDeLaPartida = true;
					partidaGanada = true;
				}
			}
		}
		
		setChanged();					
		notifyObservers("CLICK");
		
		
		//Si ha acabado la partida, muestra directamente la ventana del ranking
		if( finalDeLaPartida )
		{
			VentanaRanking vr = new VentanaRanking();
			vr.setVisible(true);
		
		}
	}
	
	private int getTiempo() {
		return tiempo;
	}

	private void descubrirCasillas(int pFila, int pColumna){
		
		int filaIn = Math.max(0, pFila - 1);
		int filaFin = Math.min(nivel.getFilas() - 1, pFila + 1);
		
		int columIn = Math.max(0, pColumna - 1);
		int columnFin = Math.min(nivel.getColumnas() - 1, pColumna + 1);
		
		for (int i = filaIn; i <= filaFin; i++) {
			
			for (int j = columIn; j <= columnFin; j++) {
				// si es una CasillaNoMarcada, no estara abierta 
				if( !matrizCasillas.consultarMarcada(pFila, pColumna)){
					clickIzquierdo(i, j);
				}
				
			}
		}	

	}
	
	public int casillasMarcadas(){
		int cont = 0;
		for( int fila = 0 ; fila < nivel.getFilas(); fila++){
			for( int colum = 0 ; colum < nivel.getColumnas(); colum++ ){
				if ( matrizCasillas.consultarMarcada(fila, colum) ){
					cont++;
				}
			}
		}
		return cont;	
	}
	
	private void actualizarSegundos() {
		
		if( !finalDeLaPartida ){
			tiempo++;
			if (tiempo == 1000){
				tiempo = 0;
			}
			setChanged();
			notifyObservers();
		}
	}
	
	public int tiempoUtilizado(){
		
		return tiempo;
	}
	
	public boolean consultarAbierta(int pFila, int pColumna)throws FilaIncorrecta, ColumnaIncorrecta {
		if(pFila< 0 || pFila > nivel.getFilas()){
			throw new FilaIncorrecta();
		}
		if(pColumna< 0 || pColumna > nivel.getColumnas() ){
			throw new ColumnaIncorrecta();
		}
		return matrizCasillas.consultarAbierta(pFila, pColumna);
	}
	
	public boolean consultarMarcada(int pFila, int pColumna)throws FilaIncorrecta, ColumnaIncorrecta {
		if(pFila< 0 || pFila > nivel.getFilas()){
			throw new FilaIncorrecta();
		}
		if(pColumna< 0 || pColumna > nivel.getColumnas() ){
			throw new ColumnaIncorrecta();
		}
		return matrizCasillas.consultarMarcada(pFila, pColumna);
	}
	
	
	public void setChanged(){
		super.setChanged();
	}
	
	public void notifyObservers(){
		super.notifyObservers();
	}
}