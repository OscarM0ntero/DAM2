package filosofos;

public class Filosofo implements Runnable
{
	private final String nombre;
	private final String color;
	private final boolean delay;	// Booleana de si necesita o no delay

	public Filosofo(String nombre, String color, boolean delay)
	{
		this.nombre = nombre;
		this.color = color;
		this.delay = delay;

	}

	@Override
	public void run()
	{
		try
		{
			// Esto hace que algunos filosofos tengan delay
			if(delay)
				Thread.sleep(100);
			while(true)
			{
				System.out.println(color + tmp() + nombre + "\t-> Espera para comer.");
				Mesa.palillos.acquire();
				System.out.println(color + tmp() + nombre + "\t-> Ha cogido un palillo.");
				Mesa.palillos.acquire();
				System.out.println(color + tmp() + nombre + "\t-> Ha cogido un palillo.");
				System.out.println(color + tmp() + nombre + "\t-> Ha empezado a cenar.");
				Thread.sleep(Mesa.tiempoCena * 1000);
				System.out.println(color + tmp() + nombre + "\t-> Ha terminado de cenar.");
				Mesa.palillos.release();
				Mesa.palillos.release();
				System.out.println(color + tmp() + nombre + "\t-> Ha soltado los palillos.");
				
			}

		}
		catch (InterruptedException e)
		{
			System.err.println(color + nombre + " fue interrumpido.");
			Thread.currentThread().interrupt();
		}
	}
	
	// Temporizador para imprimir los tiempos de ejecucion en ms
	public String tmp()
	{
		return String.format("[%dms]\t", System.currentTimeMillis() - Mesa.tiempoInicial);
	}
}
