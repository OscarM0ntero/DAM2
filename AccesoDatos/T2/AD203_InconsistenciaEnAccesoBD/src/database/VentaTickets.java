// Hecho por Oscar Montero	28/10/2024
package database;

import java.sql.*;
public class VentaTickets
{
	protected static final String URL = "jdbc:sqlite:tickets.db"; // Cambia la URL según tu configuración
	protected static int totalTicketsVendidos = 0;

	public static void main(String[] args)
	{
		// Ajustamos la cantidad de entradas a 100
		actualizarCantidadTickets(1, 100);

		// Crear tres hilos para simular la compra de tickets
		Thread comprador1 = new Thread(new Comprador(5)); // Compra 5 tickets
		Thread comprador2 = new Thread(new Comprador(3)); // Compra 3 tickets
		Thread comprador3 = new Thread(new Comprador(7)); // Compra 7 tickets

		// Iniciar los hilos
		comprador1.start();
		comprador2.start();
		comprador3.start();

		// Esperar a que los hilos terminen
		try
		{
			comprador1.join();
			comprador2.join();
			comprador3.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// Imprimir resultados finales
		System.out.println("Total tickets vendidos: " + totalTicketsVendidos);
		System.out.println("Tickets disponibles al final: " + getTicketsDisponibles());
	}
	
	// Método para insertar un nuevo registro en la tabla tickets
	// Método para actualizar la cantidad de tickets disponibles
    public static void actualizarCantidadTickets(int ticketId, int ticketsDisponibles) {
        // Consulta SQL para actualizar el registro
        String sql = "UPDATE tickets SET tickets_disponibles = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            // Establecer los valores de los parámetros de la consulta
            preparedStatement.setInt(1, ticketsDisponibles);
            preparedStatement.setInt(2, ticketId);

            // Ejecutar la consulta
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// Obtener el número de tickets disponibles
	protected static int getTicketsDisponibles()
	{
		int ticketsDisponibles = 0;
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement stmt = connection
						.prepareStatement("SELECT tickets_disponibles FROM tickets WHERE id = 1"))
		{
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
			{
				ticketsDisponibles = rs.getInt("tickets_disponibles");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return ticketsDisponibles;
	}
}
