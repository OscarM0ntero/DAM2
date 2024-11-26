package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main
{
	private static SessionFactory sessionFactory;

	public static void main(String[] args)
	{
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Cantante.class);
		sessionFactory = config.buildSessionFactory();

		Scanner input = new Scanner(System.in);
		int opcion;

		do
		{
			System.out.println("\n=== MENÚ PRINCIPAL ===");
			System.out.println("1. Alta de cantantes");
			System.out.println("2. Consulta por género musical");
			System.out.println("3. Listado de todos los cantantes");
			System.out.println("4. Salir");
			System.out.print("Elija una opción: ");
			opcion = input.nextInt();
			input.nextLine();

			switch (opcion)
			{
			case 1:
				altaCantante(input);
				break;
			case 2:
				consultaPorGenero(input);
				break;
			case 3:
				listadoDeCantantes();
				break;
			case 4:
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Opción no válida. Intente nuevamente.");
			}
		} while (opcion != 5);

		sessionFactory.close();
	}

	private static void altaCantante(Scanner scanner)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("\nALTA DE CANTANTE");
		Cantante cantante = new Cantante();

		System.out.print("Ingrese el nombre del cantante: ");
		cantante.setNombre(scanner.nextLine());

		System.out.print("Ingrese el año: ");
		cantante.setAnio(scanner.nextInt());
		scanner.nextLine();

		System.out.print("Ingrese el nombre del álbum: ");
		cantante.setAlbum(scanner.nextLine());

		System.out.print("ID del género musical: ");
		int generoId = scanner.nextInt();

		GeneroMusical genero = session.get(GeneroMusical.class, generoId);
		if (genero == null)
		{
			System.out.println("Género no encontrado.");
			session.close();
			return;
		}
		cantante.setGenero(genero);

		session.persist(cantante);
		transaction.commit();
		session.close();
		System.out.println("Cantante añadido con éxito.");
	}

	private static void consultaPorGenero(Scanner input)
	{
		Session session = sessionFactory.openSession();

		System.out.print("ID del género musical: ");
		int generoId = input.nextInt();

		GeneroMusical genero = session.get(GeneroMusical.class, generoId);
		if (genero == null)
		{
			System.out.println("Género no encontrado.");
			session.close();
			return;
		}

		List<Cantante> cantantes = genero.getCantantes();
		System.out.println("Lista de cantantes para el género " + genero.getNombre() + ":");
		for (Cantante cantante : cantantes)
		{
			System.out.println(
					"Código: " + cantante.getCodigo() +
					", Nombre: " + cantante.getNombre() +
					", Año: " + cantante.getAnio() +
					", Álbum: " + cantante.getAlbum()
			);
		}
		session.close();
	}

	private static void listadoDeCantantes()
	{
		Session session = sessionFactory.openSession();
		List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();

		System.out.println("Listado de todos los cantantes:");
		for (Cantante cantante : cantantes)
		{
			System.out.println(
					"Código: " + cantante.getCodigo() + ", Nombre: " + cantante.getNombre() + ", Género: "
							+ cantante.getGenero().getNombre()
			);
		}

		session.close();
	}

}