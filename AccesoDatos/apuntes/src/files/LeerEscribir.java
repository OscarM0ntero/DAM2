package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class LeerEscribir
{

	public static void main(String[] args)
	{
		
		try
		{
			crearCarpeta("G:\\Mi unidad\\DAM1\\Programacion\\JavaDocs\\monterooscar899\\hola");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static ArrayList<String> leerArchivo(String path) throws FileNotFoundException
	{
		File archivo = new File(path);
		
		ArrayList<String> lista = new ArrayList<>();
		
		Scanner input = new Scanner(archivo);
		
		while(input.hasNextLine())
		{
			String linea = input.nextLine();
			lista.add(linea);
		}
		return lista;
	}
	
	private static void escribirEnArchivo(ArrayList<String> lista) throws IOException
	{
		File archivo = new File("nombre.txt");

		FileWriter escritor = new FileWriter(archivo);
		
		for(String str: lista)
		{
			escritor.write(str + "\n");
		}
		
		escritor.close();
	}
	
	private static void crearCarpeta(String nom) throws IOException
	{
		File carpeta = new File(nom);
		// carpeta.mkdir() devuelve un boolean de si ha podido o no crear la carpeta en la ruta nom
		if(carpeta.mkdir())
			System.out.printf("Creada\n");
		else
			System.out.printf("No creada\n");

	}
	

}
