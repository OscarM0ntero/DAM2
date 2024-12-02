public class Pitufos
{

	static class AsientoComedor
	{
		private boolean enUso = false;

		public synchronized void tomarAsiento() throws InterruptedException
		{
			while (enUso)
			{
				wait();
			}
			enUso = true;
		}

		public synchronized void dejarAsiento()
		{
			enUso = false;
			notify();
		}
	}

	static class Pitufo extends Thread
	{
		private final int id;
		private final AsientoComedor[] comedor;

		public Pitufo(int id, AsientoComedor[] comedor)
		{
			this.id = id;
			this.izquierdo = izquierdo;
			this.derecho = derecho;
		}

		@Override
		public void run()
		{
			try
			{
				while (true)
				{
					esperar();
					tomarAsiento();
					comer();
					dejarAsiento();
				}
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				System.out.println("Pitufo " + id + " interrumpido.");
			}
		}

		private void esperar() throws InterruptedException
		{
			System.out.println("Pitufo " + id + " está esperando.");
			Thread.sleep((long) (Math.random() * 1000));
		}

		private void tomarTenedores() throws InterruptedException
		{
			if (id % 2 == 0)
			{
				// Filósofos pares toman primero el tenedor izquierdo
				synchronized (izquierdo)
				{
					izquierdo.tomar();
					synchronized (derecho)
					{
						derecho.tomar();
					}
				}
			}
			else
			{
				// Filósofos impares toman primero el tenedor derecho
				synchronized (derecho)
				{
					derecho.tomar();
					synchronized (izquierdo)
					{
						izquierdo.tomar();
					}
				}
			}
			System.out.println("Filósofo " + id + " tomó los tenedores.");
		}

		private void comer() throws InterruptedException
		{
			System.out.println("Filósofo " + id + " está comiendo.");
			Thread.sleep((long) (Math.random() * 1000)); // Simula tiempo comiendo
		}

		private void soltarTenedores()
		{
			synchronized (izquierdo)
			{
				izquierdo.soltar();
			}
			synchronized (derecho)
			{
				derecho.soltar();
			}
			System.out.println("Filósofo " + id + " soltó los tenedores.");
		}
	}

	public static void main(String[] args)
	{
		int numPitufos = 5;
		Tenedor[] tenedores = new Tenedor[numPitufos];

		// Crear tenedores
		for (int i = 0; i < numPitufos; i++)
		{
			tenedores[i] = new Tenedor();
		}

		// Crear filósofos
		Pitufo[] pitufos = new Pitufo[numPitufos];
		for (int i = 0; i < numPitufos; i++)
		{
			pitufos[i] = new Pitufo(i, tenedores[i], tenedores[(i + 1) % numPitufos]);
			pitufos[i].start();
		}

		// Esperar a que todos los hilos terminen (en este caso no terminarán)
		for (Pitufo pitufo : pitufos)
		{
			try
			{
				pitufo.join();
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
	}
}
