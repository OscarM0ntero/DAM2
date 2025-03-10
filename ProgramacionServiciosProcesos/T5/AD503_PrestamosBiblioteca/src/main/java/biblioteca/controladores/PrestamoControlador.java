package biblioteca.controladores;

import biblioteca.modelos.Prestamo;
import biblioteca.repositorios.PrestamoRepositorio;
import biblioteca.repositorios.LibroRepositorio;
import biblioteca.repositorios.LectorRepositorio;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoControlador {

	@Autowired
	private PrestamoRepositorio prestamoRepositorio;

	@Autowired
	private LibroRepositorio libroRepositorio;

	@Autowired
	private LectorRepositorio lectorRepositorio;

	// Registrar un nuevo préstamo
	@PostMapping("/registrar")
	public ResponseEntity<String> registrarPrestamo(@RequestBody Map<String, Long> requestData) {
		Long idLibro = requestData.get("libroId");
		Long idLector = requestData.get("lectorId");

		if (idLibro == null || idLector == null) {
			return ResponseEntity.badRequest().body("Faltan parámetros: libroId o lectorId");
		}

		if (!libroRepositorio.existsById(idLibro)) {
			return ResponseEntity.badRequest().body("El libro con ID " + idLibro + " no existe.");
		}

		if (!lectorRepositorio.existsById(idLector)) {
			return ResponseEntity.badRequest().body("El lector con ID " + idLector + " no existe.");
		}

		Prestamo prestamo = new Prestamo(idLibro, idLector, LocalDate.now().toString(), null);
		prestamoRepositorio.save(prestamo);

		return ResponseEntity.ok("Préstamo registrado correctamente.");
	}

	// Devolver un libro (actualiza la fecha de baja)
	@PostMapping("/devolver/{id}")
	public ResponseEntity<String> devolverPrestamo(@PathVariable Long id) {
		Optional<Prestamo> prestamoOpt = prestamoRepositorio.findById(id);

		if (prestamoOpt.isPresent()) {
			Prestamo prestamo = prestamoOpt.get();
			if (prestamo.getFechabaja() != null) {
				return ResponseEntity.badRequest().body("Este préstamo ya fue devuelto.");
			}

			prestamo.setFechabaja(LocalDate.now().toString());
			prestamoRepositorio.save(prestamo);
			return ResponseEntity.ok("Libro devuelto correctamente.");
		}

		return ResponseEntity.badRequest().body("Préstamo no encontrado.");
	}

	// Ver préstamos de un lector
	@GetMapping("/lector/{idLector}")
	public ResponseEntity<List<Prestamo>> obtenerPrestamosPorLector(@PathVariable Long idLector) {
		List<Prestamo> prestamos = prestamoRepositorio.findByIdLector(idLector);
		if (prestamos.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(prestamos);
	}

	@PutMapping("/finalizar/{id}")
	public ResponseEntity<String> finalizarPrestamo(@PathVariable Long id) {
		Optional<Prestamo> prestamoOpt = prestamoRepositorio.findById(id);

		if (prestamoOpt.isPresent()) {
			Prestamo prestamo = prestamoOpt.get();

			if (prestamo.getFechabaja() == null) { // Solo finalizar si aún no ha sido devuelto
				prestamo.setFechabaja(LocalDate.now().toString()); // Fecha actual
				prestamoRepositorio.save(prestamo);
				return ResponseEntity.ok("Préstamo finalizado correctamente.");
			} else {
				return ResponseEntity.badRequest().body("Este préstamo ya ha sido finalizado.");
			}
		}
		return ResponseEntity.notFound().build();
	}

}
