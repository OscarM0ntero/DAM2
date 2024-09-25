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

		for (Vehiculo vehiculo : lista)
		{
			escritor.write(vehiculo.toString() + "\n");
		}

		escritor.close();
	}
}
