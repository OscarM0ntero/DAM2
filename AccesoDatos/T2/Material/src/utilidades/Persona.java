package utilidades;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.Query;

/*! Importante implementar el serializable*/
public class Persona implements Serializable{
	private String nombre;
	private int id;
	private static final long serialVersionUID = 4193175540683740691L;

	public Persona(int id,String nombre) {
		this.id=id;
		this.nombre = nombre;
	}
	
	public Persona(String nombre) {
		this.nombre = nombre;
		this.id=0;
	}
	
	public static Persona Seleccionar(Connection conn,String nombre) throws SQLException {
		Persona persona=null;
		
		PreparedStatement selectStatment=conn.prepareStatement("Select * from personas where nombre=?;");
		selectStatment.setString(1, nombre);
		ResultSet rs=selectStatment.executeQuery();
		
		if(rs.next()) {
			persona=new Persona(rs.getInt(1),rs.getString(2));
		}
		
		return persona;
	}
	
	public Persona Borrar(Connection conn) throws SQLException {
		Persona persona=null;
		
		PreparedStatement deleteStatment=conn.prepareStatement("Delete from personas where nombre=?;");
		deleteStatment.setString(1, nombre);
		deleteStatment.executeUpdate();
		
		persona=Persona.Seleccionar(conn, nombre);
		
		return persona;
	}
	
	public boolean Insertar(Connection conn) {
		boolean resultado=false;
		
		try {			
			PreparedStatement insertStatement=conn.prepareStatement("insert into personas(nombre) values (?);");
						
			insertStatement.setString(1, this.nombre);
			
			insertStatement.executeUpdate();
			
			resultado=true;
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Error de insercción...");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Algo has hecho mal...");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultado;		
	}
	
	public void Actualizar(Connection conn,String newNombre) {
		try {			
			PreparedStatement insertStatement=conn.prepareStatement("update personas set nombre=? where nombre=?;");
						
			insertStatement.setString(1, newNombre);
			
			insertStatement.setString(2, this.nombre);
			
			insertStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Error de update...");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Algo has hecho mal...");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		this.nombre=newNombre;
	}
	
	@Override
	public String toString() {
		return "Persona="+id+" " + nombre + ".";
	}
		
}
