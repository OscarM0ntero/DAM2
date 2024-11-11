// Hecho por Oscar Montero	11/11/2024
package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Tienda
{
	protected static final String URL = "jdbc:mysql://127.0.0.1:3306/tienda";
	protected static final String usuario = "java";
	protected static final String contrasenia = "java12345678";
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int opc;
		do
		{
			mostrarMenu();
			opc = input.nextInt();
			if(opc > 0 && opc < 4)
			{
				switch(opc)
				{
				case 1:
					realizarPedido();
					break;
				case 2:
					registrarRider();
					break;
				case 3:
					checkRiders();
					break;
				}
			}
			else if(opc != 0)
			{
				System.out.println("Opción incorrecta. Intentalo de nuevo.");
			}
		} while(opc != 0);
		
		try (Connection connection = DriverManager.getConnection(URL, usuario, contrasenia))
		{
			connection.setAutoCommit(false);

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
						synchronized (Tienda.class)
						{
							Tienda.totalTicketsVendidos += ticketsAComprar;
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
	
	private static void mostrarMenu()
	{
		System.out.println("MENÚ");
		System.out.println("1. Realizar pedido");
		System.out.println("2. Registrar rider");
		System.out.println("3. Ver riders disponibles");
		System.out.println("0. Salir");
		System.out.println("Opción: ");
	}
	
	private static void realizarPedido()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("NUEVO PEDIDO");
		System.out.println("Código cliente: ");
		int codcli = input.nextInt();
		System.out.println("Dirección: ");
		String dir = input.nextLine();
		System.out.println("Código artículo: ");
		int codart = input.nextInt();
		System.out.println("Cantidad: ");
		int cantidad = input.nextInt();
		
		Pedido p = new Pedido(codcli, dir, codart, cantidad);
		
	}
	
	private static void regisrarRider()
	{
		
	}
	
	private static void checkRider()
	{
		
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
