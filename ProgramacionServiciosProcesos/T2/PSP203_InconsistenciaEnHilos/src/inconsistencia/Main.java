// Hecho por Oscar Montero	22/10/2024
package inconsistencia;

public class Main {
	private static int contador = 0;

	public static void main(String[] args) {
		// Crear los hilos
		Thread hilo1 = new Thread(new Incrementador(1));
		Thread hilo2 = new Thread(new Incrementador(3));
		Thread hilo3 = new Thread(new Incrementador(5));

		// Iniciar los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();

		// Esperar a que los hilos terminen
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Valor final del contador: " + contador);
	}

	static class Incrementador implements Runnable {
		private final int incremento;

		public Incrementador(int incremento) {
			this.incremento = incremento;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				int anterior = contador;
				contador += incremento;
				System.out.println(anterior + "\t+" + incremento + " = " + contador);
			}
		}
	}
}
