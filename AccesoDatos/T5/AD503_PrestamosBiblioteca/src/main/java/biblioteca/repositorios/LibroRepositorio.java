package biblioteca.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblioteca.modelos.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
	Page<Libro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
