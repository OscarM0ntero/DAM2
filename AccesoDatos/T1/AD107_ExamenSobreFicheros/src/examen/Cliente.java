// Creado por Oscar Montero		08/10/2024
package examen;

public class Cliente implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
    private int Age;
    private String Email;
    private String City;

    public Cliente(String name, int age, String email, String city)
    {
        this.Name = name;
        this.Age = age;
        this.Email = email;
        this.City = city;
    }

    public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public int getAge()
	{
		return Age;
	}

	public void setAge(int age)
	{
		Age = age;
	}

	public String getEmail()
	{
		return Email;
	}

	public void setEmail(String email)
	{
		Email = email;
	}

	public String getCity()
	{
		return City;
	}

	public void setCity(String city)
	{
		City = city;
	}

	@Override
    public String toString()
    {
        return String.format("%s,%d,%s,%s", Name, Age, Email, City);
    }
}
