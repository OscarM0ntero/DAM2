package database;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Login {
	/*
	 * 	protected static final String URL = "jdbc:mysql://localhost:3306/tienda";
		protected static final String usuario = "java";
		protected static final String contrasenia = "java12345678";
	 * */
    private static final String URL = "jdbc:sqlite:usuarios.db";


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\nMENU DE OPCIONES:");
                System.out.println("1. Alta de usuario");
                System.out.println("2. Entrada de usuario");
                System.out.println("3. Listado de entradas");
                System.out.println("4. FIN");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        altaUsuario(conn, scanner);
                        break;
                    case 2:
                        entradaUsuario(conn, scanner);
                        break;
                    case 3:
                        listadoEntradas(conn, scanner);
                        break;
                    case 4:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } while (opcion != 4);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cifrar una cadena con MD5
    private static String cifrarMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(input.getBytes());

            // Convertir el byte array a un string hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar la contraseña en MD5", e);
        }
    }

    // Alta de usuario
    private static void altaUsuario(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el nombre de login: ");
        String nombrelogin = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese el nombre completo: ");
        String nombrecompleto = scanner.nextLine();

        // Cifrar la contraseña usando MD5
        String contrasenaCifrada = cifrarMD5(contrasena);

        String checkQuery = "SELECT COUNT(*) FROM usuarios WHERE nombrelogin = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, nombrelogin);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("El nombre de login ya está en uso. Elija otro.");
                return;
            }
        }

        String insertQuery = "INSERT INTO usuarios (nombrelogin, contrasena, nombrecompleto) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, nombrelogin);
            insertStmt.setString(2, contrasenaCifrada);
            insertStmt.setString(3, nombrecompleto);
            insertStmt.executeUpdate();
            System.out.println("Usuario registrado con éxito.");
        }
    }

    // Entrada de usuario
    private static void entradaUsuario(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el nombre de login: ");
        String nombrelogin = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        // Cifrar la contraseña ingresada para compararla con la almacenada en la BD
        String contrasenaCifrada = cifrarMD5(contrasena);

        String selectQuery = "SELECT coduser, contrasena FROM usuarios WHERE nombrelogin = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
            selectStmt.setString(1, nombrelogin);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int codUsuario = rs.getInt("coduser");
                String storedPassword = rs.getString("contrasena");

                if (storedPassword.equals(contrasenaCifrada)) {
                    String insertEntradaQuery = "INSERT INTO entradas (cod_usuario, hora_ultima_entrada) VALUES (?, ?)";
                    try (PreparedStatement insertEntradaStmt = conn.prepareStatement(insertEntradaQuery)) {
                        insertEntradaStmt.setInt(1, codUsuario);
                        insertEntradaStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                        insertEntradaStmt.executeUpdate();
                        System.out.println("Entrada registrada con éxito.");
                    }
                } else {
                    System.out.println("Contraseña incorrecta.");
                    logError(nombrelogin);
                }
            } else {
                System.out.println("Usuario no encontrado.");
                logError(nombrelogin);
            }
        }
    }

    // Listado de entradas por usuario
    private static void listadoEntradas(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el nombre de login: ");
        String nombrelogin = scanner.nextLine();

        String selectQuery = "SELECT e.hora_ultima_entrada FROM entradas e " +
                             "JOIN usuarios u ON e.cod_usuario = u.coduser WHERE u.nombrelogin = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
            selectStmt.setString(1, nombrelogin);
            ResultSet rs = selectStmt.executeQuery();

            System.out.println("Entradas registradas para el usuario " + nombrelogin + ":");
            while (rs.next()) {
                System.out.println(rs.getTimestamp("hora_ultima_entrada"));
            }
        }
    }

    // Registrar errores en un archivo de texto
    private static void logError(String nombrelogin) {
        try (FileWriter writer = new FileWriter("errores.txt", true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("Error de login - " + timestamp + " - Usuario: " + nombrelogin + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
