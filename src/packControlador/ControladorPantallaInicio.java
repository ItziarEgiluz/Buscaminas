package packControlador;

import packBuscaminas.Nivel;
import packBuscaminas.Tablero;
import packExcepciones.NivelIncorrecto;
import packInterfaz.VentanaPantallaInicio;
import packInterfaz.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPantallaInicio implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		Nivel nivel = VentanaPantallaInicio.getInstance().getNivelSeleccionado();
		Tablero.getInstance().setNivel( nivel );
		try {
			Tablero.getInstance().inicializarJuego( nivel );
		} catch (NivelIncorrecto e1) {
			System.out.println("El nivel introducido es incorrecto");
			e1.printStackTrace();
		}		
		VentanaPantallaInicio.getInstance().setVisible(false);
		Ventana.getInstance().setVisible(true);
		
	}
	
}