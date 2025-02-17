package biblioteca.controladores;

import biblioteca.modelos.Libro;
import biblioteca.repositorios.LibroRepositorio;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroControlador {
	@Autowired
	private LibroRepositorio repository;

	@Autowired
	private LibroRepositorio libroRepositorio;

	@GetMapping
	public List<Libro> getAllLibros() {
		return repository.findAll();
	}

	@PostMapping
	public Libro createLibro(@RequestBody Libro libro) {
		return repository.save(libro);
	}

	@GetMapping("/{id}")
	public Optional<Libro> getLibroById(@PathVariable Long id) {
		return repository.findById(id);
	}

	@PutMapping("/{id}")
	public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
		return repository.findById(id).map(libro -> {
			libro.setTitulo(libroDetails.getTitulo());
			libro.setAutor(libroDetails.getAutor());
			libro.setPaginas(libroDetails.getPaginas());
			libro.setGenero(libroDetails.getGenero());
			libro.setDisponible(libroDetails.isDisponible());
			return repository.save(libro);
		}).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
	}

	@DeleteMapping("/{id}")
	public void deleteLibro(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/buscar")
	public List<Libro> buscarLibros(@RequestParam String titulo, @RequestParam(defaultValue = "10") int limite) {
		return libroRepositorio.findByTituloContainingIgnoreCase(titulo, PageRequest.of(0, limite)).getContent();
	}

}
