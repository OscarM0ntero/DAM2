package biblioteca.controladores;

import biblioteca.modelos.Lector;
import biblioteca.repositorios.LectorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectores")
public class LectorControlador {

	@Autowired
	private LectorRepositorio lectorRepositorio;

	// registrar lector
	@PostMapping
	public ResponseEntity<Lector> registrarLector(@RequestBody Lector lector) {
		if (lector.getNombre() == null || lector.getNombre().isEmpty() || lector.getNombrelogin() == null
				|| lector.getNombrelogin().isEmpty() || lector.getContraseña() == null
				|| lector.getContraseña().isEmpty()) {
			return ResponseEntity.badRequest().body(null); // validación de datos
		}

		try {
			Lector nuevoLector = lectorRepositorio.save(lector);
			return ResponseEntity.ok(nuevoLector);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null); // error interno
		}
	}

	// buscar lector por nombre
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<List<Lector>> buscarLector(@PathVariable String nombre) {
		List<Lector> lectores = lectorRepositorio.findByNombreContainingIgnoreCase(nombre);
		if (lectores.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lectores);
	}
}
