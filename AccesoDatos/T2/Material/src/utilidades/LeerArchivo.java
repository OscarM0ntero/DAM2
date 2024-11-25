package utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import java.io.File;


public class LeerArchivo {
	
	public static void leer(String fichero){		
		try {
			FileInputStream fis=new FileInputStream(new File(fichero));
			ObjectInputStream leer=new ObjectInputStream(fis);
						
			for(Persona i:(List<Persona>) leer.readObject()) {
				System.out.println(i.toString());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Persona> exportarListas(String fichero){
		List<Persona>contenido=new ArrayList<>();
		
		try {
			FileInputStream fis=new FileInputStream(new File(fichero));
			ObjectInputStream leer=new ObjectInputStream(fis);
			
			
			contenido.addAll((List<Persona>) leer.readObject());
			
			fis.close();
			leer.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contenido;
	}
	
}
