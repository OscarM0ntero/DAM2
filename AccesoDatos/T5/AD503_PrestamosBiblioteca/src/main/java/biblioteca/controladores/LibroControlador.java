package biblioteca.controladores;

import biblioteca.modelos.Libro;
import biblioteca.repositorios.LibroRepositorio;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroControlador {

	@Autowired
	private LibroRepositorio libroRepositorio;

	// Obtener todos los libros
	@GetMapping
	public List<Libro> getAllLibros() {
		return libroRepositorio.findAll();
	}

	// Crear un libro
	@PostMapping
	public Libro createLibro(@RequestBody Libro libro) {
		return libroRepositorio.save(libro);
	}

	// Obtener un libro por ID
	@GetMapping("/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
		return libroRepositorio.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Actualizar un libro
	@PutMapping("/{id}")
	public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
		return libroRepositorio.findById(id).map(libro -> {
			libro.setTitulo(libroDetails.getTitulo());
			libro.setAutor(libroDetails.getAutor());
			libro.setPaginas(libroDetails.getPaginas());
			libro.setGenero(libroDetails.getGenero());
			libro.setDisponible(libroDetails.isDisponible());
			libroRepositorio.save(libro);
			return ResponseEntity.ok(libro);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Eliminar un libro
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
		if (!libroRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		libroRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Libro>> buscarLibros(@RequestParam String titulo) {
		System.out.println("Buscando libros con título que contenga: " + titulo); // Log en consola
		List<Libro> libros = libroRepositorio.buscarPorTitulo(titulo);
		System.out.println("Resultados encontrados: " + libros.size()); // Log en consola
		return ResponseEntity.ok(libros);
	}

	@GetMapping("/test")
	public ResponseEntity<List<Libro>> testQuery() {
		List<Libro> libros = libroRepositorio.findAll(); // Devuelve todos los libros
		return ResponseEntity.ok(libros);
	}

}
