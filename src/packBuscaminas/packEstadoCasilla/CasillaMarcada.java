package packBuscaminas.packEstadoCasilla;

public class CasillaMarcada implements EstadoCasilla {
		
	public CasillaMarcada(){
		super();
	}
	
	@Override
	public boolean consultar(){
		return true;
	}

	@Override
	public void cambiarEstado(){
		
	}

}