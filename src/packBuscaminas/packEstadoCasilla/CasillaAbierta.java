package packBuscaminas.packEstadoCasilla;

public class CasillaAbierta implements EstadoCasilla {
	
	
	private boolean estaAbierta;
	
	public CasillaAbierta(){
		estaAbierta = false;
	}
	
	@Override
	public boolean consultar() {
		return estaAbierta;
	}

	@Override
	public void cambiarEstado() {
		estaAbierta = true;
	}
}