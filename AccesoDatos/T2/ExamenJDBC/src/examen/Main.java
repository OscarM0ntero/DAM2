package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main
{
	public static String URL = "jdbc:mysql://localhost:3306/examenjdbc";
	public static final String usuario = "oscar";
	public static final String contrasenia = "Examen2024";

	public static void main(String[] args)
	{
		try (Connection connection = DriverManager.getConnection(URL, usuario, contrasenia))
		{
			connection.setAutoCommit(false);
			Scanner input = new Scanner(System.in);
			int opc;
			do
			{
				System.out.println("MENÚ");
				System.out.println("1. Alta de viaje");
				System.out.println("2. Crear reserva");
				System.out.println("3. Cancelar reserva");
				System.out.println("0. Salir");
				System.out.println("Opción: ");
				opc = input.nextInt();
				input.nextLine();
				if (opc > 0 && opc < 4)
				{
					switch (opc)
					{
					case 1:
						altaViaje(connection, input);
						connection.commit();
						break;
					case 2:
						crearReserva(connection, input);
						connection.commit();
						break;
					case 3:
						cancelarReserva(connection, input);
						connection.commit();
						break;
					}
				}
				else if (opc != 0)
				{
					System.out.println("Opción incorrecta. Intentalo de nuevo.");
				}
			} while (opc != 0);
			input.close();
			System.out.println("Fin del programa.");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Alta de viajes
	private static void altaViaje(Connection connection, Scanner input) throws SQLException
	{
		System.out.print("Ingrese el destino: ");
		String destino = input.nextLine();
		System.out.print("Ingrese el total de plazas: ");
		int totalplazas = input.nextInt();

		String checkQuery = "SELECT COUNT(*) FROM viajes WHERE destino = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setString(1, destino);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0)
			{
				System.out.println("El destino ya está registrado.");
				return;
			}
		}

		String insertQuery = "INSERT INTO viajes (destino, totalplazas) VALUES (?, ?)";
		try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery))
		{
			insertStmt.setString(1, destino);
			insertStmt.setInt(2, totalplazas);
			insertStmt.executeUpdate();
			System.out.println("Viaje registrado con éxito.");
		}
	}

	// Crear reserva
	private static void crearReserva(Connection connection, Scanner input) throws SQLException
	{
		System.out.print("Ingrese el destino: ");
		String destino = input.nextLine();
		System.out.print("Ingrese el código del cliente: ");
		int idclientes = input.nextInt();
		System.out.print("Ingrese la cantidad de plazas: ");
		int plazas = input.nextInt();

		int numviaje = -1;
		// comprobamos que hay un viaje al destino
		String checkQuery = "SELECT idviajes FROM viajes WHERE destino = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setString(1, destino);
			ResultSet rs = checkStmt.executeQuery();
			if (!rs.next())
			{
				System.out.println("No hay ningun viaje a este destino.");
				return;
			}
			else
			{
				numviaje = rs.getInt(1);
			}
		}
		
		// comprobamos que el cliente existe
		checkQuery = "SELECT COUNT(*) FROM clientes WHERE idclientes = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setInt(1, idclientes);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0)
			{
				System.out.println("El cliente no existe.");
				return;
			}
		}
		
		int plazaslibres = Viajes.plazaslibres(destino);
		
		// Si hay plazas suficientes, se asigna Adjudicado, sino Espera
		String estado = (plazaslibres-plazas>=0)?"A":"E";
		
		// insertamos la reserva
		String insertQuery = "INSERT INTO reservas (idcliente, fecha, plazas, estado, numviaje) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery))
		{
			insertStmt.setInt(1, idclientes);
			insertStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			insertStmt.setInt(3, plazas);
			insertStmt.setString(4, estado);	
			insertStmt.setInt(5, numviaje);
			insertStmt.executeUpdate();
			System.out.println("Reserva registrado con éxito.");
		}
		
		// Si se han adjudicado, descontamos las plazas utilizadas del viaje
		if(estado.equals("A"))
		{
			Viajes.actualizarPlazas(numviaje, plazas*(-1));
		}
	}
	
	// Cancelar reserva
	private static void cancelarReserva(Connection connection, Scanner input) throws SQLException
	{
		System.out.print("Ingrese el número de reserva: ");
		int idreservas = input.nextInt();
		System.out.print("Ingrese el código del cliente: ");
		int idcliente = input.nextInt();

		int numviaje = -1;
		// comprobamos que la reserva existe
		String checkQuery = "SELECT COUNT(*) FROM reservas WHERE idreservas = ? and idcliente = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setInt(1, idreservas);
			checkStmt.setInt(2, idcliente);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0)
			{
				System.out.println("Los datos no son correctos.");
				return;
			}
		}
		// obtenemos el numviaje de la reserva
		checkQuery = "SELECT numviaje FROM reservas WHERE idreservas = ? and idcliente = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setInt(1, idreservas);
			checkStmt.setInt(2, idcliente);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			numviaje = rs.getInt(1);
		}
		
		// Comprobamos el estado de la reserva
		String estado;
		int plazas;
		checkQuery = "SELECT plazas FROM reservas WHERE idreservas = ? and idcliente = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setInt(1, idreservas);
			checkStmt.setInt(2, idcliente);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			plazas = rs.getInt(1);
		}
		
		checkQuery = "SELECT estado FROM reservas WHERE idreservas = ? and idcliente = ?";
		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
		{
			checkStmt.setInt(1, idreservas);
			checkStmt.setInt(2, idcliente);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			estado = rs.getString(1);
		}
		// Si estaba adjudicada, liberamos las plazas
		if (estado.equals("A"))
		{
			Viajes.actualizarPlazas(numviaje, plazas);
		}
		
		// si los datos son correctos, actualizamos el estado de la reserva a "C"
		String updateQuery = "UPDATE reservas SET estado = ? WHERE idreservas = ?";
		try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery))
		{
			updateStmt.setString(1, "C");
			updateStmt.setInt(2, idreservas);
			updateStmt.executeUpdate();
		}
		
		actualizarReservasEspera(connection, idreservas, plazas);
	}
	
	private static void actualizarReservasEspera(Connection connection, int idReservaCancelada, int plazasliberadas) throws SQLException
	{
		if(plazasliberadas == 0)
			return;
		try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reservas WHERE estado = 'E' and plazas <= ?"))
		{
			stmt.setInt(1, plazasliberadas);
			ResultSet rs = stmt.executeQuery();
			List<Integer> reservasEspera = new ArrayList<Integer>();
			List<Timestamp> reservasEsperaFecha = new ArrayList<Timestamp>();
			while (rs.next())
			{
				reservasEspera.add(rs.getInt(1));
				reservasEsperaFecha.add(rs.getTimestamp(3));
			}
			if(reservasEspera.size()==0)
				return;
			int prioritario = 0;
			for(int i = 0; i < reservasEsperaFecha.size(); i++)
			{
				if(reservasEsperaFecha.get(i).compareTo(reservasEsperaFecha.get(prioritario))>0)
					prioritario = i;
			}
			
			if(prioritario != -1)
			{
				String updateQuery = "UPDATE reservas SET estado = ? WHERE idreservas = ?";
				try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery))
				{
					updateStmt.setString(1, "A");
					updateStmt.setInt(2, reservasEspera.get(prioritario));
					updateStmt.executeUpdate();
				}
				int numviaje;
				String checkQuery = "SELECT numviaje FROM reservas WHERE idreservas = ?";
				try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
				{
					checkStmt.setInt(1, reservasEspera.get(prioritario));
					rs = checkStmt.executeQuery();
					rs.next();
					numviaje = rs.getInt(1);
				}
				
				int plazas;
				checkQuery = "SELECT plazas FROM reservas WHERE idreservas = ?";
				try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
				{
					checkStmt.setInt(1, reservasEspera.get(prioritario));
					rs = checkStmt.executeQuery();
					rs.next();
					plazas = rs.getInt(1);
				}
				
				Viajes.actualizarPlazas(numviaje, plazas);
				actualizarReservasEspera(connection, reservasEspera.get(prioritario), plazas);
			}
		}
	}
}
