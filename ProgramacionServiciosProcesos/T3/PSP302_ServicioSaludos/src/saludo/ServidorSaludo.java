package saludo;

import java.io.*;
import java.net.*;
import java.util.Random;

public class ServidorSaludo {
    private static final int PUERTO = 5000;
    private static final String[] SALUDOS = {
        "Hola, %s! Bienvenido!",
        "Es un placer saludarte, %s.",
        "Un gusto verte, %s!",
        "Hola, %s. Espero que tengas un gran dia."
    };

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                try (Socket cliente = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

                    System.out.println("Cliente conectado desde " + cliente.getInetAddress());

                    // Enviar la pregunta al cliente
                    salida.println("¿Cuál es tu nombre?");

                    // Recibir el nombre del cliente
                    String nombre = entrada.readLine();
                    if (nombre == null || nombre.trim().isEmpty()) {
                        System.out.println("No se recibió un nombre válido.");
                        salida.println("Error: No se recibió un nombre válido.");
                        continue;
                    }

                    // Generar un saludo aleatorio
                    String saludo = SALUDOS[new Random().nextInt(SALUDOS.length)];
                    salida.println(String.format(saludo, nombre));

                    System.out.println("Saludo enviado a " + cliente.getInetAddress() + ": " + nombre);
                } catch (IOException e) {
                    System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
