// Creado por Oscar Montero		08/10/2024
package examen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Properties cfg = null;
		boolean propCargadas = false;
		try
		{
			cfg = cargarPropiedades("programa.conf");
			propCargadas = true;
		}
		catch (IOException ex)
		{
			System.out.println("Error. " + ex.getMessage());
		}
		
		// Si las propiedades se han cargado correctamente las asignamos a variables para trabajar con ellas
		if(propCargadas)
		{
			int inicio = 0, fin = 0, edadmin = 0, edadmax = 0;
			String nombrefichero = cfg.getProperty("nombrefichero");
			boolean propiedadesCorrectas = false;
			try
			{
				inicio = Integer.parseInt(cfg.getProperty("inicio"));
				fin = Integer.parseInt(cfg.getProperty("fin"));
				edadmin = Integer.parseInt(cfg.getProperty("edadmin"));
				edadmax = Integer.parseInt(cfg.getProperty("edadmax"));
				propiedadesCorrectas = true;
			}
			catch (NumberFormatException ex)
			{
				System.out.println("Error. " + ex.getMessage());
			}
			
			// Si las propiedades eran correctas y se han podido parsear, continuamos leyendo el archivo y guardandolo en una lista de clientes
			if (propiedadesCorrectas)
			{
				File archivoClientes = new File(nombrefichero);
				
				ArrayList <Cliente> clientes = new ArrayList<>();
				
				Scanner output = null;
				try
				{
					output = new Scanner(archivoClientes);
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				output.nextLine();	// Saltamos la primera línea y continuamos leyendo el archivo
				while(output.hasNextLine())
				{
					String linea = output.nextLine();
					String[] datos = linea.split(",");
					
					Cliente c = new Cliente(datos[0], Integer.parseInt(datos[1]), datos[2], datos[3]);
					clientes.add(c);
				}
				
				File archivoOutput = new File("clienteBIN.dat");
				try (FileOutputStream fos = new FileOutputStream(archivoOutput); ObjectOutputStream oos =new ObjectOutputStream(fos))
				{
					// El siguiente for imprimirá y exportará a nuestro .dat los clientes que cumplen los requisitos
					for(Cliente c: clientes.subList(inicio - 1, fin - 1))
					{
						if(c.getAge() >= edadmin && c.getAge() <= edadmax)
						{
							oos.writeObject(c);
							System.out.println(c);
						}
					}
					oos.close();
					fos.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		else
			System.out.println("Las propiedades no se cargaron correctamente.");
		
	}
	
	private static Properties cargarPropiedades(String file) throws IOException
	{
		Properties cfg = new Properties();
		cfg.load(new FileInputStream(file));
		return cfg;
	}
}
