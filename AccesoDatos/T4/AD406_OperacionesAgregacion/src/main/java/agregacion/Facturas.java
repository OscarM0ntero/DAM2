package agregacion;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Accumulators.*;
import java.util.Arrays;

public class Facturas {
	// Configuración de la conexión
	private static final String MONGO_URI = "mongodb://facturas:1234@79.72.63.217:27017/facturas_db?authSource=facturas_db";
	private static final String DATABASE_NAME = "facturas_db";

	public static void main(String[] args) {
		// Deshabilitar los logs de MongoDB
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.WARNING);

		// Conexión a la base de datos
		MongoClient mongoClient = MongoClients.create(MONGO_URI);
		MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
		MongoCollection<Document> facturas = database.getCollection("facturas");

		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("Menú de operaciones:");
			System.out.println("1. Ver cuántas facturas tiene un cliente");
			System.out.println("2. Calcular el importe total de una factura");
			System.out.println("3. Calcular el importe total de ventas de un cliente");
			System.out.println("4. Ver los productos más vendidos");
			System.out.println("0. Salir");
			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				System.out.print("Ingrese el nombre del cliente: ");
				String cliente1 = scanner.next();
				obtenerFacturasPorCliente(facturas, cliente1);
				break;
			case 2:
				System.out.print("Ingrese el número de la factura: ");
				int numeroFactura = scanner.nextInt();
				calcularTotalFactura(facturas, numeroFactura);
				break;
			case 3:
				System.out.print("Ingrese el nombre del cliente: ");
				String cliente2 = scanner.next();
				calcularTotalVentasCliente(facturas, cliente2);
				break;
			case 4:
				obtenerProductosMasVendidos(facturas);
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);

		scanner.close();
		mongoClient.close();
	}

	private static void obtenerFacturasPorCliente(MongoCollection<Document> facturas, String cliente) {
		AggregateIterable<Document> resultado = facturas
				.aggregate(Arrays.asList(match(eq("cliente", cliente)), group("$cliente", sum("totalFacturas", 1))));

		for (Document doc : resultado) {
			System.out.println("Cliente: " + doc.getString("_id"));
			System.out.println("Total de facturas: " + doc.getInteger("totalFacturas"));
		}
		System.out.println();
	}

	private static void calcularTotalFactura(MongoCollection<Document> facturas, int numeroFactura) {
		AggregateIterable<Document> resultado = facturas.aggregate(Arrays.asList(
				match(eq("numeroFactura", numeroFactura)), unwind("$lineas"),
				group("$numeroFactura", sum("totalFactura",
						new Document("$multiply", Arrays.asList("$lineas.precioUnitario", "$lineas.cantidad"))))));

		for (Document doc : resultado) {
			System.out.println("Número de factura: " + doc.getInteger("_id"));
			// Usamos get() y convertimos a double explícitamente
			Number totalFactura = doc.get("totalFactura", Number.class);
			System.out.println("Total de la factura: " + totalFactura.doubleValue());
		}
		System.out.println();
	}

	private static void calcularTotalVentasCliente(MongoCollection<Document> facturas, String cliente) {
		AggregateIterable<Document> resultado = facturas.aggregate(
				Arrays.asList(match(eq("cliente", cliente)), unwind("$lineas"), group("$cliente", sum("totalVentas",
						new Document("$multiply", Arrays.asList("$lineas.precioUnitario", "$lineas.cantidad"))))));

		for (Document doc : resultado) {
			System.out.println("Cliente: " + doc.getString("_id"));
			// Usamos get() y convertimos a double explícitamente
			Number totalVentas = doc.get("totalVentas", Number.class);
			System.out.println("Total de ventas: " + totalVentas.doubleValue());
		}
		System.out.println();
	}

	private static void obtenerProductosMasVendidos(MongoCollection<Document> facturas) {
		AggregateIterable<Document> resultado = facturas.aggregate(
				Arrays.asList(unwind("$lineas"), group("$lineas.articulo", sum("unidadesVendidas", "$lineas.cantidad")),
						sort(descending("unidadesVendidas"))));

		System.out.println("Productos más vendidos:");
		for (Document doc : resultado) {
			System.out.println("Producto: " + doc.getString("_id") + " | Unidades vendidas: "
					+ doc.getInteger("unidadesVendidas"));
		}
		System.out.println();
	}
}