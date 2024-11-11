package transacciones;

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

public class Pedido
{
	private int codcli;
	private String direcion;
	private int codart;
	private int cantidad;
	
	public Pedido(int codcli, String dir, int codart, int cantidad)
	{
		this.codcli = codcli;
		this.direcion = dir;
		this.codart = codart;
		this.cantidad = cantidad;
	}

	public int getCodcli()
	{
		return codcli;
	}

	public void setCodcli(int codcli)
	{
		this.codcli = codcli;
	}

	public String getDirecion()
	{
		return direcion;
	}

	public void setDirecion(String direcion)
	{
		this.direcion = direcion;
	}

	public int getCodart()
	{
		return codart;
	}

	public void setCodart(int codart)
	{
		this.codart = codart;
	}

	public int getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}
	
	public void ejecutar()
	{
		int existenciasArticulo = 0;
		try (Connection connection = DriverManager.getConnection(Tienda.URL, Tienda.usuario, Tienda.contrasenia))
		{
			connection.setAutoCommit(false);
			// Comprobamos que has suficiente existencias del articulo
			try (
					PreparedStatement stmt = connection.prepareStatement
					("SELECT existencias FROM articulos WHERE codart = ?")
				)
			{
				stmt.setInt(1, this.codart);
				ResultSet rs = stmt.executeQuery();
				if (rs.next())
				{
					existenciasArticulo = rs.getInt("existencias");
				}
			}
			
			// Si hay suficiente existencia, ejecutamos las operaciones
			if(existenciasArticulo >= this.cantidad)
			{
				int newExistencias = existenciasArticulo - this.cantidad;
				int rowsAffected = 0;
				boolean allGood = true;
				int codigoPedido = 0;
				int codigoRider = 0;
				
				// Insetartamos el pedido y actualizamos el stock
				try (
						PreparedStatement insertStmt = connection.prepareStatement
						("INSERT INTO pedidos (fecha, codcli, direccion, codart, cantidad) VALUES (?, ?, ?, ?, ?)");
						PreparedStatement updateStmt = connection.prepareStatement
						("UPDATE articulos SET existencias = ? WHERE codart = ?")
					)
				{
					insertStmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
					insertStmt.setInt(2, this.codcli);
					insertStmt.setString(3, this.direcion);
					insertStmt.setInt(4, this.codart);
					insertStmt.setInt(5, this.cantidad);
					updateStmt.setInt(1, newExistencias);
					updateStmt.setInt(2, this.codart);
					
					if (insertStmt.executeUpdate() == 0)
					{
						System.out.println("Pedido no insertado.");
						allGood = false;
					}
					if (updateStmt.executeUpdate() == 0)
					{
						System.out.println("Existencia no actualizada.");
						allGood = false;
					}
				}
				// Obtenemos el codped del pedido que acabamos de asignar
				try (
						PreparedStatement stmt = connection.prepareStatement
						(
							"SELECT max(codped) AS cod FROM pedidos"
						)
					)
				{
					ResultSet rs = stmt.executeQuery();
					if (rs.next())
					{
						codigoPedido = rs.getInt("cod");
					}
					System.out.println("Codigo pedido: " + codigoPedido);
				}
				// Obtenemos una lista de los riders disponibles
				try (
						PreparedStatement stmt = connection.prepareStatement
						(
							"SELECT * FROM riders WHERE disponible = 1"
						)
					)
				{
					ResultSet rs = stmt.executeQuery();
					List<Integer> ridersDisponibles = new ArrayList<Integer>();
					while (rs.next())
					{
						ridersDisponibles.add(rs.getInt("codrider"));
					}
					// Una vez tenemos una lista con los riders disponibles, seleccionamos uno aleatorio (si hay riders disponibles)
					if(ridersDisponibles.isEmpty())
					{
						System.out.println("Rider no disponible.");
						allGood = false;
					}
					else
						codigoRider = ridersDisponibles.get((new Random()).nextInt(ridersDisponibles.size()));
				}
				// Creamos el envio y ponemos al rider como no disponble
				try (
						PreparedStatement insertStmt = connection.prepareStatement
						("INSERT INTO envios (codped, codrider, terminado) VALUES (?, ?, 0)");
						PreparedStatement updateStmt = connection.prepareStatement
						("UPDATE riders SET disponible = ? WHERE codrider = ?")
					)
				{
					insertStmt.setInt(1, codigoPedido);
					insertStmt.setInt(2, codigoRider);
					updateStmt.setInt(1, 0);
					updateStmt.setInt(2, codigoRider);
					
					if (insertStmt.executeUpdate() == 0)
					{
						System.out.println("Envio no insertado.");
						allGood = false;
					}
					if (updateStmt.executeUpdate() == 0)
					{
						System.out.println("Estado de rider no actualizado.");
						allGood = false;
					}
				}
				
				if(allGood)
				{
					System.out.println("Pedido registrado.");
					connection.commit();
				}
				else
				{
					System.out.println("Pedido NO registrado.");
					connection.rollback();
				}
			}
			else
			{
				System.out.println("No hay stock suficiente para este pedido.");
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

