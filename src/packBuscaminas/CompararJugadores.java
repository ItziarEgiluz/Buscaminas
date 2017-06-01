package packBuscaminas;
import java.util.Comparator;

public class CompararJugadores implements Comparator<Jugador> {
	@Override
	public int compare(Jugador o1, Jugador o2) {
		
		return o1.getTiempo() - o2.getTiempo();
	}

}
