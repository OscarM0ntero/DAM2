/**
 * Bucles indeterminados: while y do while
 */
fun main() {
	val str: String = "zapatos"
    var count: Int = 0
    
    for (i in 0..str.length-1) {
        var c: Char = str.get(i)
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            count++
        }
    }
    
    println("Hay " + count + " vocales")
    
}
