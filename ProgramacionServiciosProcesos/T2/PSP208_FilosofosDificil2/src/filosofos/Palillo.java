package filosofos;

class Palillo
{
	private boolean enUso = false;

	public synchronized void tomar() throws InterruptedException
	{
		while (enUso)
		{
			wait();
		}
		enUso = true;
	}

	public synchronized void soltar()
	{
		enUso = false;
		notify();
	}
}