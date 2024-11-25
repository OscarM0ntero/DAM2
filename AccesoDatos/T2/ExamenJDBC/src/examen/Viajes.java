package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Viajes
{
	private int idviajes;
	private String destino;
	private int totalplazas;
	
	public Viajes(int idViajes, String destino, int totalPlazas)
	{
		this.idviajes = idViajes;
		this.destino = destino;
		this.totalplazas = totalPlazas;
	}

	public int getIdviajes()
	{
		return idviajes;
	}

	public void setIdviajes(int idViajes)
	{
		this.idviajes = idViajes;
	}

	public String getDestino()
	{
		return destino;
	}

	public void setDestino(String destino)
	{
		this.destino = destino;
	}

	public int getTotalplazas()
	{
		return totalplazas;
	}

	public void setTotalplazas(int totalPlazas)
	{
		this.totalplazas = totalPlazas;
	}
	
	public static int plazaslibres(String destino)
	{
		int libres = -1;
		try (Connection connection = DriverManager.getConnection(Main.URL, Main.usuario, Main.contrasenia))
		{
			try (PreparedStatement stmt = connection.prepareStatement("SELECT totalplazas FROM viajes WHERE destino = ?"))
			{
				stmt.setString(1, destino);
				ResultSet rs = stmt.executeQuery();
				if (rs.next())
					libres = rs.getInt("totalplazas");
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return libres;
	}
	
	// suma o resta(si es negativa) la cifra que haya en la variable plazas al viaje
	public static void actualizarPlazas(int idviajes, int plazas)
	{
		try (Connection connection = DriverManager.getConnection(Main.URL, Main.usuario, Main.contrasenia))
		{
			int plazaslibres;
			String checkQuery = "SELECT totalplazas FROM viajes WHERE idviajes = ?";
			try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery))
			{
				checkStmt.setInt(1, idviajes);
				ResultSet rs = checkStmt.executeQuery();
				rs.next();
				plazaslibres = rs.getInt(1);
			}
			
			String updateQuery = "UPDATE viajes SET totalplazas = ? WHERE idviajes = ?";
			try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery))
			{
				updateStmt.setInt(1, plazaslibres+plazas);
				updateStmt.setInt(2, idviajes);
				updateStmt.executeUpdate();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
