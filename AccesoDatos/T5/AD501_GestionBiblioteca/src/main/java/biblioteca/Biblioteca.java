package biblioteca;

//Importación de las bibliotecas necesarias
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;

public class Biblioteca {
 private static final String DATABASE_NAME = "biblioteca";
 private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
 private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

 private static final MongoCollection<Document> libros = database.getCollection("libros");
 private static final MongoCollection<Document> autores = database.getCollection("autores");
 private static final MongoCollection<Document> socios = database.getCollection("socios");

 // Método para añadir un nuevo autor
 public static void anadirAutor(String nombre, String nacionalidad) {
     Document autor = new Document("nombre", nombre)
             .append("nacionalidad", nacionalidad);
     autores.insertOne(autor);
 }

 // Método para añadir un nuevo libro
 public static void anadirLibro(String titulo, String isbn, ObjectId autorId, String genero, boolean disponible) {
     Document libro = new Document("titulo", titulo)
             .append("isbn", isbn)
             .append("autor_id", autorId)
             .append("genero", genero)
             .append("disponible", disponible);
     libros.insertOne(libro);
 }

 // Método para añadir un nuevo socio
 public static void anadirSocio(String nombre, String direccion) {
     Document socio = new Document("nombre", nombre)
             .append("direccion", direccion)
             .append("libros_prestados", Arrays.asList());
     socios.insertOne(socio);
 }

 // Método para prestar un libro
 public static void prestarLibro(ObjectId libroId, ObjectId socioId) {
     Document libro = libros.find(new Document("_id", libroId)).first();
     if (libro != null && libro.getBoolean("disponible")) {
         libros.updateOne(new Document("_id", libroId), new Document("$set", new Document("disponible", false)));
         socios.updateOne(new Document("_id", socioId), new Document("$push", new Document("libros_prestados", libroId)));
     }
 }

 // Método para devolver un libro
 public static void devolverLibro(ObjectId libroId, ObjectId socioId) {
     libros.updateOne(new Document("_id", libroId), new Document("$set", new Document("disponible", true)));
     socios.updateOne(new Document("_id", socioId), new Document("$pull", new Document("libros_prestados", libroId)));
 }

 // Método para listar libros disponibles
 public static void listarLibrosDisponibles() {
     MongoCursor<Document> cursor = libros.find(new Document("disponible", true)).iterator();
     try {
         while (cursor.hasNext()) {
             System.out.println(cursor.next().toJson());
         }
     } finally {
         cursor.close();
     }
 }

 public static void main(String[] args) {
     // Ejemplos de uso
     anadirAutor("Gabriel Garcia Marquez", "Colombiano");
     ObjectId autorId = autores.find(new Document("nombre", "Gabriel Garcia Marquez")).first().getObjectId("_id");
     anadirLibro("Cien Años de Soledad", "123456789", autorId, "Realismo Mágico", true);
     anadirSocio("Juan Pérez", "Calle Falsa 123");

     ObjectId libroId = libros.find(new Document("titulo", "Cien Años de Soledad")).first().getObjectId("_id");
     ObjectId socioId = socios.find(new Document("nombre", "Juan Pérez")).first().getObjectId("_id");

     prestarLibro(libroId, socioId);
     listarLibrosDisponibles();
     devolverLibro(libroId, socioId);
     listarLibrosDisponibles();
 }
} 

/* 
Consultas para Mongo Shell:

//Buscar libros por género
db.libros.find({genero: "Realismo Mágico"});

//Buscar libros por autor
let autorId = db.autores.findOne({nombre: "Gabriel Garcia Marquez"})._id;
db.libros.find({autor_id: autorId});

//Listar libros disponibles
db.libros.find({disponible: true});

//Listar libros prestados por socio
let socioId = db.socios.findOne({nombre: "Juan Pérez"})._id;
db.libros.find({_id: {$in: db.socios.findOne({_id: socioId}).libros_prestados}});
*/
