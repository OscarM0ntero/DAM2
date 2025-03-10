package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClienteHTTP {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Ingrese la URL (o 'salir' para terminar): ");
			String inputUrl = scanner.nextLine();

			if (inputUrl.equalsIgnoreCase("salir")) {
				break;
			}

			try {
				URL url = new URL(inputUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");

				int responseCode = connection.getResponseCode();
				System.out.println("Código de finalización: " + responseCode);

				System.out.println("Cabeceras de respuesta:");
				Map<String, List<String>> headers = connection.getHeaderFields();
				for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}

				System.out.println("\nContenido de la página:");
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder content = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine).append("\n");
				}
				in.close();

				System.out.println(content);

			} catch (IOException e) {
				System.out.println("Error al obtener la página: " + e.getMessage());
			}
		}

		scanner.close();
		System.out.println("Programa finalizado.");
	}
}
