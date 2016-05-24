package graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import preguntas.Preguntas;

public class Accion implements ActionListener {
	private String nombre;
	private Preguntas preguntas;
	private Random rand = new Random(System.nanoTime());
	private ArrayList<Integer> lista;
	private int index = 0, indexOp = 0;
	private String[] options = new String[3];
	private ArrayList<Integer> list;

	public Accion(String nombre) {
		this.nombre = nombre;
		preguntas = new Preguntas();
		lista = preguntas.numeroAleatorios();
		list = new ArrayList<Integer>();

		numeroAleatorios();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int numeroAleatorio = lista.get(index);
		System.out.println(numeroAleatorio);
		// int numAleatorioOP = preguntas.numeroAleatoriosOp().get(indexOp);
		String pregunta = preguntas.obtenerUnaPregunta(numeroAleatorio);
		System.out.println(pregunta);
		for (int i = 0; i < 3; i++) {
			int num = list.get(i);
			options[i] = preguntas.obtenerOpciones(numeroAleatorio).get(num);

		}

		JOptionPane.showOptionDialog(null, pregunta, "RESPONDE", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		index++;
		indexOp++;
		if (index < 25) {
			index = 0;
		}

		if (indexOp < 3) {
			indexOp = 0;
		}

	}

	public void numeroAleatorios() {
		int numeroAleatorio;

		do {
			numeroAleatorio = rand.nextInt(3);

			if (list.isEmpty()) {
				list.add(numeroAleatorio);
			} else {
				if (list.contains(numeroAleatorio)) {
					numeroAleatorio = rand.nextInt(3);
				} else {
					list.add(numeroAleatorio);
				}

			}

		} while (list.size() < 3);

	}

}
