package hibernate;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "genero_musical")
public class GeneroMusical
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cantante> cantantes;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public List<Cantante> getCantantes()
	{
		return cantantes;
	}

	public void setCantantes(List<Cantante> cantantes)
	{
		this.cantantes = cantantes;
	}
}
