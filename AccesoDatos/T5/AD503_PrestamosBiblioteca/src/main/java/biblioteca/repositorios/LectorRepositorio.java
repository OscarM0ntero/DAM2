package biblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import biblioteca.modelos.Lector;

public interface LectorRepositorio extends JpaRepository<Lector, Long> {
}
