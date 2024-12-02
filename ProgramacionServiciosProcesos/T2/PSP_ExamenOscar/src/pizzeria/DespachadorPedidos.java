// Hecho por Oscar Montero

package pizzeria;

import java.util.Random;

// El despachador irá preparando los pedidos y vaciando la cola
class DespachadorPedidos implements Runnable
{
	private final Cola colaPedidos;
	private final Random random = new Random();
	private final AgregadorPedidos ag1;
	private final AgregadorPedidos ag2;

	public DespachadorPedidos(Cola colaPedidos, AgregadorPedidos ag1, AgregadorPedidos ag2)
	{
		this.colaPedidos = colaPedidos;
		this.ag1 = ag1;
		this.ag2 = ag2;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(1000); // Esperamos 1 segundo para que haya algo en la lista
			// Iremos comprobando si hay pedidos en la cola, si no hay espera 2 segundos por
			// si se añade uno nuevo
			// Si no se añade uno en los 2 segundos el despachador termina
			while (!colaPedidos.isEmpty())
			{
				despacharPedidos();
				Thread.sleep(2000); // Esperamos un tiempo de cortesia por si se añade algun otro producto
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(GestorColas.colores[5] + "El despachador ha entregado todos los pedidos.");

	}

	// Funcion de despachar pedidos
	private void despacharPedidos() throws InterruptedException
	{
		while (!colaPedidos.isEmpty())
		{
			String[] pedido = colaPedidos.despacharPedido();
			System.out.println(GestorColas.colores[7] + "El despachador entrega un pedido: " + pedido[0]
					+ GestorColas.colores[7] + " y el próximo será: " + pedido[1]);
			Thread.sleep(random.nextInt(1000) + 500); // Tiempo que se tarda en despachar cada pedido
		}

	}
}
