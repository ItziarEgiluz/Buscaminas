package packControlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import packInterfaz.Ventana;

public class ControladorVentana extends WindowAdapter {
	public void windowClosing(WindowEvent arg0) {
		int opcion = JOptionPane.showOptionDialog(Ventana.getInstance(), "¿Quieres salir del juego?",
				"Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, 
				new Object[] { "Si", "No"},"No");
		
		if( opcion == 0 ){
			Ventana.getInstance().dispose();
			System.exit(0);
		}
	}
}
