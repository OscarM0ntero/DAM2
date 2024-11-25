package utilidades;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		
		System.out.print("Inserta el fichero:");
		String fichero=teclado.nextLine();
		System.out.println();
		
		ArrayList<Persona>personas=new ArrayList<>();
		personas.add(new Persona("Manolo"));
		personas.add(new Persona("Pepe"));
		personas.add(new Persona("Jose"));
		personas.add(new Persona("Elena"));
		personas.add(new Persona("Andres"));
		
		CrearArchivo.crear(personas, fichero);
		
		LeerArchivo.leer(fichero);
		
		List<Persona>personas2=new ArrayList<>();
		personas2.addAll(LeerArchivo.exportarListas(fichero));
		System.out.println(personas2);
		
		try {
			Connection conn=new Conexion("127.0.0.1", "manolo", "root", "12345").conexion();
			conn.setAutoCommit(false);
			
			personas.get(0).Insertar(conn);

			Persona persona=Persona.Seleccionar(conn, "Manolo");
			
			if(persona!=null) {
				System.out.println(persona);
			
				persona.Actualizar(conn, "Manuel");
			
				System.out.println(persona);
			}else {
				System.out.println("No se ha encontrado a la persona...");
			}
			
			if(persona.Borrar(conn)==null) {
				System.out.println("Borrado efectuado con exito...");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
