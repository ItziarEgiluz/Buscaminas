package packBuscaminas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packExcepciones.NumVecinosIncorrectos;

public class CasillaTest {
	
	private Casilla c0, c1, c2, c3, c4, c5, c6, c7, c8, c9;

	@Before
	public void setUp() throws Exception {
		
		c0 = new Casilla(0); // casilla sin mina, se le dice las minas alrededor, en este caso, 0
		c1 = new Casilla(1); // casilla sin mina, se le dice las minas alrededor, en este caso, 1
		c2 = new Casilla(2);
		c3 = new Casilla(3);
		c4 = new Casilla(4);
		c5 = new Casilla(5);
		c6 = new Casilla(6);
		c7 = new Casilla(7);
		c8 = new Casilla(8);
		c9 = new Casilla(); // es una casilla con mina
	}

	@After
	public void tearDown() throws Exception {
		c0 = null;
		c1 = null;
		c2 = null;
		c3 = null;
		c4 = null;
		c5 = null;
		c6 = null;
		c7 = null;
		c8 = null;
		c9 = null;
	}

	@Test
	public void testCasilla() {
		
		assertNotNull(c9);
		
		assertTrue( c9.casillaConMina() );
		
		assertFalse( c0.casillaConMina() );
	
	}

	@Test
	public void testCasillaInt() {
		
		assertNotNull(c0);
				
		assertFalse( c0.casillaConMina() );
		assertFalse( c1.casillaConMina() );
		
		try {
			new Casilla(-1);
			fail("Se debe lanzar la Excepcion NumVecinosIncorrectos cuando se pasa un valor negativo al constructor de casilla");
		} 
		catch (NumVecinosIncorrectos e) {
			System.out.println("Imposible crear una casilla con esos parametros");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza el constructor de Casilla(int) no es valida");
		}
		
		
		try {
			new Casilla(9);
			fail("Se debe lanzar la Excepcion NumVecinosIncorrectos cuando se pasa un valor superior a 8 al constructor de casilla");
		} 
		catch (NumVecinosIncorrectos e) {
			System.out.println("Imposible crear una casilla con esos parametros");
		}
		catch (Exception e) {
			fail("La Excepcion que lanza el constructor de Casilla(int) no es valida");
		}
		
	}

	@Test
	public void testGetNVecinosConMina() {
		
		assertEquals( c0.getNVecinosConMina(), 0);
		
		assertEquals( c1.getNVecinosConMina(), 1);
		
		assertEquals( c2.getNVecinosConMina(), 2);
		
		assertEquals( c3.getNVecinosConMina(), 3);
		
		assertEquals( c4.getNVecinosConMina(), 4);
		
		assertEquals( c5.getNVecinosConMina(), 5);
		
		assertEquals( c6.getNVecinosConMina(), 6);
		
		assertEquals( c7.getNVecinosConMina(), 7);
		
		assertEquals( c8.getNVecinosConMina(), 8);
		
	}
	
	@Test
	public void testConsultarAbierta(){

		// no hay ninguna casilla abierta
		assertFalse(c0.consultarAbierta());
		assertFalse(c1.consultarAbierta());
		assertFalse(c2.consultarAbierta());
		assertFalse(c3.consultarAbierta());
		assertFalse(c4.consultarAbierta());
		assertFalse(c5.consultarAbierta());
		assertFalse(c6.consultarAbierta());
		assertFalse(c7.consultarAbierta());
		assertFalse(c8.consultarAbierta());
		assertFalse(c9.consultarAbierta());
				
	}
	@Test
	public void testConsultarMarcada(){
		
		// no hay ninguna casilla marcada
		assertFalse(c0.consultarMarcada());
		assertFalse(c1.consultarMarcada());
		assertFalse(c2.consultarMarcada());
		assertFalse(c3.consultarMarcada());
		assertFalse(c4.consultarMarcada());
		assertFalse(c5.consultarMarcada());
		assertFalse(c6.consultarMarcada());
		assertFalse(c7.consultarMarcada());
		assertFalse(c8.consultarMarcada());
		assertFalse(c9.consultarMarcada());
				
	}
	
	@Test
	public void cambiarAbierta(){
		
		assertFalse(c9.consultarAbierta()); // casilla no abierta, no marcada, con mina
		
		c9.cambiarAbierta();  // abrir casilla
		assertTrue(c9.consultarAbierta());
	
		
		c9.cambiarAbierta();  // intento cerrar casilla --> imposible
		assertTrue(c9.consultarAbierta());
	
		
		
	}
	@Test
	public void cambiarMarcada(){
		
		c1.cambiarMarcada(); // marcar casilla
		assertTrue(c1.consultarMarcada());
		
				
	
		if ( c1.consultarMarcada() == true ){
			c1.cambiarAbierta(); // abrir casilla
			assertFalse(c1.consultarAbierta());
		}
	
		c1.cambiarMarcada(); // desmarcar casilla
		assertFalse(c1.consultarMarcada());
		
	}
	
}