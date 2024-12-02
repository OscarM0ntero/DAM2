// Realizado por Oscar Montero

package pitufos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Comedor
{
	// Las siguientes dos arrays de strings es para los colores y los nombres
	public static String[] colores =
	{ "\u001B[0m", // RESET
			"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m",
			"\u001B[30m", "\u001B[90m", "\u001B[91m" };

	public static String[] nombres =
	{ "Papa pitufo", "Pitufina", "Filósofo", "Pintor\t", "Gruñon\t", "Bromista", "Dormilón", "Tímido\t", "Bonachón",
			"Romántico" };

	public static final long tiempoInicial = System.currentTimeMillis(); // Esto es para el cronometro, toma el tiempo
																			// inicial

	public static final int cantFilosofos = 10; // Cantidad de pitufos
	public static final int tiempoPlato1 = 1; // Tiempo en segundos que se tarda en comer el primer plato
	public static final int tiempoPlato2 = 3; // Tiempo que se tarda en el segundo plato
	public static final int cantidadDeAsientosComedor = 3; // Esto limita el comedor a 3 pitufos concurrentes
	public static int cantidadDePitufosEnComedor = 0;

	public static final Semaphore asientosComedor = new Semaphore(cantidadDeAsientosComedor); // Generamos los asientos
																								// del comedor

	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		ArrayList<Thread> pitufos = new ArrayList<>();
		ContadorPitufos contador = new ContadorPitufos();

		Thread hiloContador = new Thread(contador);

		hiloContador.start();
		for (int i = 0; i < cantFilosofos; i++)
		{
			// Iniciamos los pitufos, que comenzaran esperando para sentarse en el comedor
			// Les asignamos un nombre y un color
			pitufos.add(new Thread(new Pitufo(nombres[i], colores[i + 1], (i % 2 == 0) ? false : true)));
			pitufos.get(i).start();
		}
		for (int i = 0; i < cantFilosofos; i++)
		{
			while (pitufos.get(i).isAlive())
			{
			}
		}
		contador.funcionando = false;

	}
}
