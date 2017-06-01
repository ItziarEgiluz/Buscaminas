package packBuscaminas;

public class Jugador {

	private String nombre;
	private int tiempo;
	private int nivel;
	
	public Jugador(String pNombre, int pTiempo, int pNivel) {
		
		nombre = pNombre;
		tiempo = pTiempo;
		nivel = pNivel;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getTiempo() {
		return tiempo;
	}
	
	public int getNivel()
	{
		return nivel;
	}
}	