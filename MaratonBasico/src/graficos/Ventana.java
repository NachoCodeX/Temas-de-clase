package graficos;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JFrame {

	private Container c;
	private JPanel panel;
	private JButton[] botones;
	private String nombre;
	private final int NUM_PREGUNTAS = 10;

	public Ventana() {
		this.setTitle("Maraton");
		this.setSize(1000, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		iniciarComponentes();
		addComponentes();
		accionesBotones();
	}

	private void iniciarComponentes() {
		nombre = JOptionPane.showInputDialog("INGRESE SU NOMBRE: ");
		c = getContentPane();
		panel = new JPanel(new GridLayout(2, 10));
		botones = new JButton[20];

		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JButton("");
		}

		for (int i = 0; i < NUM_PREGUNTAS; i++) {
			botones[i].setText(nombre);
			botones[i].setName(i + "");
		}

		for (int i = 10; i < NUM_PREGUNTAS * 2; i++) {
			botones[i].setText("Ignorancia");
		}

	}

	private void addComponentes() {
		for (int i = 0; i < botones.length; i++) {
			panel.add(botones[i]);
		}
		c.add(panel);
	}

	private void accionesBotones() {

		for (int i = 0; i < NUM_PREGUNTAS; i++) {
			botones[i].addActionListener(new Accion(i + ""));
		}
	}

}
