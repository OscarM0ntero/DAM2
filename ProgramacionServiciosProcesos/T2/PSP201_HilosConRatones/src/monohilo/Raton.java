// Hecho por Oscar Montero	14/10/2024
package monohilo;

public class Raton
{
	String nombre;
	int tiempoCome;
	
	public Raton(String s, int t)
	{
		this.nombre = s;
		this.tiempoCome = t;
	}
	
	public void comer()
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
