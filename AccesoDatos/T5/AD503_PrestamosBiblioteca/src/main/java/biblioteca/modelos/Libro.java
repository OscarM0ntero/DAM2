package biblioteca.modelos;

import jakarta.persistence.*;

@Entity
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String autor;
	private int paginas;
	private String genero;
	private boolean disponible;

	public Libro(Long id, String titulo, String autor, int paginas, String genero, boolean disponible) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.genero = genero;
		this.disponible = disponible;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public String getGenero() {
		return genero;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
