// Creado por Oscar Montero		01/10/2024

package procesos;

import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			// Proceso de Notepad
			ProcessBuilder pbNotepad = new ProcessBuilder("notepad");
			Process procesoNotepad = pbNotepad.start();
			System.out.println("Proceso de Notepad iniciado. PID: " + procesoNotepad.pid());

			// Esperamos a que termine el proceso
			int exitCodeNotepad = procesoNotepad.waitFor();
			System.out.println("Proceso de Notepad finalizado con código: " + exitCodeNotepad);

			// Proceso de Paint
			ProcessBuilder pbPaint = new ProcessBuilder("mspaint");
			Process procesoPaint = pbPaint.start();
			System.out.println("Proceso de Paint iniciado. PID: " + procesoPaint.pid());

			// Esperamos a que termine el proceso
			int exitCodePaint = procesoPaint.waitFor();
			System.out.println("Proceso de Paint finalizado con código: " + exitCodePaint);

		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			System.out.println("El proceso fue interrumpido.");
			e.printStackTrace();
		}
		System.out.println("\nEl programa ha finalizado.");
	}
}
