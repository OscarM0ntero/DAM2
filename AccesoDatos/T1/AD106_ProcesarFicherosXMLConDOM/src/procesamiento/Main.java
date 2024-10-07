// Creado por Oscar Montero		07/10/2024
package procesamiento;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File archivo = new File("pelis.xml");
		Document doc = null;
		// B) Grabamos en el archivo los objetos
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(archivo);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("pelicula");
		System.out.println("Número de películas: " + nList.getLength());

		for (int i = 0; i < nList.getLength(); i++)
		{
			Element eElement = (Element) nList.item(i);
			System.out.println("\nPelícula " + (i+1));
			System.out.println("Título en español: " + eElement.getAttribute("titulo"));
			System.out.println("Fecha estreno: " + eElement.getElementsByTagName("estreno").item(0).getTextContent());
			System.out.println("Título original: " + eElement.getElementsByTagName("titulo").item(0).getTextContent());
			System.out.println("Director: " + eElement.getElementsByTagName("director").item(0).getTextContent());
			System.out.println("País: " + ((Element) eElement.getElementsByTagName("director").item(0)).getAttribute("pais"));
		}

		System.out.println("Fin del programa.");
	}
}