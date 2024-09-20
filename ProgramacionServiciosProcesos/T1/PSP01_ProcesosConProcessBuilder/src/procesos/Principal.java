package procesos;

import java.io.*;
import java.util.*;

public class Principal 
{
	
	public static void main() throws InterruptedException,IOException 
	{
		String command = "notepad.exe";
		
		ProcessBuilder builder = new ProcessBuilder(command);
		
		Map<String, String> environ = builder.environment();
		
		final Process process = builder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null)
		{
			System.out.println(line);
		}
		
		System.out.println("Program terminated!");
	}
	
}
