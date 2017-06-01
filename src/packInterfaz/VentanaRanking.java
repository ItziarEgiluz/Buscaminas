package packInterfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import PackBaseDeDatos.BaseDeDatos;
import packBuscaminas.ListaOrdenada;
import packBuscaminas.Jugador;
import packBuscaminas.Tablero;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRanking extends JDialog {

	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JList<String> list = new JList<>(listModel);
	private JScrollPane scrollPane = new JScrollPane();	
	private final JPanel contentPanel = new JPanel();
	
	public VentanaRanking() {		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				ListaOrdenada<Jugador> datos = BaseDeDatos.getInstance().cargarDatos();
				int nivel = Tablero.getInstance().getNivel().ordinal()+1;
							
				List<Jugador>  filtro = datos.stream()
											.filter(j -> j.getNivel() == nivel )
											.collect(Collectors.toList());	
				
				int i = 0 ;
				while( i < filtro.size() && i < 10 ){
					listModel.addElement(String.format("%-15s%3d%6d",  filtro.get(i).getNombre(), filtro.get(i).getNivel() , filtro.get(i).getTiempo())); 
					i++;
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRanking.class.getResource("/packImagenes/icono.png")));
		
		setBounds(100, 100, 214, 233);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize =	getSize();
		if(windowSize.height>screenSize.height)
			windowSize.height= screenSize.height;
		if(windowSize.width	>screenSize.width)
			windowSize.	width= screenSize.width;
		setLocation((screenSize.width -	windowSize.	width)/2,(screenSize.height - windowSize.height)/2);
			
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		list.setValueIsAdjusting(true);
		list.setFont(new Font("Courier New", Font.BOLD, 12));
		scrollPane.setViewportView(list);
		
		JLabel lblRankingJugadores = new JLabel("Ranking de jugadores");
		lblRankingJugadores .setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRankingJugadores .setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblRankingJugadores , BorderLayout.NORTH);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Aceptar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();		
				}
			});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		}
}
