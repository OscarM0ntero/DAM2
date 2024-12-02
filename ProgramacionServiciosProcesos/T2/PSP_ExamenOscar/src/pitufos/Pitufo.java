// Realizado por Oscar Montero

package pitufos;

public class Pitufo implements Runnable
{
	private final String nombre;
	private final String color;

	public Pitufo(String nombre, String color, boolean delay)
	{
		this.nombre = nombre;
		this.color = color;

	}

	@Override
	public void run()
	{
		try
		{
			// Comienzan todos esperando para comer
			System.out.println(color + tmp() + nombre + "\t-> Espera para comer.");
			Comedor.asientosComedor.acquire();
			Comedor.cantidadDePitufosEnComedor++;
			// Toman un asiento y comienzan a comer
			System.out.println(color + tmp() + nombre + "\t-> Ha tomado asiento en el comedor.");
			System.out.println(color + tmp() + nombre + "\t-> Ha empezado a comer una ensalada.");
			// Esto es lo que tardan en comer el primer plato
			Thread.sleep(Comedor.tiempoPlato1 * 1000);
			System.out.println(color + tmp() + nombre + "\t-> Ha terminado la ensalada.");
			System.out.println(color + tmp() + nombre + "\t-> Ha empezado a comer una fabada.");
			// Esto es lo que tardan en comer el segundo plato
			Thread.sleep(Comedor.tiempoPlato2 * 1000);
			System.out.println(color + tmp() + nombre + "\t-> Ha terminado la fabada.");
			// Liberan el asiento y terminan su ejecución
			Comedor.asientosComedor.release();
			Comedor.cantidadDePitufosEnComedor--;
			System.out.println(color + tmp() + nombre + "\t-> Ha dejado el asiento en el comedor.");

		}
		catch (InterruptedException e)
		{
			System.err.println(color + nombre + " fue interrumpido.");
			Thread.currentThread().interrupt();
		}
	}

	// Temporizador para imprimir los tiempos de ejecucion en ms
	public static String tmp()
	{
		return String.format("[%dms]\t", System.currentTimeMillis() - Comedor.tiempoInicial);
	}
}