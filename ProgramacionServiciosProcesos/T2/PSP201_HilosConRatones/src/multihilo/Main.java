// Hecho por Oscar Montero	14/10/2024
package multihilo;
public class Main {
	
	public static void main(String[] args)
	{
		Raton tinky = new Raton("Tinky", 4);
		Raton winky = new Raton("Winky", 2);
		Raton poo = new Raton("Pooo", 1);
		Raton lala = new Raton("Lalala", 5);
		
		long time = System.currentTimeMillis();
				
		tinky.start();
		winky.start();
		poo.start();
		lala.start();
		
		try
		{
			tinky.join();
			winky.join();
			poo.join();
			lala.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		
		time = System.currentTimeMillis() - time;
		
		double tiempoSegundos = (double)time / 1000;
		
		System.out.printf("Hemos tardado %.3f segundos", tiempoSegundos);
		
	}

}