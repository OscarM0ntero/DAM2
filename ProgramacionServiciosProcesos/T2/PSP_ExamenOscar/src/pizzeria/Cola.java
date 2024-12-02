// Hecho por Oscar Montero

package pizzeria;

import java.util.LinkedList;
import java.util.List;

class Cola
{
	private final List<String> pedidos = new LinkedList<>();

	public Cola()
	{
	}

	// Esta función permite agregar un pedido a la cola
	public synchronized void agregarPedido(int id, String color, String articulo) throws InterruptedException
	{
		pedidos.add(color + articulo);
		System.out.println(GestorColas.colores[6] + "El agregador " + id + " ha añadido un pedido: " + color + articulo
				+ GestorColas.colores[6] + " con puesto en cola: " + pedidos.size());
		notifyAll();
	}

	// Esta función permite quitar un pedido de la cola, devuelve un array con el
	// pedido entregado y el proximo
	public synchronized String[] despacharPedido() throws InterruptedException
	{
		while (pedidos.isEmpty())
		{
			wait();
		}
		String pedido = pedidos.remove(0);
		String siguientePedido = "";
		// Controlamos si hay o no siguiente pedido
		if(!pedidos.isEmpty())
			siguientePedido = pedidos.get(0);
		notifyAll();
		String[] result =
		{ pedido, siguientePedido };
		return result;
	}

	// Esta función nos permite controlar si la cola está vacía o no
	public synchronized boolean isEmpty()
	{
		boolean vacia = false;
		if (pedidos.isEmpty())
			vacia = true;
		return vacia;
	}
}