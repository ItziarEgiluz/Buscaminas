package packBuscaminas;

import java.util.Random;

import packExcepciones.ColumnaIncorrecta;
import packExcepciones.FilaIncorrecta;
import packExcepciones.NumVecinosIncorrectos;

public class MatrizCasillas extends MatrizCasillasBuilder{

	private Casilla[][] matrizCasillas;

	public MatrizCasillas(int pFilas, int pColumnas, int pMinas) throws IllegalArgumentException{	
		if( (pFilas != 7 && pColumnas != 10 && pMinas != 10 )
		&& ( pFilas != 10 && pColumnas != 15 && pMinas != 30 )
		&& ( pFilas != 12 && pColumnas != 25 && pMinas != 75 ) ){
			throw new IllegalArgumentException();
		}
		else {
			matrizCasillas = new Casilla[pFilas][pColumnas];
			colocarMinas( pFilas, pColumnas, pMinas );		
			colocarNumeros( pFilas, pColumnas, pMinas );
		}
	}

	public void colocarMinas(int pFilas, int pColumnas, int pMinas) throws IllegalArgumentException{
		if((pFilas != 7 && pColumnas != 10 && pMinas != 10 )
		&&( pFilas != 10 && pColumnas != 15 && pMinas != 30 )
		&&( pFilas != 12 && pColumnas != 25 && pMinas != 75 ) ){
			throw new IllegalArgumentException();
		}
		else {
			int contadorDeMinas = 0;
			int fila,colum;
			//Mientras no tengo el numero de minas genero cordeenadas aleatoriamente
			// y si no hay casilla la creo y coloco las minas
			while( contadorDeMinas < pMinas ){
				fila = new Random().nextInt(pFilas);		
				colum = new Random().nextInt(pColumnas);
						
				if( matrizCasillas[fila][colum] == null ){
					matrizCasillas[fila][colum] = new Casilla();
					contadorDeMinas++;
				}
			}	
		}
	}

	public void colocarNumeros(int pFilas, int pColumnas, int pMinas) throws IllegalArgumentException {
		if( (pFilas != 7 && pColumnas != 10 && pMinas != 10 )
		&&( pFilas != 10 && pColumnas != 15 && pMinas != 30 )
		&&( pFilas != 12 && pColumnas != 25 && pMinas != 75 ) ){
			throw new IllegalArgumentException();
		}
		else {
			for(int i = 0 ; i < pFilas ; i++ ){
				for(int j = 0 ; j < pColumnas ; j++ ){
					if( matrizCasillas[i][j] == null ){
						
						int minas = minasAlrededor(i, j);
						try {
							matrizCasillas[i][j] = new Casilla( minas );
						} catch (NumVecinosIncorrectos e) {
							e.printStackTrace();
						}
					}
				}
			}	
		}
	}
	
	public boolean consultarAbierta(int pFila, int pColumna) {
		return matrizCasillas[pFila][pColumna].consultarAbierta();
		
	}
	public boolean consultarMarcada(int pFila, int pColumna) {
		return matrizCasillas[pFila][pColumna].consultarMarcada();
		
	}

	public void cambiarAbierta(int pFila , int pColumna) throws FilaIncorrecta, ColumnaIncorrecta {
		if( pFila < 0 || pFila > matrizCasillas.length ){
			throw new FilaIncorrecta();
		}
		if( pColumna < 0 || pColumna > matrizCasillas[0].length ){
			throw new ColumnaIncorrecta();
		}
		matrizCasillas[pFila][pColumna].cambiarAbierta();
		
	}
	public void cambiarMarcada(int pFila , int pColumna) throws FilaIncorrecta, ColumnaIncorrecta {
		if( pFila < 0 || pFila > matrizCasillas.length ){
			throw new FilaIncorrecta();
		}
		if( pColumna < 0 || pColumna > matrizCasillas[0].length ){
			throw new ColumnaIncorrecta();
		}
		matrizCasillas[pFila][pColumna].cambiarMarcada();
	}
	
	public int minasAlrededor(int pFila, int pColumna){		
	
		int minasAlrededor = 0;
		int filas = matrizCasillas.length;
		int cols = matrizCasillas[0].length;
		
		for( int f = pFila-1 ; f <= pFila + 1 ; f++ ){
			for( int c = pColumna-1 ; c <= pColumna + 1 ; c++ ){
				if( f >= 0 && c >= 0 && f < filas && c < cols
				&& matrizCasillas[f][c] != null
				&& matrizCasillas[f][c].casillaConMina()){
					minasAlrededor++;
				}
			}
		}
		return minasAlrededor;
		
	}

	public boolean casillaConMina( int pFila, int pColumna ){
		
		return matrizCasillas[pFila][pColumna].casillaConMina();
		
	}
}