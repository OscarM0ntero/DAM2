package pizzeria;

class Pizzeria {
    public static void main(String[] args) {
    	Mesa mesa = new Mesa(3);

        Thread horno = new Thread(new Horno(mesa, 20));
        horno.start();

        for (int i = 1; i <= 3; i++) {
            Thread repartidor = new Thread(new Repartidor(mesa, i));
            repartidor.start();
        }
    }
}