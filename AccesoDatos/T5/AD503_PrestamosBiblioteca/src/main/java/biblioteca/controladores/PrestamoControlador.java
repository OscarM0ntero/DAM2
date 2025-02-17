package biblioteca.controladores;

import biblioteca.modelos.Libro;
import biblioteca.modelos.Lector;
import biblioteca.modelos.Prestamo;
import biblioteca.repositorios.LibroRepositorio;
import biblioteca.repositorios.LectorRepositorio;
import biblioteca.repositorios.PrestamoRepositorio;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/registrar")
	public String registrarPrestamo(@RequestParam Long libroId, @RequestParam Long lectorId) {
		Optional<Libro> libroOpt = libroRepositorio.findById(libroId);
		Optional<Lector> lectorOpt = lectorRepositorio.findById(lectorId);

		if (libroOpt.isPresent() && lectorOpt.isPresent() && libroOpt.get().isDisponible()) {
			Prestamo prestamo = new Prestamo();
			prestamo.setLibro(libroOpt.get());
			prestamo.setLector(lectorOpt.get());
			prestamo.setFechaAlta(LocalDate.now());

			libroOpt.get().setDisponible(false);
			libroRepositorio.save(libroOpt.get());
			prestamoRepositorio.save(prestamo);
			return "Préstamo registrado correctamente";
		}
		return "No se pudo registrar el préstamo";
	}

	@PostMapping("/devolver/{id}")
	public String devolverPrestamo(@PathVariable Long id) {
		Optional<Prestamo> prestamoOpt = prestamoRepositorio.findById(id);

		if (prestamoOpt.isPresent()) {
			Prestamo prestamo = prestamoOpt.get();
			prestamo.setFechaBaja(LocalDate.now());
			prestamo.getLibro().setDisponible(true);

			libroRepositorio.save(prestamo.getLibro());
			prestamoRepositorio.save(prestamo);
			return "Libro devuelto correctamente";
		}
		return "Préstamo no encontrado";
	}

	@GetMapping("/estadisticas")
	public List<Map<String, Object>> obtenerEstadisticasPorGrupo() {
		return prestamoRepositorio.obtenerEstadisticasPorGrupo();
	}

}
