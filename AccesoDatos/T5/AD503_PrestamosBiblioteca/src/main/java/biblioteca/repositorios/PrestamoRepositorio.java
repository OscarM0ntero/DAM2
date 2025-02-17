package biblioteca.repositorios;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import biblioteca.modelos.Prestamo;

@Repository
public interface PrestamoRepositorio extends CrudRepository<Prestamo, Long> {
	@Query("SELECT l.codCurso, COUNT(p) FROM Prestamo p JOIN p.lector l GROUP BY l.codCurso")
	List<Map<String, Object>> obtenerEstadisticasPorGrupo();
}
