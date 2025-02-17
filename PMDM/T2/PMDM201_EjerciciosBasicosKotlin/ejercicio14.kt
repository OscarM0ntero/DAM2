fun main()
{
    var lista = listOf("campamento", "cardinales", "polen", null, "hoja", "hermana", "relatividad", null) 
    for (item in lista)
    {
        if (item != null)
            println(item + " -> " + item.length)
    }
}