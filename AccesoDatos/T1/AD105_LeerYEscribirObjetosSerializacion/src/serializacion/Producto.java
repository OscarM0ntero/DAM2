// Creado por Oscar Montero		07/10/2024
package serializacion;

@SuppressWarnings("serial")
// A) Creamos la clase Producto
class Producto implements java.io.Serializable
{
    private String articulo;
    private float precio;
    private int existencias;

    public Producto(String articulo, float precio, int existencias)
    {
        this.articulo = articulo;
        this.precio = precio;
        this.existencias = existencias;
    }

    public String getArticulo()
	{
		return articulo;
	}

	public void setArticulo(String articulo)
	{
		this.articulo = articulo;
	}

	public float getPrecio()
	{
		return precio;
	}

	public void setPrecio(float precio)
	{
		this.precio = precio;
	}

	public int getExistencias()
	{
		return existencias;
	}

	public void setExistencias(int existencias)
	{
		this.existencias = existencias;
	}

    @Override
    public String toString()
    {
        return String.format("Producto {articulo=%s, precio=%.2f, existencias=%d}", articulo, precio, existencias);
    }
        
}


