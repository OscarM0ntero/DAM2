// Hecho por Oscar Montero	14/10/2024
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:clientes.db");
				Statement statement = connection.createStatement();)
		{
			while(true)
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
					} while (rs.next());
				}
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}

	}

}
