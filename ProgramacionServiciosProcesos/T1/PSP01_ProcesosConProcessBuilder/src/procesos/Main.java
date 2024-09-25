package procesos;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Procesos de calculadora
            ProcessBuilder pbCalculadora1 = new ProcessBuilder("calc");
            Process procesoCalculadora1 = pbCalculadora1.start();
            System.out.println("Proceso de calculadora iniciado. PID: " + procesoCalculadora1.pid());
            
            ProcessBuilder pbCalculadora2 = new ProcessBuilder("calc");
            Process procesoCalculadora2 = pbCalculadora2.start();
            System.out.println("Proceso de calculadora iniciado. PID: " + procesoCalculadora2.pid());

            // Proceso de navegador
            ProcessBuilder pbNavegador = new ProcessBuilder("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            Process procesoNavegador = pbNavegador.start();
            System.out.println("Proceso de navegador iniciado. PID: " + procesoNavegador.pid());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
