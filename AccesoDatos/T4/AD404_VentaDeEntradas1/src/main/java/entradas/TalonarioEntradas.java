package entradas;

import java.util.Scanner;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TalonarioEntradas {

	private static final String MONGO_URI = "mongodb://entradas:1234@79.72.63.217:27017/entradas_db?authSource=entradas_db";
	private static final String DATABASE_NAME = "entradas_db";

	public static void main(String[] args) {
		// Esto es para deshabilitar los logs de MongoDB
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.WARNING);

		Scanner scanner = new Scanner(System.in);

		// Pedimos los datos del evento
		System.out.print("Ingrese el nombre del evento: ");
		String nombreEvento = scanner.nextLine();

		System.out.print("Ingrese el número de localidades: ");
		int numLocalidades = scanner.nextInt();

		// Esto protege una futura colección de usuarios
		if (nombreEvento.equals("usuarios")) {
			System.out.println("Error. No se permite un evento llamado 'usuarios'.");
			return;
		}
		// Nos conectamos a MongoDB
		try (MongoClient mongoClient = MongoClients.create(MONGO_URI)) {
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

			// Obtenemos la colección para el evento
			MongoCollection<Document> collection = database.getCollection(nombreEvento);

			// Verificamos si la colección existe y tiene documentos
			if (collection != null && collection.countDocuments() > 0) {
				collection.deleteMany(new Document());
				System.out.println("Colección existente eliminada.");
			}

			// Creamos entradas y las agregamos a la colección
			for (int i = 1; i <= numLocalidades; i++) {
				Document entrada = new Document("numeroEntrada", i).append("observaciones", "").append("nombreCliente",
						null);
				collection.insertOne(entrada);
			}

			System.out.println("Talonario creado exitosamente para el evento: " + nombreEvento);
		} catch (Exception e) {
			System.err.println("Error al conectar con la base de datos o crear el talonario: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
