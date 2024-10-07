fun main()
{
    var nota: Float = 6.75f
    
    if (nota < 1 || nota > 10)
        println("Error. Nota invalida.")
    else if(nota < 5)
        println("Suspenso.")
    else if(nota < 6)
        println("Aprobado.")
    else if(nota < 7)
        println("Bien.")
    else if(nota < 8)
        println("Notable.")
    else
        println("Sobresaliente.")
        
}
