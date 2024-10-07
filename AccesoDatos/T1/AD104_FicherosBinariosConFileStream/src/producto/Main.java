// Creado por Oscar Montero		01/10/2024
package producto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		
		// B) Grabamos en el archivo
		try (FileOutputStream fos = new FileOutputStream(archivo); DataOutputStream dos = new DataOutputStream(fos))
		{
			for(Producto p: productos)
			{
				dos.writeUTF(p.getArticulo());
				dos.writeFloat(p.getPrecio());
				dos.writeInt(p.getExistencias());
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		// C) Recuperamos del archivo y lo imprimimos
		try (FileInputStream fis = new FileInputStream(archivo); DataInputStream dis = new DataInputStream(fis))
		{
			while (dis.available() > 0)
			{
				System.out.println(String.format("Articulo: %s | Precio : %.2f€ | Existencias: %d", dis.readUTF(), dis.readFloat(), dis.readInt()));
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		System.out.println("Fin del programa.");
	}
}