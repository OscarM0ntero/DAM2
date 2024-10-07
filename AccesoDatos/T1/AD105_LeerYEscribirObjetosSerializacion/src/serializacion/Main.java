// Creado por Oscar Montero		07/10/2024
package serializacion;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// A) Creamos la lista de productos
		ArrayList<Producto> productos = new ArrayList<>();
		productos.add(new Producto("Tomate", 0.35f, 150));
		productos.add(new Producto("Lechuga", 1.10f, 80));
		productos.add(new Producto("Zanahoria", 0.18f, 200));
		
		String archivo = "productos.bin";
		
		// B) Grabamos en el archivo los objetos
		try (FileOutputStream fos = new FileOutputStream(archivo); ObjectOutputStream oos =new ObjectOutputStream(fos))
		{
			for(Producto p: productos)
			{
				oos.writeObject(p);
			}
			oos.close();
			fos.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		// C) Recuperamos los objetos del archivo y lo imprimimos
		try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis))
		{
			for(;;)
				System.out.println(String.format("%s", ois.readObject()));
		}
		catch (EOFException ex)
		{
		}
		catch (IOException | ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}

		System.out.println("Fin del programa.");
	}
}