package ejercicio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Properties cfg = new Properties();
		String userProp = null, file = null;
		Scanner input = new Scanner(System.in);
		boolean propCargadas = false;
		
		System.out.println("BUSCADOR DE PROPIEDADES");
		System.out.print("Archivo a consultar: ");
		file = input.nextLine();
		try
		{
			cfg.load(new FileInputStream(file));
			System.out.println("Propiedades cargadas correctamente.");
			propCargadas = true;
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error. " + ex.getMessage());
		}
		catch (IOException ex)
		{
			System.out.println("Error. " + ex.getMessage());
		}
		if(propCargadas)
		{
			do
			{
				System.out.println("\nIntroduce una propiedad a buscar o '0' para terminar");
				System.out.print("Propiedad: ");
				userProp = input.nextLine();
				
				String p = cfg.getProperty(userProp);
				
				if(p != null)
					System.out.println(userProp + " = " + p);
				else if(!userProp.equals("0"))
					System.out.println("Propiedad no encontrada.");
			} while(!userProp.equals("0"));
		}
		else
			System.out.println("Las propiedades no se cargaron correctamente.");
		System.out.println("Fin de programa.");
			
	}
}
