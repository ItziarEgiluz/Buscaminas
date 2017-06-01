package packPrincipal;

import java.awt.EventQueue;
import packInterfaz.Ventana;

public class ProgramaPrincipal{
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {										
					Ventana.getInstance();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}