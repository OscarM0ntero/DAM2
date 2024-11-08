// Hecho por Oscar Montero	14/10/2024
package multihilo;

public class Raton extends Thread
{
	String nombre;
	int tiempoCome;
	
	public Raton(String s, int t)
	{
		this.nombre = s;
		this.tiempoCome = t;
	}
	
	@Override
	public void run()
	{
		try
		{
			System.out.printf(" %s empieza la merienda %n", nombre);
			Thread.sleep(1000*tiempoCome);
			System.out.printf(" %s termino de comer %n", nombre);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
