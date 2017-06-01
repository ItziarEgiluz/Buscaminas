package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import packBuscaminas.Nivel;
import packBuscaminas.Tablero;
import packExcepciones.ColumnaIncorrecta;
import packExcepciones.FilaIncorrecta;
import packExcepciones.NivelIncorrecto;
import packInterfaz.Ventana;
import packInterfaz.VentanaPantallaInicio;

public class Controlador extends MouseAdapter implements ActionListener {
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if ( (JLabel)arg0.getSource() == Ventana.getInstance().getLblCarita() )
		{
			if ( arg0.getButton() == MouseEvent.BUTTON1 )
			{
				Ventana.getInstance().getLblCarita().setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/cara1.gif")));
					
				Nivel nivel = VentanaPantallaInicio.getInstance().getNivelSeleccionado();
				
				try {
					Tablero.getInstance().inicializarJuego( nivel );
				} catch (NivelIncorrecto e) {
					e.printStackTrace();
				}
			}			
		}
		else if( ! Tablero.getInstance().finalDeLaPartida() )
		{
			//Posicion de la etiqueta que se ha pulsado
			JLabel ref = ((JLabel)arg0.getSource());
							
			int i = Ventana.getInstance().buscarEtiqueta( ref );
				
			int fila = i / Tablero.getInstance().getNivel().getColumnas();
			int col  = i % Tablero.getInstance().getNivel().getColumnas();
			
			if( arg0.getButton() == MouseEvent.BUTTON1 )
			{	
				Tablero.getInstance().clickIzquierdo( fila , col );
				
				Tablero.getInstance().setChanged();
				Tablero.getInstance().notifyObservers();
			}
			else if( arg0.getButton() == MouseEvent.BUTTON3 )
			{
				Tablero.getInstance().clickDerecho( fila , col );
				Tablero.getInstance().setChanged();
				Tablero.getInstance().notifyObservers();
			}	
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand().equalsIgnoreCase("Salir") ){
			int opcion = JOptionPane.showOptionDialog(Ventana.getInstance(), "¿Quieres salir del juego?",
					"Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, 
					new Object[] { "Si", "No"},"Si");
			if( opcion == 0 ){
				Ventana.getInstance().dispose();
				System.exit(0);
			}
		}
		else if ( e.getActionCommand().equalsIgnoreCase("Nuevo juego") ){
			Ventana.getInstance().setVisible(false);
			VentanaPantallaInicio.getInstance().setVisible(true);
			VentanaPantallaInicio.getInstance().eliminarNombreJugador();
			Ventana.getInstance().getLblCarita().setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/cara1.gif")));
		}
		else if ( e.getActionCommand().equalsIgnoreCase("Posición de las minas")){
			for( int fila = 0 ; fila < Tablero.getInstance().getNivel().getFilas() ; fila++ ){
				for( int col = 0 ; col < Tablero.getInstance().getNivel().getColumnas() ; col++){
					try{
						if( Tablero.getInstance().casillaConMina(fila,col) &&
								!Tablero.getInstance().consultarMarcada(fila, col) )
							Tablero.getInstance().clickDerecho(fila, col);
					} 
					catch (FilaIncorrecta e1) {
						e1.printStackTrace();
					} 
					catch (ColumnaIncorrecta e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}