package filosofos;

import java.util.ArrayList;

public class Mesa
{
	public static String[] colores =
	{ "\u001B[0m", // RESET
			"\u001B[31m",
			"\u001B[32m",
			"\u001B[33m",
			"\u001B[34m",
			"\u001B[35m",
			"\u001B[36m",
			"\u001B[37m",
			"\u001B[30m",
			"\u001B[90m",
			"\u001B[91m",
			"\u001B[92m",
			"\u001B[93m",
			"\u001B[94m",
			"\u001B[95m",
			"\u001B[96m",
			"\u001B[97m"
	};

	public static String[] nombres =
	{ "Sócrates", "Platón\t", "Aristóteles", "Confucio", "Descartes", "Kant", "Hegel", "Nietzsche", "Spinoza", "Hume",
			"Locke", "Rousseau", "Marx", "Heidegger", "Wittgenstein" };
	
	public static final long tiempoInicial = System.currentTimeMillis();

	public static final int cantFilosofos = 5; // Cantidad de filosofos, max 15
	public static final int tiempoCena = 5; // Tiempo en segundos que tardan en comer

	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		ArrayList<Thread> hilos = new ArrayList<>();
		ArrayList<Filosofo> filosofos = new ArrayList<>();

		
		// Creamos los filosofos y los añadimos a una lista
		for (int i = 0; i < cantFilosofos; i++)
		{
			// Generamos los filosofos, dandoles delay a los impares para que no comiencen todos a la vez
			Filosofo f = new Filosofo(nombres[i], colores[i + 1], i);
			filosofos.add(f);
		}
		
		// Asignamos sus tenedores izquierdos
		for (int i = 0; i < cantFilosofos; i++)
		{
			filosofos.get(i).setP_izq((i!=0)?filosofos.get(i-1).getP_der():filosofos.get(cantFilosofos-1).getP_der());
		}
		
		// Creamos e iniciamos los hilos de los filosofos
		for(int i = 0; i < cantFilosofos; i++)
		{
			Thread t = new Thread(filosofos.get(i));
			hilos.add(t);
			t.start();
		}
	}
}
