package packBuscaminas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packExcepciones.ColumnaIncorrecta;
import packExcepciones.FilaIncorrecta;
import packExcepciones.NivelIncorrecto;

public class TableroTest {
	
	private final static int NUM_FILAS = Nivel.NIVEL1.getFilas();
	private final static int NUM_COLUMNAS = Nivel.NIVEL1.getColumnas();
	private final static int NUM_MINAS = Nivel.NIVEL1.getMinas();

	private Tablero tablero = null;
	private int[][] b1;
	private int nMinas;
	private static boolean tableroInicializado = false;

	@Before
	public void setUp() throws Exception {

		tablero = Tablero.getInstance();
		
		inicializar();
			
	}

	@After
	public void tearDown() throws Exception {
		tablero = null;
		b1 = null;
		tableroInicializado = false;
	}

	@Test
	public void testGetInstance() {
		
		Tablero tab = null;
		assertEquals( tab, null );
		
		assertEquals( tab.getInstance() , tablero.getInstance() );
	}

	@Test
	public void testGetNivel() {
		assertEquals( Nivel.NIVEL1, tablero.getNivel() );
	}

	@Test
	public void testSetNivel() {
		
		tablero.setNivel(Nivel.NIVEL2);
		assertEquals( tablero.getNivel(),Nivel.NIVEL2);
		
		tablero.setNivel(Nivel.NIVEL1);
		assertEquals( tablero.getNivel(),Nivel.NIVEL1);
	}

