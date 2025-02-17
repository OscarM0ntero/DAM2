package biblioteca.modelos;

import jakarta.persistence.*;

@Entity
public class Lector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String nombreLogin;
	private String contraseña;
	private String codCurso;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreLogin() {
		return nombreLogin;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreLogin(String nombreLogin) {
		this.nombreLogin = nombreLogin;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
}
