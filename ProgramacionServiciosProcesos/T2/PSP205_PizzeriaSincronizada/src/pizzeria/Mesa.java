package pizzeria;

import java.util.LinkedList;
import java.util.List;

class Mesa {
    private final int capacidad;
    private final List<String> pizzas = new LinkedList<>();

    public Mesa(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void agregarPizza(String pizza) throws InterruptedException {
        while (pizzas.size() == capacidad) {
            wait();
        }
        pizzas.add(pizza);
        System.out.println("El horno acaba de producir una pizza: " + pizza);
        notifyAll();
    }

    public synchronized String recogerPizza() throws InterruptedException {
        while (pizzas.isEmpty()) {
            wait();
        }
        String pizza = pizzas.remove(0);
        notifyAll();
        return pizza;
    }
}