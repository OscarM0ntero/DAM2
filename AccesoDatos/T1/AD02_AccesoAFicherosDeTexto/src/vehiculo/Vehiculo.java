package vehiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehiculo
{
    private String matricula;
    private String marca;
    private String modelo;
    private String tipo;

    public Vehiculo(String matricula, String marca, String modelo, String tipo)
    {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public String getMatricula()
    {
        return matricula;
    }

    public String getMarca() 
    {
        return marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public String getTipo()
    {
        return tipo;
    }

    // Mostrar información del vehículo
    @Override
    public String toString()
    {
        return String.format("Vehiculo {matricula=%s, marca=%s, modelo=%s, tipo=%s}", matricula, marca, modelo, tipo);
    }
    
    // Guardar los vehículos en un archivo de texto
    public static void guardarVehiculos(List<Vehiculo> lista, String nombreArchivo) throws IOException
	{
		File archivo = new File(nombreArchivo);

		FileWriter escritor = new FileWriter(archivo);
		
		for(Vehiculo vehiculo: lista)
		{
			escritor.write(vehiculo.toString() + "\n");
		}
		
		escritor.close();
	}
    
    // Recuperar los vehículos desde un archivo de texto
    public static List<Vehiculo> recuperarVehiculos(String path) throws FileNotFoundException
	{
		File archivo = new File(path);
		
		List<Vehiculo> lista = new ArrayList<>();
		
		Scanner output = new Scanner(archivo);
				
		while(output.hasNextLine())
		{
			String linea = output.nextLine();
			ArrayList<String> datos = new ArrayList<>();
			for(int i = 0; i < 3; i++)
			{
				String dato = linea.substring(linea.indexOf("=") + 1, linea.indexOf(","));
				linea = linea.substring(linea.indexOf(",") + 1);
				datos.add(dato);
			}
			String dato = linea.substring(linea.indexOf("=") + 1, linea.indexOf("}"));
			datos.add(dato);
			Vehiculo v = new Vehiculo(datos.get(0), datos.get(1), datos.get(2), datos.get(3));
			System.out.println("\t" + v.toString());
			lista.add(v);
		}
		return lista;
	}
    
    public static void buscar(List<Vehiculo> vehiculos)
    {
    	boolean end = false;
		while(!end)
		{
			Scanner input = new Scanner(System.in);
			System.out.print("\nBuscar en el registro. Introduce matrícula o 0 para salir: ");
			String entrada = input.nextLine();
			if(!entrada.equals("0"))
			{
				boolean found = false;
				for(Vehiculo v: vehiculos)
				{
					if(v.getMatricula().equalsIgnoreCase(entrada))
					{
						System.out.println("Vehículo encontrado:");
						System.out.println("\t" + v.toString());
						found = true;
					}
				}
				if(!found)
					System.out.println("Vehículo no encontrado.");
			}
			else
				end = true;
		}
    }
}


