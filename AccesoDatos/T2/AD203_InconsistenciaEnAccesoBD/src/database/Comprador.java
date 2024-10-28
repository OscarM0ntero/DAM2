// Hecho por Oscar Montero	28/10/2024
package database;

import java.sql.*;

//Clase que simula la compra de tickets
public class Comprador implements Runnable
{
	private int ticketsAComprar;

	public Comprador(int ticketsAComprar) {
		this.ticketsAComprar = ticketsAComprar;
	}

	@Override
	public void run()
	{
		try (Connection connection = DriverManager.getConnection(VentaTickets.URL))
		{
			connection.setAutoCommit(false); // Usar transacciones para evitar inconsistencias

			// Verificar el número de tickets disponibles
			int ticketsDisponibles = VentaTickets.getTicketsDisponibles();

			if (ticketsDisponibles >= ticketsAComprar)
			{
				// Restar el número de tickets solicitados
				int newAvailableTickets = ticketsDisponibles - ticketsAComprar;

				// Actualizar la base de datos
				try (PreparedStatement updateStmt = connection
						.prepareStatement("UPDATE tickets SET tickets_disponibles = ? WHERE id = 1"))
				{
					updateStmt.setInt(1, newAvailableTickets);
					int rowsAffected = updateStmt.executeUpdate();

					if (rowsAffected > 0)
					{
						connection.commit(); // Confirmar la transacción
						System.out
								.println(Thread.currentThread().getName() + " compró " + ticketsAComprar + " tickets.");
						synchronized (VentaTickets.class)
						{
							VentaTickets.totalTicketsVendidos += ticketsAComprar;
						}
					} else
					{
						connection.rollback(); // Revertir en caso de fallo
						System.out.println(Thread.currentThread().getName() + " no pudo completar la compra.");
					}
				}
			} else
			{
				System.out.println(Thread.currentThread().getName() + " no pudo comprar. No hay suficientes tickets.");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
