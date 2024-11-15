package semaforo;

public class Persona implements Runnable {
    private final String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(nombre + " quiere ir al baño.");
                Control.banios.acquire();
                System.out.println(nombre + " está usando el baño.");

                Thread.sleep((int) (Math.random() * 5000) + 1000);	// Tiempo de uso del baño

                System.out.println(nombre + " salió del baño.");
                Control.banios.release();

                Thread.sleep((int) (Math.random() * 5000) + 2000);	// Tiempo hasta tener que volver al baño

            } catch (InterruptedException e) {
                System.err.println(nombre + " fue interrumpido.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
