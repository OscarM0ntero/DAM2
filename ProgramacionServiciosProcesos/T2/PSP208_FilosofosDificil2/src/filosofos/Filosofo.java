package filosofos;

// Nuestro filosofo ahora tiene dos semaforos, el tiene inicialmente un palillo, y como segundo palillo 
// referenciara al palillo del filosofo de al lado, por eso inicialmente uno es null; una vez creados
// todos los filosofos se asignan los palillos que faltan
class Filosofo extends Thread {
	private String nombre;
	private String color;
    private final Palillo derecho;
    private final Palillo izquierdo;

    public Filosofo(String nombre, String color, Palillo izquierdo, Palillo derecho) {
    	this.nombre = nombre;
    	this.color = color;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    private void pensar() throws InterruptedException {
    	System.out.println(color + tmp() + nombre + "\t-> está pensando...");
        Thread.sleep((long) ((Math.random() + Mesa.tiempoCenaMinimo) * 1000));
    }

    private void comer() throws InterruptedException {
        System.out.println(color + tmp() + nombre + "\t-> está intentando comer...");
        izquierdo.tomar();
        System.out.println(color + tmp() + nombre + "\t-> tomó el tenedor izquierdo.");
        derecho.tomar();
        System.out.println(color + tmp() + nombre + "\t-> tomó el tenedor derecho.");

        System.out.println(color + tmp() + nombre + "\t-> está comiendo...");
        Thread.sleep((long) (Math.random() * 1000));

        izquierdo.soltar();
        System.out.println(color + tmp() + nombre + "\t-> soltó el tenedor izquierdo.");
        derecho.soltar();
        System.out.println(color + tmp() + nombre + "\t-> soltó el tenedor derecho.");
    }

    @Override
    public void run() {
        try {
            while (true) {
            	
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(color + tmp() + nombre + "\t-> fue interrumpido.");
        }
    }
	
	// Temporizador para imprimir los tiempos de ejecucion en ms
	public String tmp()
	{
		return String.format("[%dms]\t", System.currentTimeMillis() - Mesa.tiempoInicial);
	}
}