	@Test
	public void testFinalDeLaPartida() {

		inicializar();
		
		int i = 0, j = 0;
		boolean finJuego = false;
		
		assertFalse(tablero.finalDeLaPartida());
		
		// buscar una mina y descubrirla: final sin exito
		while (i < NUM_COLUMNAS && !finJuego) {
			
			while (j < NUM_COLUMNAS && !finJuego) {
				
				try {
					if ( tablero.casillaConMina(i, j) ) {
						tablero.clickIzquierdo(i, j);
						finJuego = true;
					}
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
				j++;
			}
			i++;
		}
		
		if (finJuego) {
			assertTrue(tablero.finalDeLaPartida());
		}
		
		else {
			assertFalse(tablero.finalDeLaPartida());
		}
	}

	@Test
	public void testPartidaGanada() {
		inicializar();

		int i = 0, j = 0;
		boolean finJuego = false;
		
		assertFalse(tablero.finalDeLaPartida());
		
		// marcar las minas para que acabe incorrectamente
		while (i < NUM_FILAS && !finJuego) {
			
			j = 0;
			
			while (j < NUM_COLUMNAS && !finJuego) {
				
				try {
					if (tablero.casillaConMina(i, j)) {
						
						tablero.clickIzquierdo(i, j);
						finJuego = true;
						
					}
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
				j++;
			}
			i++;
		}
		assertFalse(tablero.partidaGanada());

		// inicializar el juego
		inicializar();
		
		// marcar las minas para que acabe correctamente
		for (i = 0; i < NUM_FILAS && !tablero.finalDeLaPartida(); i++) {
			for (j = 0; j < NUM_COLUMNAS && !tablero.finalDeLaPartida(); j++) {
				
				try {
					if ( !tablero.casillaConMina(i, j) ) {
						tablero.clickIzquierdo(i, j);
					}
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
		}
		assertTrue(tablero.partidaGanada());
	}

	@Test
	public void testCasillasAbiertas() {
		
		inicializar();
		int cont = 0;
		for (int i = 0; i < NUM_FILAS; i++) {
			
			for (int j = 0; j < NUM_COLUMNAS; j++) {
				
				try {
					if( !tablero.consultarAbierta(i, j)){
						cont++;
					}
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
		}
		
		assertEquals( cont, NUM_FILAS*NUM_COLUMNAS);
		cont = 0;
		// abrimos casillas sin minas
		for (int i = 0; i < NUM_FILAS; i++) {
			
			for (int j = 0; j < NUM_COLUMNAS; j++) {
				
				try {
					if (!tablero.casillaConMina(i, j)) {
						tablero.clickIzquierdo(i, j);
						cont++;
						
					}
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
		}
		assertEquals( cont, NUM_FILAS*NUM_COLUMNAS - NUM_MINAS);
	}

	@Test
	public void testCasillaConMina() {
		
		assertEquals( tablero.getNivel().getMinas(), NUM_MINAS );
		
		// busca una mina
		int i = 0, j = 0;
		boolean parar = false;
		
		while ( i < NUM_FILAS && !parar ) {
			
			while ( j < NUM_COLUMNAS && !parar ) {
				
				if ( b1[i][j] == -1 ) {
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
			
			try {
				assertTrue(tablero.casillaConMina(i, j));
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
		
	}
	
	@Test
	public void testMinasAlrededor() {

		inicializar();
		
		int i = 0, j = 0;
		boolean parar = false;
		
		while ( i < NUM_FILAS && !parar ) {
			
			while ( j < NUM_COLUMNAS && !parar ) {
				
				try {
					if ( !tablero.casillaConMina(i, j) ) {
						parar = true;
					}
					else{
						j++;
					}
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
			if( !parar ){
				i++;
			}
		}

		assertEquals(calcVecinosConMina(tablero, i, j), tablero.minasAlrededor(i, j));
	}

	@Test
	public void testCasillasMarcadas() {
		
		inicializar();

		for (int i = 0; i < NUM_FILAS; i++) {
			
			for (int j = 0; j < NUM_COLUMNAS; j++) {
				
				try {
					assertFalse(tablero.consultarMarcada(i, j));
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
		}
		int cont = 0;
		for (int i = 0; i < NUM_FILAS; i++) {
			
			for (int j = 0; j < NUM_COLUMNAS; j++) {
				
				try {
					if ( !tablero.consultarMarcada(i, j) ) {
						
						tablero.clickDerecho(i, j);
						cont++;
					}
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
		}
		assertEquals( cont, NUM_FILAS*NUM_COLUMNAS);
	}

	@Test
	public void testConsultarAbierta() {
		
		// no hay ninguna casilla abierta

		try {
			assertFalse(tablero.consultarAbierta(0, 1));
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
			assertFalse(tablero.consultarAbierta(1, 1));
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
			assertFalse(tablero.consultarAbierta(2, 1));
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
			assertFalse(tablero.consultarAbierta(3, 1));
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
			assertFalse(tablero.consultarAbierta(4, 1));
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
			assertFalse(tablero.consultarAbierta(5, 1));
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
			assertFalse(tablero.consultarAbierta(6, 1));
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
		
		
		// abrimos las casillas
		
		tablero.clickIzquierdo(0, 1);
		try {
			assertTrue(tablero.consultarAbierta(0, 1));
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
	
		tablero.clickIzquierdo(1, 1);
		try {
			assertTrue(tablero.consultarAbierta(1, 1));
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
	
		tablero.clickIzquierdo(2, 1);
		try {
			assertTrue(tablero.consultarAbierta(2, 1));
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
	
		tablero.clickIzquierdo(3, 1);
		try {
			assertTrue(tablero.consultarAbierta(3, 1));
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
	
		tablero.clickIzquierdo(4, 1);
		try {
			assertTrue(tablero.consultarAbierta(4, 1));
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
	
		tablero.clickIzquierdo(5, 1);
		try {
			assertTrue(tablero.consultarAbierta(5, 1));
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
	
		tablero.clickIzquierdo(6, 1);
		try {
			assertTrue(tablero.consultarAbierta(6, 1));
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
		try {
			assertFalse(tablero.consultarMarcada(0, 2));
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
			assertFalse(tablero.consultarMarcada(1, 2));
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
			assertFalse(tablero.consultarMarcada(2, 2));
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
			assertFalse(tablero.consultarMarcada(3, 2));
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
			assertFalse(tablero.consultarMarcada(4, 2));
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
			assertFalse(tablero.consultarMarcada(5, 2));
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
			assertFalse(tablero.consultarMarcada(6, 2));
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
		
				
		// marcamos las casillas
		try {
			tablero.clickDerecho(0, 2);
			assertTrue(tablero.consultarMarcada(0, 2));
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
			tablero.clickDerecho(1, 2);
			assertTrue(tablero.consultarMarcada(1, 2));
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
			tablero.clickDerecho(2, 2);
			assertTrue(tablero.consultarMarcada(2, 2));
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
			tablero.clickDerecho(3, 2);
			assertTrue(tablero.consultarMarcada(3, 2));
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
			tablero.clickDerecho(4, 2);
			assertTrue(tablero.consultarMarcada(4, 2));
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
			tablero.clickDerecho(5, 2);
			assertTrue(tablero.consultarMarcada(5, 2));
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
			tablero.clickDerecho(6, 2);
			assertTrue(tablero.consultarMarcada(6, 2));
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
	
	private int calcVecinosConMina(Tablero pTablero, int pFila, int pColumna) {
		
		int cont = 0;
		int filaIn = Math.max(0, pFila - 1);
		int filaFin = Math.min(NUM_FILAS - 1, pFila + 1);
		int columIn = Math.max(0, pColumna - 1);
		int columnFin = Math.min(NUM_COLUMNAS - 1, pColumna + 1);
		
		for (int i = filaIn; i <= filaFin; i++) {
			
			for (int j = columIn; j <= columnFin; j++) {
				
				try {
					if ((i != pFila || j != pColumna)
							&& pTablero.casillaConMina(i, j)) {
						cont++;
					}
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
		}
		return cont;				
	}
	
	private int ponerMinas(int[][] pBusca) {
		
		int minas = 0;
		boolean hayMina = false;
		
		for (int i = 0; i < NUM_FILAS; i++) {
			
			for (int j = 0; j < NUM_COLUMNAS; j++) {
				
				try {
					hayMina = tablero.casillaConMina(i, j);
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
				
				if ( hayMina ) {
					pBusca[i][j] = -1;
					minas++;
				}
				else {
					pBusca[i][j] = calcVecinosConMina(tablero, i, j);
				}
			}
		}
		return minas;
		
	}
	private void inicializar() {
		
		tableroInicializado = true;
		
		try {
			tablero.inicializarJuego(Nivel.NIVEL1);
		}
		catch (NivelIncorrecto e) {
			System.out.println("El nivel introducido no es correcto");
		}
		 catch (Exception e) {
			fail("La excepcion que se lanza no es valida");
		}
		
		b1 = new int[NUM_FILAS][NUM_COLUMNAS];
		
		nMinas = ponerMinas(b1);
		
	}
}