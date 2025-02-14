// Oscar Montero 12/02/2025
package cliente;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClienteAPI
{
	private static final String API_KEY = "3b5faf6c57b79d39e76351663a5e0cbe";
	private static final String BASE_URL = "https://api.themoviedb.org/3";
	private static final OkHttpClient client = new OkHttpClient();

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int option = 0;

		do
		{
			System.out.println("\nMenú de opciones:");
			System.out.println("1. Buscar película por título");
			System.out.println("2. Obtener detalles de una película por ID");
			System.out.println("3. Listar películas populares");
			System.out.println("4. Listar películas por género");
			System.out.println("5. Salir");
			System.out.print("Seleccione una opción: ");

			try
			{
				option = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e)
			{
				System.out.println("Por favor, ingresa un número válido.");
				scanner.nextLine();
				continue;
			}

			switch (option)
			{
			case 1:
				System.out.print("Ingrese el título de la película: ");
				String title = scanner.nextLine();
				searchMovieByTitle(title);
				break;
			case 2:
				int id = 0;
				try
				{
					System.out.print("Ingrese el ID de la película: ");
					id = scanner.nextInt();
					scanner.nextLine();
					getMovieDetails(id);
				} catch (InputMismatchException e)
				{
					System.out.println("Por favor, ingresa un ID válido.");
					scanner.nextLine();
				}
				break;
			case 3:
				listPopularMovies();
				break;
			case 4:
				int genreId = 0;
				try
				{
					System.out.print("Ingrese el ID del género: ");
					genreId = scanner.nextInt();
					scanner.nextLine();
					listMoviesByGenre(genreId);
				} catch (InputMismatchException e)
				{
					System.out.println("Por favor, ingresa un ID de género válido.");
					scanner.nextLine();
				}
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
			}
		} while (option != 5);

		scanner.close();
	}

	// busca una pelicula por titulo y llama al metodo de solicitud con la url correspondiente
	private static void searchMovieByTitle(String title)
	{
		String url = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + title.replace(" ", "+");
		makeRequest(url, "Resultados de la búsqueda");
	}

	// obtiene los detalles de una pelicula utilizando su id
	private static void getMovieDetails(int movieId)
	{
		String url = BASE_URL + "/movie/" + movieId + "?api_key=" + API_KEY;
		makeRequest(url, "Detalles de la película");
	}

	// lista las peliculas populares
	private static void listPopularMovies()
	{
		String url = BASE_URL + "/movie/popular?api_key=" + API_KEY;
		makeRequest(url, "Películas populares");
	}

	// lista las peliculas por genero
	private static void listMoviesByGenre(int genreId)
	{
		String url = BASE_URL + "/discover/movie?api_key=" + API_KEY + "&with_genres=" + genreId;
		makeRequest(url, "Películas del género seleccionado");
	}

	// realiza la solicitud http y procesa la respuesta json
	private static void makeRequest(String url, String description)
	{
		Request request = new Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute())
		{
			if (!response.isSuccessful())
			{
				System.out.println("Error en la solicitud: " + response);
				return;
			}
			String responseData = response.body().string();
			JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
			System.out.println("\n" + description + ":");
			mostrarOrdenado(jsonObject);
		} catch (IOException e)
		{
			System.out.println("Error al realizar la solicitud: " + e.getMessage());
		}
	}

	// procesa el objeto json para mostrar la informacion de la pelicula
	private static void mostrarOrdenado(JsonObject jsonObject)
	{
		// si el json contiene "title", es una respuesta de detalle de una pelicula
		if (jsonObject.has("title"))
		{
			JsonObject movie = jsonObject;
			System.out.println("\n--------------------------------");
			System.out.println("Título: " + movie.get("title").getAsString());
			System.out.println("ID: " + movie.get("id").getAsInt());
			System.out.println("Idioma original: " + movie.get("original_language").getAsString());
			System.out.println("Fecha de estreno: " + movie.get("release_date").getAsString());
			System.out.println("Puntuación promedio: " + movie.get("vote_average").getAsDouble());
			System.out.println("Votos: " + movie.get("vote_count").getAsInt());
			System.out.println("Resumen: " + movie.get("overview").getAsString());
			System.out.println("--------------------------------\n");
		} else if (jsonObject.has("results"))
		{
			// si contiene "results", es una respuesta de busqueda por nombre
			JsonArray results = jsonObject.getAsJsonArray("results");
			for (JsonElement element : results)
			{
				JsonObject movie = element.getAsJsonObject();
				System.out.println("\n--------------------------------");
				System.out.println("Título: " + movie.get("title").getAsString());
				System.out.println("ID: " + movie.get("id").getAsInt());
				System.out.println("Idioma original: " + movie.get("original_language").getAsString());
				System.out.println("Fecha de estreno: " + movie.get("release_date").getAsString());
				System.out.println("Puntuación promedio: " + movie.get("vote_average").getAsDouble());
				System.out.println("Votos: " + movie.get("vote_count").getAsInt());
				System.out.println("Resumen: " + movie.get("overview").getAsString());
				System.out.println("--------------------------------\n");
			}
		} else
		{
			System.out.println("No se encontraron resultados.");
		}
	}
}
