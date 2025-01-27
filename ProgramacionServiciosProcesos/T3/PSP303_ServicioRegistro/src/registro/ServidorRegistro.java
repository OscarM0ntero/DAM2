package registro;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServidorRegistro {
	private static final int PUERTO = 5000;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/registro_db";
	private static final String DB_USER = "registro";
	private static final String DB_PASSWORD = "1234";

	public static void main(String[] args) {
		try (ServerSocket servidor = new ServerSocket(PUERTO)) {
			System.out.println("Servidor de registro escuchando en el puerto " + PUERTO);

			while (true) {
				try (Socket cliente = servidor.accept();
						BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

					System.out.println("Cliente conectado desde " + cliente.getInetAddress());

					String mensaje = entrada.readLine();
					if (mensaje == null) {
						System.out.println("Cliente desconectado antes de enviar datos.");
						continue;
					}
					
					mensaje = mensaje.trim().toLowerCase();
					
					if (mensaje.startsWith("soy ")) {
						String nombre = mensaje.substring(4).trim();
						if (usuarioExiste(nombre)) {
							registrarUsuario(nombre, cliente.getInetAddress().getHostAddress());
							salida.println("Registro realizado correctamente");
						} else {
							salida.println("Nombre desconocido");
						}
					} else {
						salida.println("Protocolo incorrecto");
					}

					System.out.println("Conexión cerrada con " + cliente.getInetAddress());

				} catch (IOException e) {
					System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.err.println("Error al iniciar el servidor: " + e.getMessage());
		}
	}

	private static boolean usuarioExiste(String nombre) {
		String sql = "SELECT id FROM usuarios WHERE user = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			System.err.println("Error al verificar usuario: " + e.getMessage());
			return false;
		}
	}

	private static void registrarUsuario(String nombre, String ip) {
		String sql = "INSERT INTO registro (id_user, ip, fechahora) "
				+ "VALUES ((SELECT id FROM usuarios WHERE user = ?), ?, ?)";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, nombre);
			pstmt.setString(2, ip);
			pstmt.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			pstmt.executeUpdate();
			System.out.println("Registro guardado: " + nombre + " desde " + ip);

		} catch (SQLException e) {
			System.err.println("Error al registrar usuario: " + e.getMessage());
		}
	}
}
