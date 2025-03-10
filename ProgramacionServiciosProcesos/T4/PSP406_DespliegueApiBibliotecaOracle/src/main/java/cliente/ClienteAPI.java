// Oscar Montero
package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClienteAPI {

    private static final String API_KEY = "3b5faf6c57b79d39e76351663a5e0cbe";
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Servidor iniciado en el puerto 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream outputStream = clientSocket.getOutputStream()
        ) {
            String requestLine = in.readLine();
            System.out.println("Solicitud recibida: " + requestLine);

            if (requestLine != null) {
                String[] parts = requestLine.split(" ");
                String method = parts[0];
                String path = parts[1];

                if (method.equals("OPTIONS")) {
                    sendResponse(outputStream, "", 200);
                } else if (method.equals("GET")) {
                    if (path.startsWith("/search")) {
                        String query = path.split("\\?")[1].split("=")[1];
                        String response = searchMovieByTitle(query);
                        sendResponse(outputStream, response, 200);
                    } else if (path.startsWith("/popular")) {
                        String response = listPopularMovies();
                        sendResponse(outputStream, response, 200);
                    } else if (path.startsWith("/by-genre")) {
                        String genreId = path.split("\\?")[1].split("=")[1];
                        String response = listMoviesByGenre(genreId);
                        sendResponse(outputStream, response, 200);
                    } else {
                        sendResponse(outputStream, "Ruta no encontrada", 404);
                    }
                } else {
                    sendResponse(outputStream, "Método no permitido", 405);
                }
            }
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Buscar películas por título
    private static String searchMovieByTitle(String title) throws Exception {
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
        String url = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + encodedTitle;
        return makeRequest(url);
    }

    // Obtener películas populares
    private static String listPopularMovies() throws Exception {
        String url = BASE_URL + "/movie/popular?api_key=" + API_KEY;
        return makeRequest(url);
    }

    // Buscar por género
    private static String listMoviesByGenre(String genreId) throws Exception {
        String url = BASE_URL + "/discover/movie?api_key=" + API_KEY + "&with_genres=" + genreId;
        return makeRequest(url);
    }

    // Realizar la solicitud HTTP
    private static String makeRequest(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return "Error en la solicitud: " + response;
            }
            return response.body().string();
        }
    }

    private static void sendResponse(OutputStream outputStream, String body, int statusCode) {
        try {
            String headers = "HTTP/1.1 " + statusCode + " OK\r\n" +
                             "Content-Type: application/json\r\n" +
                             "Access-Control-Allow-Origin: *\r\n" +
                             "Access-Control-Allow-Methods: GET, POST, OPTIONS\r\n" +
                             "Access-Control-Allow-Headers: Content-Type\r\n" +
                             "Content-Length: " + body.getBytes().length + "\r\n" +
                             "\r\n";

            outputStream.write(headers.getBytes(StandardCharsets.UTF_8));

            outputStream.write(body.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
