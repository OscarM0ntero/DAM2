package biblioteca.controladores;

import biblioteca.modelos.Libro;
import biblioteca.repositorios.LibroRepositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroControlador.class)
public class LibroControladorTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LibroRepositorio libroRepositorio;

	@Test
	public void buscarLibros_DeberiaRetornarLista() throws Exception {
		// Crear lista de libros simulada
		Libro libro = new Libro(1L, "El Quijote", "Cervantes", 500, "Novela", true);
		Page<Libro> librosPaginados = new PageImpl<>(List.of(libro));

		// Configurar Mock para el repositorio
		Mockito.when(libroRepositorio.findByTituloContainingIgnoreCase(anyString(), any(PageRequest.class)))
				.thenReturn(librosPaginados);

		// Ejecutar la prueba
		mockMvc.perform(get("/libros/buscar?titulo=quijote")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray()) // Verifica que hay un array "content"
				.andExpect(jsonPath("$.content.length()").value(1)) // Verifica que tiene 1 elemento
				.andExpect(jsonPath("$.content[0].titulo").value("El Quijote")); // Verifica el título
	}
}
