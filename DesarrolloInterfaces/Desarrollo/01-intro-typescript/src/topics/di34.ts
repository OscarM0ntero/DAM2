// La funcion sumar suma dos numeros si se los introducimos,
// si solo introudciomos uno se lo suma a si mismo.
function sumar(a:number, b:number = a)
{
    return a+b;
}

// La funcion restar resta dos numeros si se los introducimos,
// si solo introudciomos uno se lo resta a si mismo.
function restar(a:number, b:number = a)
{
    return a-b;
}

// La funcion multiplicar multiplica dos numeros si se los introducimos,
// si solo introudciomos uno se lo multiplica a si mismo.
function multiplicar(a:number, b:number = a)
{
    return a*b;
}

// La funcion dividir divide dos numeros si se los introducimos,
// si solo introudciomos uno se lo multiplica a si mismo.
// Si en algun caso el divisor es 0, avisa que no se puede realizar.
function dividir(a:number, b:number = a)
{
    if(b==0)
        return("No se puede dividir entre 0")
    else
        return a/b;
}

// Salida por consola
console.log("suma 4+4=",sumar(4))
console.log("resta 7-2=",restar(7,2))
console.log("multiplica 6*9=",multiplicar(6,9))
console.log("divide 10/2=",dividir(10,2))
console.log("divide 5/0=",dividir(5,0))

export {};