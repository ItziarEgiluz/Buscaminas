package packInterfaz;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import PackBaseDeDatos.BaseDeDatos;
import packControlador.*;
import packExcepciones.*;
import packBuscaminas.*;
import java.awt.event.*;
import java.awt.*;
	
public class Ventana extends JFrame implements Observer{
	
	//Es patron singleton 
	private static Ventana miVentana;
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblMinas;
	private JPanel panel_5;
	private JLabel lblTiempo;
	private Controlador controlador = new Controlador();
	private ControladorVentana controladorVentana = new ControladorVentana();
	private ArrayList<JLabel> listaEtiquetas = new ArrayList<>();
	
	private JLabel lblDigitoMinas1;
	private JLabel lblDigitoMinas2;
	private JLabel lblDigitoMinas3;
	
	private JLabel lblCarita;
	private JLabel lblDigito1;
	private JLabel lblDigito2;
	private JLabel lblDigito3;
	
	private JPanel contentPane;
	private JMenuItem mntmRanking;
	private JMenuItem mntmPosicionMinas;
	
	public JLabel getLblCarita(){
		return lblCarita;
	}
	
	public static Ventana getInstance(){
		if ( miVentana == null )
			miVentana = new Ventana();
		return miVentana;
	}
	
	public int buscarEtiqueta( JLabel ref ){
		int i = 0;
		boolean enc = false;
		while( !enc )
			if ( ref == listaEtiquetas.get(i) )
				enc = true;
			else
				i++;
		return i;
	}
	

