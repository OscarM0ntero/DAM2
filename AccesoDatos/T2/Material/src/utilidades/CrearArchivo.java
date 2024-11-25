package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CrearArchivo {
	public static void crear(List<Persona> personas,String nombre) {		
		try{
			File resultado=new File(nombre);
			FileOutputStream fil=new FileOutputStream(resultado/*,true Esto es para que se almacenen mas cosas*/);
			ObjectOutputStream escribir=new ObjectOutputStream(fil);
			
			escribir.writeObject(personas);
			
			fil.close();
			escribir.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
