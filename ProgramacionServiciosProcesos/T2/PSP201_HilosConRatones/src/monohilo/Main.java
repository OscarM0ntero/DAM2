// Hecho por Oscar Montero	14/10/2024
package monohilo;

public class Main {
	
	public static void main(String[] args)
	{
		Raton tinky = new Raton("Tinky", 4);
		Raton winky = new Raton("Winky", 2);
		Raton poo = new Raton("Pooo", 1);
		Raton lala = new Raton("Lalala", 5);
		
		long time = System.currentTimeMillis();
				
		tinky.comer();
		winky.comer();
		poo.comer();
		lala.comer();
		
		time = System.currentTimeMillis() - time;
		
		double tiempoSegundos = (double)time / 1000;
		
		System.out.printf("Hemos tardado %.3f segundos", tiempoSegundos);
		
	}

}
