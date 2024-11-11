package pizzeria;

import java.util.Random;

class Horno implements Runnable {
    private final Mesa mesa;
    private final int cantidadPizzas;
    private final String[] nombresPizzas = {"Margarita", "Pepperoni", "Hawaiana", "Cuatro Quesos", "Barbacoa"};
    private final Random random = new Random();

    public Horno(Mesa mesa, int cantidadPizzas) {
        this.mesa = mesa;
        this.cantidadPizzas = cantidadPizzas;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cantidadPizzas; i++) {
                String pizza = nombresPizzas[random.nextInt(nombresPizzas.length)];
                mesa.agregarPizza(pizza);
                Thread.sleep(random.nextInt(500) + 500);
            }
            System.out.println("El horno ha terminado de producir todas las pizzas.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
