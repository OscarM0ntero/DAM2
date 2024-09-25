package vehiculo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String archivo = "vehiculos.txt";
		// Recuperar los vehículos desde el archivo
		System.out.println("Comenzamos la lectura: ");
		List<Vehiculo> vehiculosRecuperados = Vehiculo.recuperarVehiculos(archivo);
		System.out.println("Número de objetos leídos: " + vehiculosRecuperados.size());
		
		Vehiculo.buscar(vehiculosRecuperados);
		System.out.println("Fin del programa.");
	}
}