package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
		int stockArticulo = 0;
		try (Connection connection = DriverManager.getConnection(Tienda.URL, Tienda.usuario, Tienda.contrasenia);
				PreparedStatement stmt = connection
						.prepareStatement(String.format("SELECT existencias FROM articulos WHERE codart = %d", this.cantidad)))
		{
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
			{
				stockArticulo = rs.getInt("existencias");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(stockArticulo >= this.cantidad)
		{
			try (Connection connection = DriverManager.getConnection(Tienda.URL, Tienda.usuario, Tienda.contrasenia))
			{
				connection.setAutoCommit(false);
				
					// Restar el número de tickets solicitados
					int newAvailableTickets = ticketsDisponibles - ticketsAComprar;

					// Actualizar la base de datos
					try (PreparedStatement stmt = connection
							.prepareStatement("INSERT INTO pedidos (fecha, codcli, direccion, codart, cantidad) VALUES (?, ?, ?, ?, ?)"))
					{
						stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
						stmt.setInt(2, this.codcli);
						stmt.setString(3, this.direcion);
						stmt.setInt(4, this.codart);
						stmt.setInt(5, this.cantidad);
						
						int rowsAffected = stmt.executeUpdate();

						if (rowsAffected > 0)
						{
							connection.commit();
							System.out.println(Thread.currentThread().getName() + " compró " + ticketsAComprar + " tickets.");
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
		else
		{
			System.out.println("No hay stock suficiente para este pedido.");
		}
	}
	
}
