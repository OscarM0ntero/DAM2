package biblioteca.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Libro libro;

	@ManyToOne
	private Lector lector;

	private LocalDate fechaAlta;
	private LocalDate fechaBaja;

	public Long getId() {
		return id;
	}

	public Libro getLibro() {
		return libro;
	}

	public Lector getLector() {
		return lector;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
}
