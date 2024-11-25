package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String hostName;
	private String dataBase;
	private String usuario;
	private String password;

	public Conexion(String hostName, String dataBase, String usuario, String password) throws SQLException {
		this.hostName = hostName;
		this.dataBase = dataBase;
		this.usuario = usuario;
		this.password = password;
	}

	public Connection conexion() throws SQLException {
		Connection conn=null;
		String url = String.format("jdbc:mysql://%s/%s", hostName, dataBase);
		conn=DriverManager.getConnection(url, usuario, password);
		return conn;
	}
}
