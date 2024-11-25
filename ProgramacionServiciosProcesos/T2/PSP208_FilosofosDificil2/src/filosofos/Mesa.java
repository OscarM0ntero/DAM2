package filosofos;

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
	public static final int tiempoCenaMinimo = 5; // Tiempo en segundos minimo que tardan en comer

	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		 Palillo[] palillos = new Palillo[cantFilosofos];
	        Filosofo[] filosofos = new Filosofo[cantFilosofos];

	        for (int i = 0; i < cantFilosofos; i++) {
	            palillos[i] = new Palillo();
	        }

	        for (int i = 0; i < cantFilosofos; i++) {
	            Palillo izquierdo = palillos[i];
	            Palillo derecho = palillos[(i + 1) % cantFilosofos];
	            filosofos[i] = new Filosofo(nombres[i], colores[i], izquierdo, derecho);
	            filosofos[i].start();
	        }
	}
}
