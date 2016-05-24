package preguntas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

public class Preguntas {
	private RandomAccessFile fileAleatorio;
	private File archivo, archivo2;
	private ArrayList<String> listaDePreguntas, listaOpciones;
	private final static int espacioEntrePregunta = 200, espacioEntreRespuesta = 200;
	private final int NUM_OPCIONES = 50;
	private Random rand = new Random(System.nanoTime());
	private final int TOTAL_PRE = 25;
	private ArrayList<Integer> list, list2;

	public Preguntas() {
		archivo = new File("src/preguntas/preguntas.dat");
		archivo2 = new File("src/preguntas/opciones.dat");
		listaDePreguntas = new ArrayList<String>();
		listaOpciones = new ArrayList<String>();
		list = new ArrayList<Integer>();
		list2 = new ArrayList<Integer>();
	}

	public void escribirEnfichero() {
		try {
			fileAleatorio = new RandomAccessFile(archivo, "rw");
			int index = 0;
			for (int i = 1; i <= listaDePreguntas.size(); i++) {
				fileAleatorio.seek(i * 200);
				fileAleatorio.writeUTF(listaDePreguntas.get(index));
				index++;
			}

			fileAleatorio = new RandomAccessFile(archivo2, "rw");
			index = 0;
			for (int i = 1; i < NUM_OPCIONES; i++) {
				fileAleatorio.seek(i * espacioEntreRespuesta);
				fileAleatorio.writeUTF(listaOpciones.get(index));
				index++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void obtenerPreguntas() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("src/preguntas/PreguntasMaraton.txt")));
			String linea = reader.readLine();

			while (linea != null) {
				listaDePreguntas.add(linea);
				linea = reader.readLine();
			}

			reader = new BufferedReader(new FileReader(new File("src/preguntas/Respuestas.txt")));

			linea = reader.readLine();
			while (linea != null) {
				listaOpciones.add(linea);
				linea = reader.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String obtenerUnaPregunta(int numAleatorio) {

		try {
			fileAleatorio = new RandomAccessFile(archivo, "rw");
			fileAleatorio.seek(numAleatorio * espacioEntrePregunta);
			return fileAleatorio.readUTF();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<String> obtenerOpciones(int num) {
		try {
			int aux = 0;
			ArrayList<String> listaTemp = new ArrayList<String>();
			fileAleatorio = new RandomAccessFile(archivo2, "rw");

			for (int i = 0; i < 3; i++) {
				fileAleatorio.seek((num + aux) * espacioEntreRespuesta);
				listaTemp.add(fileAleatorio.readUTF());
				aux++;

			}

			return listaTemp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Integer> numeroAleatorios() {
		int numeroAleatorio;

		do {
			numeroAleatorio = rand.nextInt(TOTAL_PRE) + 1;

			if (list.isEmpty()) {
				list.add(numeroAleatorio);
			} else {
				if (list.contains(numeroAleatorio)) {
					numeroAleatorio = rand.nextInt(TOTAL_PRE);
				} else {
					list.add(numeroAleatorio);
				}

			}

		} while (list.size() < TOTAL_PRE);

		return list;
	}

	public ArrayList<Integer> numeroAleatoriosOp() {
		int numeroAleatorio;

		do {
			numeroAleatorio = rand.nextInt(TOTAL_PRE) + 1;

			if (list2.isEmpty()) {
				list2.add(numeroAleatorio);
			} else {
				if (list2.contains(numeroAleatorio)) {
					numeroAleatorio = rand.nextInt(TOTAL_PRE);
				} else {
					list2.add(numeroAleatorio);
				}

			}

		} while (list2.size() < TOTAL_PRE);

		return list2;
	}

}
