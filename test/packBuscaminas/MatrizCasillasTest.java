package packBuscaminas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packExcepciones.ColumnaIncorrecta;
import packExcepciones.FilaIncorrecta;

public class MatrizCasillasTest {
	
	// creamos una copia del buscaminas para tener con que compararla despues
	private int[][] b1;
	private int nMinas1;
	
	private MatrizCasillas mc1; // nivel 1

	@Before
	public void setUp() throws Exception {

		mc1 = new MatrizCasillas( Nivel.NIVEL1.getFilas(), Nivel.NIVEL1.getColumnas(), Nivel.NIVEL1.getMinas() );
		b1 = new int[Nivel.NIVEL1.getFilas()][Nivel.NIVEL1.getColumnas()];
		nMinas1 = crearMatrizYContarMinas(mc1, b1);
		
	}

	@After
	public void tearDown() throws Exception {
		
		mc1 = null;

		b1 = null;
		
	}

	@Test
	public void testMatrizCasillas() {

		int cont = 0;
		for( int f = 0; f < Nivel.NIVEL1.getFilas(); f++ ){
			for( int c = 0; c < Nivel.NIVEL1.getColumnas(); c++){
				if( mc1.casillaConMina(f, c) ){
					cont++;
				}
			}
		}
		
		assertEquals( cont, Nivel.NIVEL1.getMinas() );
		
		// al crear la matriz, las casillas estan cerradas
		cont = 0;
		for( int f = 0; f < Nivel.NIVEL1.getFilas(); f++ ){
			for( int c = 0; c < Nivel.NIVEL1.getColumnas(); c++){
				
				// miramos si la casilla no esta abierta
				if( ! mc1.consultarAbierta(f, c) ){
					cont++;
				}
				
			}
		}
		
		assertEquals( cont, Nivel.NIVEL1.getFilas()*Nivel.NIVEL1.getColumnas() );
		
		assertEquals( Nivel.NIVEL1.getMinas() , nMinas1 );
		
	}

		
	@Test
	public void testConsultarAbierta() {
		
		// no hay ninguna casilla abierta

		assertFalse(mc1.consultarAbierta(0, 1));
		assertFalse(mc1.consultarAbierta(1, 1));
		assertFalse(mc1.consultarAbierta(2, 1));
		assertFalse(mc1.consultarAbierta(3, 1));
		assertFalse(mc1.consultarAbierta(4, 1));
		assertFalse(mc1.consultarAbierta(5, 1));
		assertFalse(mc1.consultarAbierta(6, 1));
		
		// abrimos las casillas
		try {
			mc1.cambiarAbierta(0, 1);
			assertTrue(mc1.consultarAbierta(0, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarAbierta(1, 1);
			assertTrue(mc1.consultarAbierta(1, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarAbierta(2, 1);
			assertTrue(mc1.consultarAbierta(2, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarAbierta(3, 1);
			assertTrue(mc1.consultarAbierta(3, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarAbierta(4, 1);
			assertTrue(mc1.consultarAbierta(4, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarAbierta(5, 1);
			assertTrue(mc1.consultarAbierta(5, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarAbierta(6, 1);
			assertTrue(mc1.consultarAbierta(6, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
	}
	
	@Test
	public void testConsultarMarcada() {
				
		// no hay ninguna casilla marcada
		assertFalse(mc1.consultarMarcada(0, 2));
		assertFalse(mc1.consultarMarcada(1, 2));
		assertFalse(mc1.consultarMarcada(2, 2));
		assertFalse(mc1.consultarMarcada(3, 2));
		assertFalse(mc1.consultarMarcada(4, 2));
		assertFalse(mc1.consultarMarcada(5, 2));
		assertFalse(mc1.consultarMarcada(6, 2));
		
		
		// marcamos las casillas
		
		try {
			mc1.cambiarMarcada(0, 2);
			assertTrue(mc1.consultarMarcada(0, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarMarcada(1, 2);
			assertTrue(mc1.consultarMarcada(1, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarMarcada(2, 2);
			assertTrue(mc1.consultarMarcada(2, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarMarcada(3, 2);
			assertTrue(mc1.consultarMarcada(3, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarMarcada(4, 2);
			assertTrue(mc1.consultarMarcada(4, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarMarcada(5, 2);
			assertTrue(mc1.consultarMarcada(5, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
		
		try {
			mc1.cambiarMarcada(6, 2);
			assertTrue(mc1.consultarMarcada(6, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
	}
	
	@Test
	public void testCambiarMarcada() {
		
		assertFalse(mc1.consultarMarcada(2, 2)); // esta no marcada la casilla
		
		try {
			mc1.cambiarMarcada(2, 2); // marco casilla[2,2]
			assertTrue(mc1.consultarMarcada(2, 2));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
		
	}
	
	@Test
	public void testCambiarAbierta() {

		assertFalse(mc1.consultarAbierta(1, 1)); // esta cerrada la casilla
				
		try {
			mc1.cambiarAbierta(1, 1); // abro casilla[1,1]
			assertTrue(mc1.consultarAbierta(1, 1));
		}
		catch (FilaIncorrecta e) {
			System.out.println("Fila incorrecta");
		}
		catch (ColumnaIncorrecta e) {
			System.out.println("Columna incorrecta");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza no es valida");
		}
	
	}

	@Test
	public void testMinasAlrededor() {

		int i = 0, j = 0;
		int numVecMinas = 0;
		int supNumVecMinas = 0; //supuestas minas alrededor
		
		boolean casMina = false;
		
		while ( i < Nivel.NIVEL1.getFilas() && !casMina ) {
			
			while ( j < Nivel.NIVEL1.getColumnas() && !casMina ) {
				
				if ( !mc1.casillaConMina(i, j) ) {
					
					numVecMinas = calcVecinosConMina( mc1, i, j );
					
					supNumVecMinas = mc1.minasAlrededor(i, j);

					casMina = true;
				}
				else{
					j++;
				}
			}
			if( !casMina ){
				i++;
			}
			
		}
		if( casMina ){
			assertEquals( numVecMinas , supNumVecMinas );
		}
		
	}
	
	@Test
	public void testCasillaConMina() {
		
		int i = 0, j = 0;
		boolean parar = false;
		
		while ( i < Nivel.NIVEL1.getFilas() && !parar ) {
			
			while ( j < Nivel.NIVEL1.getColumnas() && !parar ) {
				
				if (b1[i][j] == -1) {
					parar = true;	
				}
				else {
					j++;
				}
			}
			if( !parar ){
				i++;
			}
		}
		if( parar ){
			assertTrue(mc1.casillaConMina(i, j));
		}
		
	}
	
	private int calcVecinosConMina(MatrizCasillas pMatriz, int pFila, int pColumna) {
		
		int cont = 0;
		int filaIn = Math.max(0, pFila - 1);
		int filaFin = Math.min(Nivel.NIVEL1.getFilas() - 1, pFila + 1);
		int columIn = Math.max(0, pColumna - 1);
		int columnFin = Math.min(Nivel.NIVEL1.getColumnas() - 1, pColumna + 1);
		
		for (int i = filaIn; i <= filaFin; i++) {
			
			for (int j = columIn; j <= columnFin; j++) {
				
				if ( (i != pFila || j != pColumna)
					&& pMatriz.casillaConMina(i, j) ) {
					cont++;
				}
			}
		}
		return cont;
	}
	
	private int crearMatrizYContarMinas(MatrizCasillas pMatriz, int[][] pBusca) {
		
		int minas = 0;
		
		for (int i = 0; i < Nivel.NIVEL1.getFilas(); i++) {
			
			for (int j = 0; j < Nivel.NIVEL1.getColumnas(); j++) {
				
				if ( pMatriz.casillaConMina(i, j) ) {
					minas++;
					pBusca[i][j] = -1;
				}
				else {
					pBusca[i][j] = calcVecinosConMina(pMatriz, i, j);
				}
			}
		}
		return minas;
	}
}