package procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("cmd.exe", "/c", "dir");

			processBuilder.directory(new File(System.getProperty("user.home") + "/Downloads"));

			// Iniciamos el proceso
			Process process = processBuilder.start();

			// Capturamos el flujo de salida
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			// Leemos la salida del proceso hijo línea por línea
			while ((line = reader.readLine()) != null)
			{
				// Enviamos la salida del proceso hijo al flujo de salida del proceso padre
				System.out.println(line);
			}

			int exitCode = process.waitFor();
			System.out.println("\nProceso terminado con código de salida: " + exitCode);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
