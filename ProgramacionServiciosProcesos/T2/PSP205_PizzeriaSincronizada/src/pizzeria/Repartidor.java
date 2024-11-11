package pizzeria;

import java.util.Random;

class Repartidor implements Runnable {
    private final Mesa mesa;
    private final int id;
    private final Random random = new Random();

    public Repartidor(Mesa mesa, int id) {
        this.mesa = mesa;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String pizza = mesa.recogerPizza();
                System.out.println("Repartidor " + id + " retira una pizza: " + pizza);
                Thread.sleep(random.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}