	private Ventana() {
		
		addWindowListener(controladorVentana);
		
		setTitle("Buscaminas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/packImagenes/icono.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnJuego = new JMenu("Juego");
		menuBar.add(mnJuego);
		
		JMenuItem mntmNuevoJuego = new JMenuItem("Nuevo juego");
		mnJuego.add(mntmNuevoJuego);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		
		mntmSalir.addActionListener( controlador );
		mntmNuevoJuego.addActionListener( controlador);
		
		mntmRanking = new JMenuItem("Ranking");
		mntmRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaRanking vr = new VentanaRanking();
				
				vr.setModal(true);
				vr.setVisible(true);
			}
		});
		
		mnJuego.add(mntmRanking);
		
		mnJuego.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		mntmPosicionMinas = new JMenuItem("Posición de las minas");
		mntmPosicionMinas.addActionListener(controlador);
		mnAyuda.add(mntmPosicionMinas);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		lblCarita = new JLabel("");
		
		lblCarita.addMouseListener( controlador );
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(0);
		
		lblDigitoMinas1 = new JLabel("");
		panel_3.add(lblDigitoMinas1);
		
		lblDigitoMinas2 = new JLabel("");
		panel_3.add(lblDigitoMinas2);
		
		lblDigitoMinas3 = new JLabel("");
		panel_3.add(lblDigitoMinas3);
		
		lblCarita = new JLabel("");
		
		lblCarita.addMouseListener(controlador);
				
		lblCarita.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCarita);
		lblCarita.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/cara1.gif")));
		
		lblMinas = new JLabel("Minas");
		lblMinas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMinas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinas.setBackground(Color.LIGHT_GRAY);
		panel_2.add(lblMinas, BorderLayout.NORTH);
				
		lblCarita.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCarita);
		lblCarita.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/cara1.gif")));
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4, BorderLayout.SOUTH);
		panel_4.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(0);
		
		lblDigito1 = new JLabel("");
		panel_4.add(lblDigito1);
		lblDigito1.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n0.gif")));
		
		lblDigito2 = new JLabel("");
		panel_4.add(lblDigito2);
		lblDigito2.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n0.gif")));
		
		lblDigito3 = new JLabel("");
		panel_4.add(lblDigito3);
		lblDigito3.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n0.gif")));
		
		lblTiempo = new JLabel("Tiempo");
		lblTiempo.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTiempo, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);		
						
		
		VentanaPantallaInicio dialog = VentanaPantallaInicio.getInstance();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize =	dialog.getSize();
		if(windowSize.height>screenSize.height)
			windowSize.height= screenSize.height;
		if(windowSize.width	>screenSize.width)
			windowSize.	width= screenSize.width;
		
		dialog.setLocation((screenSize.width -	windowSize.	width)/2,(screenSize.height - windowSize.height)/2);
		dialog.setVisible(true);

		Tablero.getInstance().addObserver( this );
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	
		Tablero tab = Tablero.getInstance();
		Nivel niv = tab.getNivel();
		int filas = niv.getFilas();
		int columnas = niv.getColumnas();
		//Cuando se invoque el método, a no ser que se haga click, no se redibujaran las casillas
		if( arg1 != null && (arg1.equals("INICIO") || arg1.equals("CLICK") ) ){		
			dibujarCasillas( filas, columnas);
		}
		
		establecerDisplayMinas();
				
		int ancho  = niv.getAncho();
		int alto = niv.getAlto();
		
		setSize(ancho,alto);
				
		centrarVentana();
				
		if( Tablero.getInstance().partidaGanada() ){
			lblCarita.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/cara3.gif")));
			String nombre = VentanaPantallaInicio.getInstance().getNombreJugador();
			int tiempo = Tablero.getInstance().tiempoUtilizado();
			int nivel = Tablero.getInstance().getNivel().ordinal() + 1; 
			
			//Guardo los datos en la base de datos
			BaseDeDatos.getInstance().guardarDatos( nombre,tiempo, nivel );		
		}
	}
	
	private void dibujarCasillas(int filas , int columnas ){
		try{
			//Hago clear en el modelo
			listaEtiquetas.clear();
			//Hago clear en la vista
			panel_1.removeAll();
			
			JLabel etiqueta = null;
			int f;
			int c;
			//recorro todo el tablero para redibujar las casillas
			for( int i = 0 ; i < filas*columnas ; i++ ){
				//Vista
				
				f = i / columnas;
				c = i % columnas;
				
				etiqueta = new JLabel("");
				// Si la casilla no está abierta puede tener la posibilidad de colocar la bandera con el click derecho
				if( !Tablero.getInstance().consultarAbierta(f, c) ){	
					if ( !Tablero.getInstance().consultarMarcada(f, c) ){
						if( Tablero.getInstance().finalDeLaPartida() &&  !Tablero.getInstance().partidaGanada() && Tablero.getInstance().casillaConMina(f, c ) ){
							etiqueta.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/mina-n.gif")));
						}
						else{
							etiqueta.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/tablero.gif")));
						}
					}
					else if (Tablero.getInstance().finalDeLaPartida() && Tablero.getInstance().consultarMarcada(f, c) &&  
							!Tablero.getInstance().casillaConMina( f , c ) ){
							etiqueta.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/mina-x.gif")));
					}
					else{
						etiqueta.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/bandera.gif")));
					}
				}
				//Si está abierta
				else {
					if( Tablero.getInstance().casillaConMina( f , c ) ){
						lblCarita.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/cara2.gif")));
						etiqueta.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/mina-r.gif")));
						
					}
					else{
						
						int minasAl = Tablero.getInstance().minasAlrededor(f, c);
						etiqueta.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/c" + minasAl + ".gif")));
						
					}
				}
							
				panel_1.add(etiqueta);
								
				//Modelo
				//añado la etiqueta
				listaEtiquetas.add( etiqueta );
				
				//Controlador
				etiqueta.addMouseListener( controlador );
			
				// Para ACTUALIZAR TABLERO
				this.validate();
			}
		}
		catch( FilaIncorrecta ex ){
			ex.printStackTrace();
		}
		catch(ColumnaIncorrecta ex ){
			ex.printStackTrace();
		}
		
	}
	private void centrarVentana(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize =	this.getSize();
		if(windowSize.height>screenSize.height)
			windowSize.height= screenSize.height;
		
		if(windowSize.width	>screenSize.width)
			windowSize.	width= screenSize.width;
				
		setLocation((screenSize.width -	windowSize.	width)/2,(screenSize.height - windowSize.height)/2);
	}
	
	private void establecerDisplayMinas(){
		int minas = Tablero.getInstance().getNivel().getMinas();
		minas = minas - Tablero.getInstance().casillasMarcadas();
		int dig1 = 0;		
		int dig2 = Math.abs(minas) / 10;
		int resto = Math.abs(minas) % 10;			
		int dig3 = resto;
		
		lblDigitoMinas1.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n" + dig1  + ".gif")));
		lblDigitoMinas2.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n" + dig2 + ".gif")));
		lblDigitoMinas3.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n" + dig3 + ".gif")));		
				
		
				
		dig1 = Tablero.getInstance().tiempoUtilizado() / 100;
		resto = Tablero.getInstance().tiempoUtilizado()  % 100;
		dig2 = resto / 10;
		resto = resto % 10;
		dig3 = resto;
		
		lblDigito1.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n" + dig1 + ".gif")));		
		lblDigito2.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n" + dig2 + ".gif")));	
		lblDigito3.setIcon(new ImageIcon(Ventana.class.getResource("/packImagenes/n" + dig3 + ".gif")));
		
	}
}