package semaforo;

import java.util.concurrent.Semaphore;

public class Control {

    public static final Semaphore banios = new Semaphore(3);

    public static void main(String[] args) {
    	String[] nombres = {"Juan", "Pepe", "Antonio", "Marcos", "Domingo", "Lucas", "Oscar", "Daniel", "Alfonso", "Roberto"};
        for (int i = 0; i < 10; i++) { 
            new Thread(new Persona(nombres[i])).start();
        }
    }
}
