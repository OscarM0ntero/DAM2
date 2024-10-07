fun main()
{
    var h: Int = 4
    for(i in 1..h)
    {
        // 2*i+1 es la cantidad de asteríscos que queremos por línea
        for(j in 1..(2*i-1))    
            print("*")
        println()
    }
}