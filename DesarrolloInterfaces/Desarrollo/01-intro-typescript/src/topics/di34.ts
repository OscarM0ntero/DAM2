// La funcion sumar suma dos numeros si se los introducimos, si solo introudciomos uno se lo suma a si mismo
function sumar(a:number, b:number = a)
{
    return a+b;
}

// La funcion restar resta dos numeros si se los introducimos, si solo introudciomos uno se lo resta a si mismo
function restar(a:number, b:number = a)
{
    return a-b;
}

// La funcion multiplicar multiplica dos numeros si se los introducimos, si solo introudciomos uno se lo multiplica a si mismo
function multiplicar(a:number, b:number = a)
{
    return a*b;
}

// La funcion dividir divide dos numeros si se los introducimos, si solo introudciomos uno se lo multiplica a si mismo
// Si en algun caso el divisor es 0, avisa que no se puede realizar
function dividir(a:number, b:number = a)
{
    if(b==0)
        console.log("No se puede dividir entre 0")
    else
        return a/b;
}



export {};