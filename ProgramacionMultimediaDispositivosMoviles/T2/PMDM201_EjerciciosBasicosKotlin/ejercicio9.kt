
fun main() {
    var edad: Int = 25
    
    when (edad)
    {
        in 1..17 -> println("El usuario es menor de edad.")
        in 18..64 -> println("El usuario está en activo.")
        else -> println("El usuario está jubilado.")
    }
        
}
