package packBuscaminas;
import packBuscaminas.packEstadoCasilla.*;
import packExcepciones.NumVecinosIncorrectos;

public class Casilla {
	private int numVecinosConMina;
	private EstadoCasilla estadoCasilla;
	private boolean mina;
	
	public Casilla() {
		mina = true;
		estadoCasilla = new CasillaNoMarcada();
	}

	public Casilla(int pNumVecinos) throws NumVecinosIncorrectos {
		if( pNumVecinos < 0 || pNumVecinos > 8 ){
			throw new NumVecinosIncorrectos();
		}
		else{
			mina = false;
			estadoCasilla = new CasillaNoMarcada();
			numVecinosConMina = pNumVecinos;
		}
	}
	
	public boolean casillaConMina (){
		return mina;
	}
	
	public int getNVecinosConMina() {
		return numVecinosConMina;
	}

	public boolean consultarAbierta(){
		if(estadoCasilla instanceof CasillaAbierta && estadoCasilla.consultar() == true){
			 return true;
		}
		else{
			return false;
		}
	}
	public boolean consultarMarcada(){
		if( estadoCasilla instanceof CasillaMarcada ){
			return true;
		}
		else if( estadoCasilla instanceof CasillaNoMarcada ){
			return false;
		}
		else{
			return false;
		}
	}
	
	public void cambiarMarcada(){
				
		if( estadoCasilla instanceof CasillaNoMarcada ){
			estadoCasilla = new CasillaMarcada();
		}
		else{
			estadoCasilla = new CasillaNoMarcada();
		}
		
	}
	public void cambiarAbierta(){
		
		if( estadoCasilla instanceof CasillaNoMarcada ){
			estadoCasilla = new CasillaAbierta();
			
			estadoCasilla.cambiarEstado();
		}
	}
}