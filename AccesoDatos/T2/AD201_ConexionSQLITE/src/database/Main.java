// Hecho por Oscar Montero	14/10/2024
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:clientes.db");
				Statement statement = connection.createStatement();)
		{
			menu(statement);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		System.out.println("Fin del programa.");
				
	}
	
	private static void menu(Statement statement) throws SQLException
	{
		Scanner input = new Scanner(System.in);
		int opt = 0;
		while(opt != 3)
		{
			System.out.println("MENU\n----");
			System.out.println("1. Registrar cliente");
			System.out.println("2. Buscar cliente");
			System.out.println("3. Salir");
			try
			{
				opt = input.nextInt();
			}
			catch(InputMismatchException ex)
			{
				input.nextLine();
				System.out.println("Opcion erronea.");
			}
			if(opt > 0 && opt < 4)
			{
				switch(opt)
				{
				case 1:
					registrarCliente(statement);
					break;
				case 2:
					buscarCliente(statement);
					break;
				default:
				}
			}
			else
				System.out.println("Opción erronea.");
		}
	}

	private static void registrarCliente(Statement statement)  throws SQLException
	{
		Scanner input = new Scanner(System.in);
		System.out.println("REGISTRO DE CLIENTE");
		System.out.print("Nombre: ");
		String nombre = input.nextLine();
		System.out.print("Teléfono: ");
		String telefono = input.nextLine();
		System.out.print("Email: ");
		String email = input.nextLine();
		
		statement.executeUpdate(String.format("insert into cliente (nombre, telefono, email) values ('%s','%s','%s')", nombre, telefono, email));
	}
	
	private static void buscarCliente(Statement statement) throws SQLException
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Nombre a buscar: ");
		String nombre = input.nextLine();
		
		statement.setQueryTimeout(30);
		ResultSet rs = statement.executeQuery(String.format("select * from cliente where nombre like '%s' or nombre like '%% %s' or nombre like '%s %%'", nombre, nombre, nombre));
		if(!rs.next())
			System.out.println("Nombre no encontrado.");
		else
		{
			do {
				System.out.println("Codigo = " + rs.getInt("codigo"));
				System.out.println("Nombre = " + rs.getString("nombre"));
				System.out.println("Teléfono = " + rs.getString("telefono"));
				System.out.println("Email = " + rs.getString("email"));
				System.out.println();
			} while (rs.next());
		}
		System.out.println();
	}
	
}
