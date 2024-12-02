// Hecho por Oscar Montero

package pizzeria;

import java.util.Random;

// El agregador agregará pedidos a la cola, el número que le indiquemos de pedidos
class AgregadorPedidos implements Runnable
{
	private final int id;
	private final Cola colaPedidos;
	private final int cantidadPedidos;
	private final String color;
	private final String[] nombresPedidos =
	{ "Agua", "Pan", "Tomates", "Queso", "Atun", "Aceite", "Leche" };
	private final Random random = new Random();

	public AgregadorPedidos(int id, Cola colaPedidos, int cantidadPedidos)
	{
		this.id = id;
		this.colaPedidos = colaPedidos;
		this.cantidadPedidos = cantidadPedidos;
		this.color = GestorColas.colores[id + 1];
	}

	@Override
	public void run()
	{
		try
		{
			for (int i = 0; i < cantidadPedidos; i++)
			{
				String pedido = nombresPedidos[random.nextInt(nombresPedidos.length)]; // Se crea un pedido random
				colaPedidos.agregarPedido(id, color, pedido); // Se agrega
				Thread.sleep(random.nextInt(500) + 500); // Tiempo que se tarda en agregar cada pedido
			}
			System.out.println(color + "El agregador " + id + " ha terminado de añadir todos los pedidos.");
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
	}
}
