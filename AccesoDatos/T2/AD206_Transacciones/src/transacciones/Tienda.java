// Hecho por Oscar Montero	11/11/2024
package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Tienda
{
	protected static final String URL = "jdbc:mysql://localhost:3306/tienda";
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
			if(opc > 0 && opc < 3)
			{
				switch(opc)
				{
				case 1:
					realizarPedido();
					break;
				case 2:
					checkRiders();
					break;
				}
			}
			else if(opc != 0)
			{
				System.out.println("Opción incorrecta. Intentalo de nuevo.");
			}
		} while(opc != 0);
		System.out.println("Fin del programa.");
	}
	
	private static void mostrarMenu()
	{
		System.out.println("MENÚ");
		System.out.println("1. Realizar pedido");
		System.out.println("2. Ver riders disponibles");
		System.out.println("0. Salir");
		System.out.println("Opción: ");
	}
	
	private static void realizarPedido()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("NUEVO PEDIDO");
		System.out.println("Código cliente: ");
		int codcli = input.nextInt();
		input.nextLine();
		System.out.println("Dirección: ");
		String dir = input.nextLine();
		System.out.println("Código artículo: ");
		int codart = input.nextInt();
		System.out.println("Cantidad: ");
		int cantidad = input.nextInt();
		
		Pedido p = new Pedido(codcli, dir, codart, cantidad);
		p.ejecutar();
	}
	
	private static void checkRiders()
	{
		try (Connection connection = DriverManager.getConnection(Tienda.URL, Tienda.usuario, Tienda.contrasenia))
		{
			try (
					PreparedStatement stmt = connection.prepareStatement
					(
						"SELECT * FROM riders WHERE disponible = 1"
					)
				)
			{
				ResultSet rs = stmt.executeQuery();
				System.out.println("LISTADO DE RIDERS DISPONIBLES");
				while (rs.next())
				{
					System.out.printf("Código: %d Nombre: %s\n", rs.getInt("codrider"), rs.getString("nombre"));
				}
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}