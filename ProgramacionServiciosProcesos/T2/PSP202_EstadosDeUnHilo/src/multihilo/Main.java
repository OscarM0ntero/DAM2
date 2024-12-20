// Hecho por Oscar Montero	21/10/2024
package multihilo;

public class Main {

    public static void main(String[] args) {
        Raton tinky = new Raton("Tinky", 4);
        Raton winky = new Raton("Winky", 2);
        Raton poo = new Raton("Pooo", 1);
        Raton lala = new Raton("Lalala", 5);

        long time = System.currentTimeMillis();

        // Iniciar los hilos
        tinky.start();
        winky.start();
        poo.start();
        lala.start();

        // Seguimiento del estado del hilo "tinky"
        Thread.State estadoAnterior = tinky.getState();
        System.out.printf("Estado inicial del hilo %s: %s%n", tinky.getNombre(), estadoAnterior);

        // Bucle para monitorizar los cambios de estado del hilo "tinky"
        while (tinky.getState() != Thread.State.TERMINATED) {
            Thread.State estadoActual = tinky.getState();
            if (estadoActual != estadoAnterior) {
                System.out.printf("El estado del hilo %s ha cambiado a: %s%n", tinky.getNombre(), estadoActual);
                estadoAnterior = estadoActual;
            }
            try {
                Thread.sleep(100); // Pausa para evitar consumir demasiados recursos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprimir el estado final del hilo
        System.out.printf("El estado final del hilo %s: %s%n", tinky.getNombre(), tinky.getState());

        // Esperar a que todos los hilos terminen
        try {
            tinky.join();
            winky.join();
            poo.join();
            lala.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        double tiempoSegundos = (double) time / 1000;

        System.out.printf("Hemos tardado %.3f segundos%n", tiempoSegundos);
    }
}
