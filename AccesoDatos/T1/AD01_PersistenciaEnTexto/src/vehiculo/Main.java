package vehiculo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		Vehiculo vehiculo1 = new Vehiculo("1234ABC", "Toyota", "Corolla", "Coche");
		Vehiculo vehiculo2 = new Vehiculo("5678DEF", "Ford", "Transit", "Furgón");
		Vehiculo vehiculo3 = new Vehiculo("9101GHI", "Yamaha", "R6", "Moto");

		// Guardar los vehículos en un archivo
		List<Vehiculo> listaVehiculos = new ArrayList<>();
		listaVehiculos.add(vehiculo1);
		listaVehiculos.add(vehiculo2);
		listaVehiculos.add(vehiculo3);

		System.out.println("Vehículos:");
		for (Vehiculo v : listaVehiculos)
		{
			System.out.println("\t" + v.toString());
		}

		String archivo = "vehiculos.txt";
		// Guardamos los vehículos en el archivo
		Vehiculo.guardarVehiculos(listaVehiculos, archivo);

		System.out.println("Fin del programa.");
	}
}