// Realizado por Oscar Montero

package pitufos;

// Este contador imprimira por pantalla cada 1 segundo la cantidad de filosofos que hay en el comedor
public class ContadorPitufos implements Runnable
{
	public boolean funcionando;

	public ContadorPitufos()
	{
		this.funcionando = true;
	}

	@Override
	public void run()
	{
		try
		{
			while (this.funcionando)
			{
				System.out.println("\u001B[93m" + Pitufo.tmp() + "Hay " + Comedor.cantidadDePitufosEnComedor
						+ " pitufos en el comedor.");
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
	}
}