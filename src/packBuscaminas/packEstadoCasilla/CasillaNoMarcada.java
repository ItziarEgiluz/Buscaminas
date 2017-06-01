package packBuscaminas.packEstadoCasilla;

public class CasillaNoMarcada implements EstadoCasilla{
	
	public CasillaNoMarcada(){
		super();
	}

	@Override
	public boolean consultar() {
		return true;
	}

	@Override
	public void cambiarEstado() {
		
	}
}
