package packInterfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import packControlador.ControladorPantallaInicio;
import packBuscaminas.Nivel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPantallaInicio extends JDialog {

	private final JPanel panelNivel;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JButton okButton;
	private static VentanaPantallaInicio miVentanaPantallaInicio = new VentanaPantallaInicio();	
	private ControladorPantallaInicio controladorPantallaInicio = new ControladorPantallaInicio();	

	public static VentanaPantallaInicio getInstance(){
		return miVentanaPantallaInicio;
	}
	
	public Nivel getNivelSeleccionado(){
		if ( rdbtnNewRadioButton.isSelected() )
			return Nivel.NIVEL1;
		else if ( rdbtnNewRadioButton_1.isSelected() )
			return Nivel.NIVEL2;
		else
			return Nivel.NIVEL3;
	}
	
	public String getNombreJugador(){
		return textField.getText();
	}
	
	public void eliminarNombreJugador(){
		textField.setText("");
		textField.requestFocus();
	}

	private VentanaPantallaInicio() {
		getContentPane().setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPantallaInicio.class.getResource("/packImagenes/icono.png")));
		setTitle("Buscaminas");
		
		setBounds(100, 100, 352, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelNivel = new JPanel();
			panelNivel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccionar el nivel :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelNivel.setBounds(10, 81, 316, 98);
			contentPanel.add(panelNivel);
			panelNivel.setLayout(null);
			
			rdbtnNewRadioButton = new JRadioButton("Nivel 1");
			rdbtnNewRadioButton.setForeground(Color.BLACK);
			rdbtnNewRadioButton.setBounds(6, 21, 304, 23);
			buttonGroup.add(rdbtnNewRadioButton);
			panelNivel.add(rdbtnNewRadioButton);
			
			rdbtnNewRadioButton_1 = new JRadioButton("Nivel 2");
			rdbtnNewRadioButton_1.setBounds(6, 47, 304, 23);
			buttonGroup.add(rdbtnNewRadioButton_1);
			panelNivel.add(rdbtnNewRadioButton_1);
			
			rdbtnNewRadioButton_2 = new JRadioButton("Nivel 3");
			rdbtnNewRadioButton_2.setBounds(6, 71, 304, 23);
			buttonGroup.add(rdbtnNewRadioButton_2);
			panelNivel.add(rdbtnNewRadioButton_2);
		}
		
		JPanel panelNombre = new JPanel();
		panelNombre.setBounds(10, 38, 316, 32);
		contentPanel.add(panelNombre);
		panelNombre.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 8, 76, 14);
		panelNombre.add(lblNombre);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(  textField.getText().length() > 10){
					arg0.consume();
				}	
								
				panelNivel.setEnabled(true);		
				rdbtnNewRadioButton.setEnabled(true);
				rdbtnNewRadioButton_1.setEnabled(true);
				rdbtnNewRadioButton_2.setEnabled(true);
				okButton.setEnabled(true);
			}
		});
		textField.setBounds(96, 5, 210, 20);
		panelNombre.add(textField);
		textField.setColumns(10);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(10, 11, 316, 23);
		contentPanel.add(panelTitulo);
		
		JLabel lblBuscaminasV = new JLabel("Buscaminas");
		panelTitulo.add(lblBuscaminasV);{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);{
				okButton = new JButton("Aceptar");
				okButton.addActionListener(controladorPantallaInicio );
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Salir-------
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		panelNivel.setEnabled(false);		
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setEnabled(false);		
		rdbtnNewRadioButton_1.setEnabled(false);
		rdbtnNewRadioButton_2.setEnabled(false);
		okButton.setEnabled(false);
	}
}
