
fun main() { 
    var num: Int = 12345  
    var invertido: Int = 0

    while (num > 9)
    {
        invertido *= 10
        invertido += num % 10
        num /= 10
    }
    invertido = invertido * 10 + num
    println(invertido)

}
