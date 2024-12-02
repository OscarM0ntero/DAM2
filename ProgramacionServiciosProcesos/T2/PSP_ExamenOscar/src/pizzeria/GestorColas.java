// Hecho por Oscar Montero

package pizzeria;

class GestorColas
{
	public static String[] colores =
	{ "\u001B[0m", // RESET
			"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m",
			"\u001B[30m", "\u001B[90m", "\u001B[91m" };

	public static void main(String[] args)
	{
		Cola colaPedidos = new Cola();
		AgregadorPedidos agregadorPedidos1 = new AgregadorPedidos(1, colaPedidos, 10);
		AgregadorPedidos agregadorPedidos2 = new AgregadorPedidos(2, colaPedidos, 15);
		DespachadorPedidos despachadorPedidos = new DespachadorPedidos(colaPedidos, agregadorPedidos1,
				agregadorPedidos2);

		Thread hiloAGP1 = new Thread(agregadorPedidos1);
		Thread hiloAGP2 = new Thread(agregadorPedidos2);
		Thread hiloDP = new Thread(despachadorPedidos);

		hiloAGP1.start();
		hiloAGP2.start();
		hiloDP.start();
	}
}