package filosofos;

import java.util.concurrent.Semaphore;

// Nuestro filosofo ahora tiene dos semaforos, el tiene inicialmente un palillo, y como segundo palillo 
// referenciara al palillo del filosofo de al lado, por eso inicialmente uno es null; una vez creados
// todos los filosofos se asignan los palillos que faltan
public class Filosofo implements Runnable
{
	private String nombre;
	private String color;
	private int posicion;
	private Semaphore p_der;
	private Semaphore p_izq;

	public Filosofo(String nombre, String color, int posicion)
	{
		this.nombre = nombre;
		this.color = color;		
		this.posicion = posicion;
		this.p_der = new Semaphore(1);
		this.p_izq = null;
	}

	public Semaphore getP_der()
	{
		return p_der;
	}

	public Semaphore getP_izq()
	{
		return p_izq;
	}
	
	public void setP_der(Semaphore s)
	{
		this.p_der = s;
	}
	
	public void setP_izq(Semaphore s)
	{
		this.p_izq = s;
	}

	@Override
	public void run()
	{
		try
		{
			// Esto hace que los filosofos impares tengan delay de 100ms
			if(this.posicion%2==1)
				Thread.sleep(100);
			while(true)
			{
				System.out.println(color + tmp() + nombre + "\t-> Espera para comer.");
				p_der.acquire();
				System.out.println(color + tmp() + nombre + "\t-> Ha cogido un palillo.");
				p_izq.acquire();
				System.out.println(color + tmp() + nombre + "\t-> Ha cogido un palillo.");
				System.out.println(color + tmp() + nombre + "\t-> Ha empezado a cenar.");
				Thread.sleep(Mesa.tiempoCena * 1000);
				System.out.println(color + tmp() + nombre + "\t-> Ha terminado de cenar.");
				p_der.release();
				p_izq.release();
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
