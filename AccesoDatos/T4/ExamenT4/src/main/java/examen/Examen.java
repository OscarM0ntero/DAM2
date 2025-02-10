package examen;

import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.lang.Math;

public class Examen {
	private static final String DATABASE_NAME = "almacen";
	private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
	private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

	private static final MongoCollection<Document> articulos = database.getCollection("articulos");

	// Imprimir articulo
	private static void imprimirArticulo(Document doc) {
		System.out.printf("%s\n\t%s\n\t%.2f\n\t%s\n\t%d\n\t%s\n", doc.getString("nombre"), doc.getString("descripcion"),
				doc.getDouble("precio"), doc.getString("categoria"), doc.getInteger("stock"),
				doc.getString("fecha_creacion"));
		if (doc.containsKey("proveedor"))
			System.out.printf("\t%s\n", doc.getList("proveedor", String.class));
		System.out.println();
	}

	// Buscar producto por nombre
	private static void buscarNombre(String nombre) {
		AggregateIterable<Document> resultado = articulos.aggregate(Arrays.asList(match(eq("nombre", nombre))));

		for (Document doc : resultado) {
			imprimirArticulo(doc);
		}
		System.out.println("-----------------------");
	}

	// Buscar producto por nombre
	private static void buscarCategoria(String categoria) {
		AggregateIterable<Document> resultado = articulos.aggregate(Arrays.asList(match(eq("categoria", categoria))));

		for (Document doc : resultado) {
			imprimirArticulo(doc);
		}
		System.out.println("-----------------------");
	}

	// Buscar producto por stock inferior a
	private static void buscarStockInferior(int stock) {
		AggregateIterable<Document> resultado = articulos.aggregate(Arrays.asList(match(lt("stock", stock))));

		for (Document doc : resultado) {
			imprimirArticulo(doc);
		}
		System.out.println("-----------------------");
	}

	// Actualizar precios de categoria mediante porcentaje
	private static void actualizarPreciosCategoria(String categoria, double porcentaje) {
		AggregateIterable<Document> resultado = articulos.aggregate(Arrays.asList(match(eq("categoria", categoria))));

		double mult = (porcentaje + 100) / 100; // Variable por la que se multiplicará el precio para ajustarlo
												// porcentualmente

		for (Document doc : resultado) {
			double nuevoPrecio = Math.round(doc.getDouble("precio") * mult);

			articulos.updateOne(doc, new Document("$set", new Document("precio", nuevoPrecio)));
		}
		buscarCategoria(categoria);
	}

	// Añadir proveedor a articulo
	private static void aniadirProveedorNombre(String nombre, String proveedor) {
		AggregateIterable<Document> resultado = articulos.aggregate(Arrays.asList(match(eq("nombre", nombre))));

		for (Document doc : resultado) {
			articulos.updateOne(doc, new Document("$push", new Document("proveedor", proveedor)));
		}
		buscarNombre(nombre);
	}

	public static void main(String[] args) {
		// Deshabilitar los logs de MongoDB
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.WARNING);

		Scanner scanner = new Scanner(System.in);

		// Bucle de operaciones
		while (true) {
			System.out.println("\n=== Gestión de Almacén ===");
			System.out.println("1. Buscar producto por nombre");
			System.out.println("2. Buscar producto por categoría");
			System.out.println("3. Mostrar productos con stock inferior a un valor");
			System.out.println("4. Actualizar los precios de una categoría mediante un porcentaje dado");
			System.out.println("5. Añadir dato de proveedor a producto");
			System.out.println("6. Salir");
			System.out.print("Seleccione una opción: ");
			try {
				int opcion = scanner.nextInt();
				scanner.nextLine();
				if (opcion == 1) {
					System.out.printf("Nombre producto: ");
					buscarNombre(scanner.nextLine());
				} else if (opcion == 2) {
					System.out.printf("Categoría: ");
					buscarCategoria(scanner.nextLine());
				} else if (opcion == 3) {
					System.out.printf("Valor máximo: ");
					buscarStockInferior(scanner.nextInt());
				} else if (opcion == 4) {
					System.out.printf("Categoría: ");
					String cat = scanner.nextLine();
					System.out.printf("Porcentaje a cambiar: ");
					actualizarPreciosCategoria(cat, scanner.nextInt());
				} else if (opcion == 5) {
					System.out.printf("Nombre producto: ");
					String nombre = scanner.nextLine();
					System.out.printf("Proveedor: ");
					aniadirProveedorNombre(nombre, scanner.nextLine());
				} else if (opcion == 6) {
					System.out.println("Programa finalizado.");
					break;
				} else {
					System.out.println("Opción no válida.");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}

	}
}
